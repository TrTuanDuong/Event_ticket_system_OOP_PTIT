# 🎬 Cinema Ticket Management System

> **Dự án OOP - PTIT**  
> **Tech Stack**: Java 21 + JavaFX 22 + PostgreSQL 16 + Maven

Hệ thống quản lý đặt vé rạp chiếu phim desktop với giao diện JavaFX hiện đại, tích hợp đầy đủ tính năng quản lý phim, suất chiếu, đặt vé, thanh toán và thống kê doanh thu.

## ✨ Tính năng nổi bật

### 👤 Dành cho Khách hàng

- 🔐 **Đăng ký/Đăng nhập**: Authentication với Django password hash (PBKDF2-SHA256)
- 🎬 **Duyệt phim**: Xem danh sách phim, search, filter theo thể loại
- 🎫 **Đặt vé thông minh**:
  - Chọn suất chiếu theo phim và thời gian
  - Seat map interactive với real-time updates (5s refresh)
  - Phân loại ghế: Standard (50k) / VIP (100k) / Couple (150k)
  - Timer 10 phút tự động release ghế
- 💳 **Thanh toán linh hoạt**: QR Code (chờ duyệt) hoặc Cash (tức thì)
- 📋 **Quản lý booking**: Xem lịch sử, trạng thái đặt vé
- ⚙️ **Profile**: Cập nhật thông tin, đổi mật khẩu

### 👨‍💼 Dành cho Admin

- 📽️ **Quản lý Phim**: CRUD phim với poster, thể loại, đạo diễn, thời lượng
- 🎬 **Quản lý Suất chiếu**: Tạo/sửa/xóa lịch chiếu, validation trùng lịch
- 🏛️ **Quản lý Phòng chiếu**: CRUD auditorium, cấu hình ghế
- 👥 **Quản lý User**: Xem danh sách, phân quyền
- 💰 **Phê duyệt thanh toán**: Approve/Reject QR payments
- 📊 **Thống kê & Báo cáo**: Revenue, occupancy rate, export Excel

---

## 🚀 QUICK START

### Prerequisites

```bash
# Check requirements
java -version    # Cần Java 21+
mvn --version    # Cần Maven 3.8+
psql --version   # Cần PostgreSQL 14+
```

### Cài đặt nhanh (5 phút)

```bash
# 1. Clone repository
git clone https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT.git
cd Event_ticket_system_OOP_PTIT

# 2. Import database (đã có sẵn dump file)
createdb cinema
psql -d cinema < database/cinema_dump.sql

# 3. Cấu hình database trong application.properties
# src/main/resources/application.properties
# Sửa db.user và db.password nếu cần

# 4. Build và chạy
mvn clean compile
mvn javafx:run
```

### Login mặc định

```
Admin:
- Username: admin
- Password: admin

User:
- Username: test
- Password: 123456
```

**Expected**: Cửa sổ JavaFX hiển thị danh sách movies & showtimes ✅

---

## 🏗️ KIẾN TRÚC HỆ THỐNG

### 3-Layer Architecture

```
UI Layer (FXML + Controllers)
    ↓ calls
Service Layer (Business Logic)
    ↓ calls
Repository Layer (Data Access)
    ↓ connects
PostgreSQL Database
```

**Chi tiết các layer**:

1. **Presentation Layer** - JavaFX UI

   - 10 FXML files: login, dashboard, movie-list, seat-map, payment, admin-panel...
   - 10 Controllers: xử lý events, binding data
   - Components: GridPane (seat map), FlowPane (movies), TableView (admin)

2. **Business Layer** - Services

   - `AuthService`: Login/Register, password hashing
   - `MovieService`, `ShowtimeService`: CRUD operations
   - `BookingService`: Booking logic, timer, payment workflow
   - `SeatService`: Seat availability, real-time updates
   - `ReportService`: Statistics, Excel export
   - `SessionManager`: User session tracking

3. **Data Access Layer** - Repositories

   - `BaseRepo<T>`: Template method pattern
   - 6 Repositories: UserRepo, MovieRepo, ShowtimeRepo, SeatRepo, BookingRepo
   - Transaction management: `Tx.java`
   - Connection pool: HikariCP (Singleton)

4. **Database Layer** - PostgreSQL
   - 10+ tables với relationships
   - Indexes để optimize queries
   - Foreign keys & constraints

### Design Patterns (8 patterns)

