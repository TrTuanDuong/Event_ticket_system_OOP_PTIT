# ✨ FEATURES - CHI TIẾT TÍNH NĂNG

> **Cinema Ticket Management System**  
> **Version**: 1.0.0  
> **Cập nhật**: Tháng 11/2025

**Tài liệu chi tiết về các tính năng, code location, và implementation details**

---

## 📋 MỤC LỤC

1. [Authentication & Authorization](#-authentication--authorization)
2. [User Features](#-user-features)
3. [Admin Features](#-admin-features)
4. [Special Features](#-special-features)
5. [Implementation Details](#-implementation-details)

---

## 🔐 AUTHENTICATION & AUTHORIZATION

### ✅ Login System

**Mô tả**: Xác thực người dùng với Django password hash

**Tài khoản mặc định**:

- Admin: `admin` / `admin`
- User: `test` / `123456`

**Features**:

- Django Password Compatibility: PBKDF2-SHA256 hash verification (260,000 iterations)
- Role-based Access: Admin/User roles
- Session Management: Keep user logged in across screens
- Error Handling: Invalid credentials, user not found, database errors

**Implementation**:

```java
// AuthService.java
public User authenticate(String username, String password) {
    User user = userRepo.findByUsername(username);
    if (user != null && DjangoPassword.verify(password, user.getPassword())) {
        SessionManager.getInstance().setCurrentUser(user);
        return user;
    }
    return null;
}
```

**Code Location**: `LoginController.java`, `AuthService.java`, `DjangoPassword.java`

---

### ✅ Register System

**Mô tả**: Đăng ký tài khoản mới

**Features**:

- User Registration: Create new account với auto-hash password
- Validation: Username unique, password strength, email format
- Auto-hash Password: Django-compatible PBKDF2
- Default Role: User (không phải admin)

**Implementation**:

```java
// AuthService.java
public boolean register(String username, String password, String email) {
    if (userRepo.findByUsername(username) != null) {
        return false; // Username exists
    }
    String hashedPassword = DjangoPassword.hash(password);
    User newUser = new User(username, hashedPassword, email, false);
    userRepo.insert(newUser);
    return true;
}
```

**Code Location**: `RegisterController.java`, `AuthService.java`

---

### ✅ Session Management

**Mô tả**: Quản lý phiên đăng nhập

**Features**:

- Current User Tracking: SessionManager singleton
- Persistent Login: User object available across all screens
- Logout: Clear session và redirect to login

**Implementation**:

```java
// SessionManager.java (Singleton)
private static SessionManager instance;
private User currentUser;

public static SessionManager getInstance() {
    if (instance == null) {
        instance = new SessionManager();
    }
    return instance;
}

public User getCurrentUser() { return currentUser; }
public void setCurrentUser(User user) { this.currentUser = user; }
public void logout() { this.currentUser = null; }
```

**Code Location**: `SessionManager.java`, `SessionTimer.java`

---

## 👤 USER FEATURES

### ✅ Dashboard

**Mô tả**: Trang chủ sau khi login

**Features**:

- Welcome message với username
- Movies đang chiếu (grid layout với posters)
- Quick actions: Đặt vé ngay, Xem chi tiết phim
- Navigation: Profile, My Bookings, Logout

**UI Components**:

- Header: Welcome + username + actions
- Body: Movie cards với poster, title, duration, rating
- Footer: Quick links

**Code Location**: `DashboardController.java`, `dashboard.fxml`

---

### ✅ Movie List

**Mô tả**: Xem và tìm kiếm phim

**Features**:

- Grid layout với movie posters
- Search bar: Tìm theo tên phim
- Filter: Theo thể loại (Action, Drama, Sci-Fi...)
- Movie details: Title, Director, Duration, Rating, Description
- Action: "Đặt vé" button → Showtime list

**UI Components**:

- Search field với real-time filtering
- Genre filter dropdown
- Movie cards: Poster + info + actions
- Responsive grid layout

**Code Location**: `MovieListController.java`, `movie-list.fxml`

---

### ✅ Showtime Selection

**Mô tả**: Chọn suất chiếu cho phim

**Features**:

- List showtimes cho phim đã chọn
- Hiển thị: Thời gian, Phòng chiếu, Giá vé
- Filter theo ngày
- Click showtime → Seat map
- Back to movie list

**UI Components**:

- Movie info header (title, poster)
- Date picker
- Showtime cards: Time + Auditorium + Price
- "Chọn ghế" button

**Code Location**: `EventController.java`, `EventView.fxml`

---

### ✅ Seat Map - Interactive Selection

**Mô tả**: Chọn ghế ngồi interactive với real-time updates

**Features**:

- **Visual Seat Map**: Grid layout theo hàng/cột
- **Color Coding**:
  - 🟢 Green: Available (có thể chọn)
  - 🔴 Red: Booked (đã đặt)
  - 🔵 Blue: Selected (đang chọn)
- **Seat Types**:
  - Standard: 50,000 VND
  - VIP: 80,000 VND
  - Couple: 150,000 VND
- **Timer**: 10-minute countdown
- **Total Price**: Auto-calculate khi chọn ghế
- **Multi-select**: Chọn nhiều ghế cùng lúc
- **Real-time Updates**: Auto-refresh seat availability mỗi 5 giây

**Real-time Seat Availability**:

- Background scheduler checks database mỗi 5 giây
- Automatically updates UI khi ghế được book bởi user khác
- Removes selected seats nếu bị book
- Shows alert nếu seat đang chọn bị book
- Freed seats (từ canceled bookings) tự động available lại
- Daemon thread tự động stop khi leave screen

**Timer Behavior**:

- Starts khi vào seat map
- Shows: "⏰ 09:45" (minutes:seconds)
- Color changes: Green → Orange (<3min) → Red (<1min)
- Timeout: Alert + redirect to movie list
- Stops: Khi confirm payment hoặc back

**UI Components**:

- Movie + Showtime info header
- Timer display (prominent)
- Seat grid (interactive buttons)
- Legend: Available/Booked/Selected
- Selected seats list
- Total price calculation
- "Tiếp tục" button

**Code Location**: `SeatMapController.java`, `SeatMap.fxml`, `SessionTimer.java`

**Implementation Details**:

```java
// Real-time update scheduler
ScheduledExecutorService seatUpdateScheduler;
- Refresh interval: 5 seconds
- Daemon thread (auto-stops)
- Platform.runLater() for UI updates

// Methods:
startSeatUpdateScheduler(): Start monitoring
refreshSeatAvailability(): Query database
updateSeatButtons(newlyBooked, newlyFreed): Update UI
stopSeatUpdateScheduler(): Cleanup

// SeatRepo:
getBookedSeatIds(UUID showtimeId): Get all booked seat IDs
```

---

### ✅ Payment Screen

**Mô tả**: Thanh toán và confirm booking

**Features**:

- **Payment Methods**:
  - 💳 QR Code: Pending approval from admin
  - 💵 Cash: Instant confirmation
- **Booking Summary**:
  - Movie title
  - Showtime
  - Selected seats
  - Total amount
- **Timer Continuation**: Timer từ seat map continues
- **Status Messages**:
  - QR: "⏳ Waiting for Admin Approval"
  - Cash: "✅ Booking Confirmed - Pay at Counter"

**Workflow**:

1. Review booking details
2. Select payment method
3. Click "Xác nhận thanh toán"
4. Create booking in database
5. Show confirmation message
6. Redirect to My Bookings

**Code Location**: `PaymentController.java`, `payment.fxml`

---

### ✅ My Bookings

**Mô tả**: Xem lịch sử đặt vé

**Features**:

- List all bookings của user
- Booking info: Movie, Showtime, Seats, Amount, Status
- **Status Colors**:
  - 🟢 Green: Paid (confirmed)
  - 🟠 Orange: Pending approval (QR payment)
  - 🔴 Red: Canceled/Expired
- Filter: All/Paid/Pending/Canceled
- Sort: Newest first
- Action: View details, Cancel (if pending)

**UI Components**:

- Booking cards với color-coded status
- Movie poster thumbnail
- Booking details
- Actions: View details, Cancel

**Code Location**: `MyBookingsController.java`, `my-bookings.fxml`

---

### ✅ Profile Settings

**Mô tả**: Quản lý thông tin cá nhân

**Features**:

- View current info: Username, Email, Full Name
- Update profile: Email, Full Name
- Change password: Old password + New password + Confirm
- Validation: Password strength, email format
- Logout button
- Success/Error messages

**UI Components**:

- Profile form với editable fields
- Save button
- Change password section
- Logout button
- Settings + Logout in header (all screens)

**Code Location**: `ProfileSettingsController.java`, `profile-settings.fxml`

---

## 👨‍💼 ADMIN FEATURES

### ✅ Admin Panel - Dashboard

**Mô tả**: Trang quản trị toàn diện

**Features**: 7 tabs quản lý

**Access**: Admin Panel button (chỉ admin thấy)

**Code Location**: `AdminPanelController.java`, `admin-panel.fxml`

---

### ✅ Tab 1: Movies Management

**CRUD Operations**:

- **Create**: Add new movie
  - Fields: Title, Genre(s), Director, Duration, Rating, Release Date, Poster URL, Description
  - Genre selection: Multiple checkboxes
  - Validation: Required fields, duration > 0
- **Read**: List all movies
  - Display: Poster, Title, Genres, Director, Duration, Rating
  - Search: By title, director, genre
- **Update**: Edit movie info
  - Pre-fill form với current data
  - Update genres (checkboxes pre-selected)
- **Delete**: Remove movie
  - Confirmation dialog
  - Check showtimes trước khi xóa

**UI Components**:

- Search field
- "Thêm Phim Mới" button
- Movie cards với Edit/Delete buttons
- Dialog forms cho Create/Edit

---

### ✅ Tab 2: Showtimes Management

**CRUD Operations**:

- **Create**: Add new showtime
  - Fields: Movie (dropdown), Auditorium (dropdown), Start time, End time, Base price
  - Validation:
    - Start < End time
    - Start time > now
    - No schedule conflict (same auditorium)
  - Format: ISO 8601 (2024-12-25T19:00:00+07:00)
- **Read**: List upcoming showtimes
  - Display: Movie title, Auditorium, Start time, Price
  - Sort: By start time
- **Update**: Edit showtime (TBD)
- **Delete**: Remove showtime
  - Confirmation dialog
  - Check bookings trước khi xóa

**Validation Logic**:

```java
- hasScheduleConflict(): Check trùng lịch trong auditorium
- validateShowtime(): Comprehensive validation
```

**UI Components**:

- "Thêm Suất chiếu" button
- Showtime cards với Edit/Delete buttons
- Dialog form với ComboBox cho Movie/Auditorium

---

### ✅ Tab 3: Auditoriums Management

**CRUD Operations**:

- **Create**: Add new auditorium
  - Fields: Name, Standard rows, VIP rows, Couple rows, Seats per row
  - Validation: All > 0
- **Read**: List all auditoriums
  - Display: Name, Capacity (rows x seats), Seat types
- **Update**: Edit auditorium
  - Pre-fill form
  - Update configuration
- **Delete**: Remove auditorium
  - Confirmation dialog
  - Check showtimes trước khi xóa

**UI Components**:

- "Thêm Phòng" button
- Auditorium cards với Edit/Delete buttons
- Dialog forms

---

### ✅ Tab 4: Users Management

**Operations**:

- **Read**: List all users
  - Display: Username, Email, Role, Created date
  - Search: By username, email
- **Create**: Add new user (admin can create)
- **Update**: Edit user info (TBD)
- **Role Management**: Assign admin/user role (TBD)

**UI Components**:

- Search field
- User cards/table
- "Thêm User" button

---

### ✅ Tab 5: Bookings Management

**Operations**:

- **Read**: View all bookings
  - Display: User, Movie, Showtime, Seats, Amount, Status, Payment method
  - Filter: By status (All/Paid/Pending/Canceled)
  - Sort: Newest first
  - Limit: 50 recent bookings
- **View Details**: Click "👁️ Xem vé" button
  - Shows: Full booking info + list of tickets with seats
  - Ticket details: Row + Seat number + Seat type

**UI Components**:

- Booking cards với status color-coding
- "Xem vé" button
- Detail dialog với ticket list

**Code**:

```java
handleViewBookingTickets(UUID bookingId) {
    // Query booking + tickets + seats (JOINs)
    // Show Alert với full details
}
```

---

### ✅ Tab 6: QR Payment Approval

**Mô tả**: Phê duyệt thanh toán QR Code

**Workflow**:

1. User chọn QR payment → Booking status = "pending_approval"
2. Admin vào tab này → Xem list bookings chờ duyệt
3. Admin click "✅ Duyệt" hoặc "❌ Từ chối"
4. Duyệt: Status → "paid", User nhận confirmation
5. Từ chối: Status → "canceled", User nhận thông báo

**Features**:

- List pending QR payments
- Display: User, Movie, Showtime, Amount
- Actions: Approve/Reject với confirmation
- Real-time update: Refresh sau khi duyệt
- Notification: Success messages

**UI Components**:

- Pending approval cards
- "✅ Duyệt" button (green)
- "❌ Từ chối" button (red)
- "🔄 Refresh" button

**Code Location**:

```java
BookingService:
- getPendingApprovals(): Query pending_approval bookings
- approveBooking(UUID): Update status to "paid"
- rejectBooking(UUID): Update status to "canceled"

AdminPanelController:
- loadPendingApprovals(): Display cards
- handleApproveBooking(): Confirm + approve
- handleRejectBooking(): Confirm + reject
```

---

### ✅ Tab 7: Statistics Dashboard

**Metrics**:

- 📽️ **Total Movies**: COUNT từ api_movie
- 👥 **Total Users**: COUNT từ api_user
- 🎟️ **Total Bookings**: COUNT từ api_booking
- (Future): Revenue, Popular movies, Occupancy rate

**UI Components**:

- Stats cards với icons
- Large numbers (bold, colored)
- Labels
- Auto-refresh khi có changes

---

## ⭐ SPECIAL FEATURES

### ✅ 10-Minute Booking Timer

**Mô tả**: Countdown timer cho booking session

**Behavior**:

- **Start**: Khi user vào seat map
- **Duration**: 600 seconds (10 minutes)
- **Display**: "⏰ 09:45" format (MM:SS)
- **Color Coding**:
  - Green: > 3 minutes
  - Orange: 1-3 minutes
  - Red: < 1 minute
- **Continuation**: Timer continues từ seat map → payment
- **Stop Conditions**:
  - User confirms payment ✅
  - User clicks back ❌
  - Timer expires (0:00) ⏰
- **Timeout Action**: Alert + redirect to movie list

**Implementation**:

```java
SessionTimer (Singleton):
- startTimer(onTick, onTimeout)
- stopTimer()
- getFormattedTime(): "MM:SS"
- isRunning(): boolean

SeatMapController:
- Initialize timer với callbacks
- updateTimerDisplay(): UI update mỗi giây
- handleTimeout(): Show alert + navigate

PaymentController:
- Continue timer từ SeatMap
- stopTimer() khi confirm payment
```

**Code Location**: `SessionTimer.java`, `SeatMapController.java`, `PaymentController.java`

---

### ✅ QR Payment Approval Workflow

**Mô tả**: Two-step payment approval

**Flow**:

```
User selects QR payment
    ↓
Booking created với status = "pending_approval"
    ↓
User sees: "⏳ Waiting for Admin Approval"
    ↓
Admin receives notification (in QR Approval tab)
    ↓
Admin reviews booking details
    ↓
Admin APPROVES → Status = "paid" ✅
    OR
Admin REJECTS → Status = "canceled" ❌
    ↓
User receives notification
```

**Database States**:

- `pending_approval`: Chờ admin duyệt
- `paid`: Đã được duyệt/thanh toán
- `canceled`: Bị từ chối

**Benefits**:

- Security: Verify payment trước khi confirm
- Control: Admin kiểm soát transactions
- Audit: Track approval history

---

### ✅ Real-time Seat Selection

**Mô tả**: Interactive seat map với visual feedback

**Features**:

- Click seat → Toggle selection
- Color changes instantly
- Multi-select support
- Price updates real-time
- Booked seats: Disabled (không click được)

**Visual States**:

- 🟢 Available: Green background, clickable
- 🔴 Booked: Red background, disabled
- 🔵 Selected: Blue background, clickable (to deselect)

**Calculation**:

```
Total = Σ(seat.price)
where seat.price = base_price × seat_type_multiplier
```

---

### ✅ Django Password Compatibility

**Mô tả**: Verify passwords với Django PBKDF2-SHA256

**Format**: `pbkdf2_sha256$iterations$salt$hash`

**Implementation**:

```java
DjangoPassword.verify(plainPassword, djangoHash):
1. Parse hash components (iterations, salt, hash)
2. Hash plainPassword với same salt & iterations
3. Compare hashes (constant-time comparison)
4. Return boolean
```

**Use Cases**:

- Login verification
- Password change validation
- Shared database với Django backend

**Security**:

- 600,000 iterations (PBKDF2)
- Random salt per password
- SHA-256 hashing
- Constant-time comparison (prevent timing attacks)

---

## 🛠️ IMPLEMENTATION DETAILS

### Connection Pooling (HikariCP)

**Implementation**:

```java
// Database.java (Singleton)
private static HikariDataSource dataSource;

public static Connection getConnection() throws SQLException {
    if (dataSource == null) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        dataSource = new HikariDataSource(config);
    }
    return dataSource.getConnection();
}
```

**Configuration**:

- Max pool size: 10 connections
- Connection timeout: 30s
- Auto-recovery from failures
- Connection leak detection

**Code Location**: `Database.java`

---

### Transaction Management

**Implementation**:

```java
// Tx.java - Functional transaction wrapper
public static <T> T withTx(DataSource ds, SqlFunction<Connection, T> fn) {
    try (Connection conn = ds.getConnection()) {
        conn.setAutoCommit(false);
        try {
            T result = fn.apply(conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }
}
```

**Usage Example**:

```java
List<Movie> movies = Tx.withTx(Database.getDataSource(), conn -> {
    return movieRepo.findAll(conn);
});
```

**Code Location**: `Tx.java`

---

### Repository Pattern

**Base Repository**:

```java
// BaseRepo.java (Template Method Pattern)
public abstract class BaseRepo<T> {
    protected abstract T mapRow(ResultSet rs) throws SQLException;
    protected abstract String getTableName();

    public List<T> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + getTableName();
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        }
        return results;
    }
}
```

**Concrete Repository Example**:

```java
// MovieRepo.java
public class MovieRepo extends BaseRepo<Movie> {
    @Override
    protected String getTableName() { return "api_movie"; }

    @Override
    protected Movie mapRow(ResultSet rs) throws SQLException {
        return new Movie(
            UUID.fromString(rs.getString("id")),
            rs.getString("title"),
            rs.getString("director"),
            rs.getInt("duration_min"),
            rs.getDouble("rating")
        );
    }
}
```

**Available Repositories**:

- `UserRepo`: findByUsername, findAll, insert
- `MovieRepo`: findAll, findById, insert, update, delete
- `ShowtimeRepo`: findUpcoming, findByMovieId, hasScheduleConflict
- `BookingRepo`: findPendingApproval, updateStatus, findByUser
- `SeatRepo`: findByShowtime, updateStatus, getBookedSeatIds

**Code Location**: `repo/` package

---

---

## � TÀI LIỆU LIÊN QUAN

- **[README.md](README.md)**: Tổng quan dự án, quick start, kiến trúc
- **[Chạy.md](Chạy.md)**: Hướng dẫn cài đặt chi tiết
- **[TASK_ASSIGNMENT.md](TASK_ASSIGNMENT.md)**: Phân công công việc 4 người
- **Database Diagrams**: `database/diagrams/`

---

## 🔗 CODE STRUCTURE

```
src/main/java/com/ptit/ticketing/
├── ui/           → 10 Controllers (Login, Dashboard, MovieList, SeatMap, Payment, Admin...)
├── service/      → 9 Services (Auth, Movie, Booking, Seat, Report, Session...)
├── repo/         → 6 Repositories (User, Movie, Showtime, Booking, Seat...)
├── domain/       → 8 Domain models (User, Movie, Genre, Showtime, Auditorium, Seat, Booking, Ticket)
├── config/       → Database.java (HikariCP connection pool)
├── auth/         → DjangoPassword.java (PBKDF2 hashing)
└── util/         → Tx.java (Transaction helper)
```

---

**🎓 Dự án OOP - PTIT**