| Pattern             | Ứng dụng                               | Mục đích                          |
| ------------------- | -------------------------------------- | --------------------------------- |
| **Singleton**       | Database, SessionManager, SessionTimer | Đảm bảo chỉ 1 instance duy nhất   |
| **Template Method** | BaseRepo, BaseService                  | Định nghĩa skeleton cho CRUD      |
| **Repository**      | UserRepo, MovieRepo, etc.              | Tách biệt data access logic       |
| **MVC**             | Controllers + Services + Models        | Separation of concerns            |
| **Factory**         | Seat types (Standard/VIP/Couple)       | Tạo objects theo loại             |
| **Strategy**        | Payment methods (QR/Cash)              | Đa dạng hóa payment logic         |
| **State**           | Booking status (Pending/Paid/Canceled) | Quản lý trạng thái                |
| **Observer**        | Real-time seat updates                 | Auto-refresh UI khi data thay đổi |

## 🗄️ DATABASE SCHEMA

### Tables (10+)

- `api_user`: Users & Admins
- `api_movie`: Movies catalog
- `api_genre`: Movie genres
- `api_moviegenre`: Many-to-many relationship
- `api_auditorium`: Cinema halls
- `api_seat`: Seats configuration
- `api_showtime`: Movie showtimes
- `api_booking`: Booking records
- `api_ticket`: Individual tickets

### Key Relationships

```
User ─1:N→ Booking ─1:N→ Ticket ─N:1→ Seat
Movie ─1:N→ Showtime ←N:1─ Auditorium ─1:N→ Seat
Movie ─N:M→ Genre (via moviegenre)
Booking ─N:1→ Showtime
```

## 🎨 KỸ THUẬT NỔI BẬT

### 1. Real-time Seat Availability

```java
// Auto-refresh mỗi 5 giây
ScheduledExecutorService seatUpdateScheduler;
- Query database để check ghế đã book
- Platform.runLater() update UI (JavaFX thread-safe)
- Daemon thread tự động stop khi rời màn hình
```

### 2. Booking Timer System

```java
// SessionTimer (Singleton)
- 10 phút countdown từ khi chọn ghế
- Timeline animation với color transition
- Green → Orange (<3min) → Red (<1min)
- Auto-release seats khi timeout
```

### 3. Security - Django Password Compatibility

```java
// PBKDF2-SHA256 hashing
- 260,000 iterations
- Compatible với Django backend
- Salt-based hashing
```

### 4. Transaction Management

```java
// Tx.java - Rollback on error
Connection conn = Database.getConnection();
conn.setAutoCommit(false);
try {
    // Execute queries
    conn.commit();
} catch (Exception e) {
    conn.rollback();
}
```

### 5. Connection Pooling

```java
// HikariCP - High performance
- Max pool size: 10 connections
- Connection timeout: 30 seconds
- Singleton pattern
```

## 🧪 TESTING

```bash
# Test database connection
psql -d cinema -c "SELECT COUNT(*) FROM api_movie;"

# Compile project
mvn clean compile

# Run application
mvn javafx:run
```

---

## 📁 CẤU TRÚC PROJECT

```
Event_ticket_system_OOP_PTIT/
│
├── 📁 database/                          # Database scripts
│   ├── cinema_schema.sql                 # Schema (CREATE TABLE)
│   ├── cinema_data.sql                   # Sample data (INSERT)
│   ├── cinema_dump.sql                   # Full dump
│   ├── export-database.sh                # Export script
│   └── README.md                         # Database docs
│
├── 📁 src/main/
│   ├── 📁 java/com/ptit/ticketing/
│   │   ├── MainApp.java                  # 🚀 Entry point
│   │   ├── TestConnection.java           # DB test
│   │   │
│   │   ├── 📁 ui/                        # Controllers (10 files)
│   │   │   ├── LoginController.java
│   │   │   ├── RegisterController.java
│   │   │   ├── DashboardController.java
│   │   │   ├── MovieListController.java
│   │   │   ├── ShowtimeListController.java
│   │   │   ├── SeatMapController.java
│   │   │   ├── PaymentController.java
│   │   │   ├── MyBookingsController.java
│   │   │   ├── ProfileSettingsController.java
│   │   │   └── AdminPanelController.java (2529 lines)
│   │   │
│   │   ├── 📁 domain/                    # Entities (8 models)
│   │   │   ├── User.java
│   │   │   ├── Movie.java
│   │   │   ├── Genre.java
│   │   │   ├── Auditorium.java
│   │   │   ├── Seat.java
│   │   │   ├── Showtime.java
│   │   │   ├── Booking.java
│   │   │   └── Ticket.java
│   │   │
│   │   ├── 📁 repo/                      # Repositories (6 repos)
│   │   │   ├── BaseRepo.java
│   │   │   ├── UserRepo.java
│   │   │   ├── MovieRepo.java
│   │   │   ├── ShowtimeRepo.java
│   │   │   ├── SeatRepo.java
│   │   │   └── BookingRepo.java
│   │   │
│   │   ├── 📁 service/                   # Business logic (9 services)
│   │   │   ├── BaseService.java
│   │   │   ├── AuthService.java
│   │   │   ├── MovieService.java
│   │   │   ├── ShowtimeService.java
│   │   │   ├── SeatService.java
│   │   │   ├── BookingService.java
│   │   │   ├── ReportService.java        # 📊 Revenue stats + Excel
│   │   │   ├── SessionManager.java
│   │   │   └── SessionTimer.java
│   │   │
│   │   ├── 📁 config/
│   │   │   └── Database.java             # Connection pool
│   │   │
│   │   ├── 📁 auth/
│   │   │   └── DjangoPassword.java       # PBKDF2 hashing
│   │   │
│   │   └── 📁 util/
│   │       └── Tx.java                   # Transaction helper
│   │
│   └── 📁 resources/
│       ├── application.properties        # Database config
│       ├── 📁 ui/ (10 FXML files)
│       │   ├── login.fxml
│       │   ├── register.fxml
│       │   ├── dashboard.fxml
│       │   ├── movie-list.fxml
│       │   ├── showtime-list.fxml
│       │   ├── SeatMap.fxml
│       │   ├── payment.fxml
│       │   ├── my-bookings.fxml
│       │   ├── profile-settings.fxml
│       │   └── admin-panel.fxml
│       │
│       └── 📁 ImageView/
│           └── qr-payment.png            # QR code image
├── 📁 target/                            # Compiled classes (Maven)
├── pom.xml                               # Maven config
├── README.md                             # Project docs
├── FEATURES.md                           # Feature list
└── Chạy.md                               # Run instructions
```

---

## 🆘 GẶP LỖI?

### Lỗi thường gặp:

**1. Connection refused**

```bash
# Check PostgreSQL đang chạy
brew services list | grep postgresql
```

**2. Maven plugin error**

```bash
# Reinstall dependencies
mvn clean install -U
```

**3. Java version error**

```bash
# Check Java version (cần >= 21)
java -version
```

**Troubleshooting đầy đủ** → Xem [DEVELOPMENT_GUIDE.md](DEVELOPMENT_GUIDE.md) phần **"🆘 TROUBLESHOOTING"** (8 vấn đề + solutions)

---

## 📊 WORKFLOW HỆ THỐNG

### User Booking Flow

```
1. Login → Dashboard
2. Browse Movies (search/filter)
3. Select Movie → View Showtimes
4. Choose Showtime → Seat Map (timer bắt đầu ⏰)
5. Select Seats (Standard/VIP/Couple)
6. Payment (QR/Cash)
7. Booking Confirmation
```

### Admin Management Flow

```
1. Login Admin → Admin Panel
2. Tab Movies: CRUD phim, upload poster
3. Tab Showtimes: Tạo lịch chiếu, validation trùng
4. Tab Auditoriums: Quản lý phòng, cấu hình ghế
5. Tab QR Approval: Duyệt/Từ chối thanh toán
6. Tab Statistics: Xem báo cáo, export Excel
```

### Payment Workflow

```
QR Code Payment:
User chọn QR → Status: Pending → Admin duyệt → Status: Paid

Cash Payment:
User chọn Cash → Status: Paid (instant) → Pay tại quầy
```

## 🆘 TROUBLESHOOTING

### ❌ "Connection refused"

```bash
# Check PostgreSQL running
brew services list | grep postgresql
# Start if needed
brew services start postgresql@16
```

### ❌ "Database does not exist"

```bash
createdb cinema
psql -d cinema < database/cinema_dump.sql
```

### ❌ "Password authentication failed"

Sửa `src/main/resources/application.properties`:

```properties
db.user=your_postgres_username
db.password=your_postgres_password
```

### ❌ "Maven plugin error"

```bash
mvn clean install -U
```

### ❌ JavaFX runtime error

Ensure Java 21+ installed:

```bash
java -version
# Should show: openjdk version "21.x.x"
```

## � THỐNG KÊ DỰ ÁN

- **Tổng số files**: ~60 files
- **Lines of Code**: ~7,600 LOC
- **Domain Models**: 8 entities (User, Movie, Genre, Showtime, Auditorium, Seat, Booking, Ticket)
- **Repositories**: 6 repos (BaseRepo + 5 concrete)
- **Services**: 9 services
- **UI Controllers**: 10 controllers
- **FXML Files**: 10 screens
- **Database Tables**: 10+ tables
- **Design Patterns**: 8 patterns applied

## � LIÊN KẾT

- **Repository**: https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT
- **Database Diagrams**: `/database/diagrams/`
- **Task Assignment**: `TASK_ASSIGNMENT.md`

---

**🎓 Dự án OOP - Học viện Công nghệ Bưu chính Viễn thông (PTIT)**  
**Made with ❤️ by CODESEAT| **Java 21 + JavaFX 22 + PostgreSQL 16**
