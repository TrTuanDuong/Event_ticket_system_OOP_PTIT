# 🎬 Cinema Ticket Management System - JavaFX Edition

> **Dự án OOP - PTIT**  
> **Tech Stack**: Java 21 + JavaFX 22 + PostgreSQL 16 + Maven  
> **Timeline**: 10 days | **Progress**: 85% ✅  
> **Status**: Fully Functional with Admin Panel, Booking System, QR Payment Approval

Ứng dụng quản lý rạp chiếu phim desktop hiện đại sử dụng JavaFX, kết nối **trực tiếp** với PostgreSQL database.

## ✨ Tính năng nổi bật

- ✅ **Quản lý Phim**: CRUD phim với thể loại, đạo diễn, poster
- ✅ **Quản lý Suất chiếu**: Tạo, sửa, xóa lịch chiếu với validation
- ✅ **Quản lý Phòng chiếu**: CRUD auditorium với cấu hình ghế
- ✅ **Đặt vé**: Booking với timer 10 phút, chọn ghế interactive
- ✅ **Thanh toán QR**: Workflow phê duyệt payment từ admin
- ✅ **Admin Panel**: Dashboard quản lý toàn diện
- ✅ **Authentication**: Login/Register với Django password hash
- ✅ **Session Management**: Tự động logout sau timeout
- ✅ **Statistics**: Thống kê movies, users, bookings

---

## 📚 DOCUMENTATION (Chỉ 2 files!)

### 🎯 Bắt đầu với 2 file này:

#### 1. **[PROJECT_OVERVIEW.md](PROJECT_OVERVIEW.md)** ⭐ **(Đọc đầu tiên)**

**Mục đích**: Hiểu dự án - Ai làm gì, Kiến trúc thế nào

**Nội dung**:

- 📋 Giới thiệu dự án & mục tiêu
- 👥 **Phân công 4 thành viên** (A, B, C, D) - Trách nhiệm cụ thể
- 🏗️ Kiến trúc hệ thống (Shared Database + 3-layer)
- 📁 Cấu trúc project (files & folders)
- 🗄️ Database schema (10+ tables, relationships)
- 🎨 Design patterns (Singleton, Template Method, Repository...)
- 📅 Timeline 10 ngày

**Đọc khi**: Lần đầu vào project, cần hiểu overview

---

#### 2. **[DEVELOPMENT_GUIDE.md](DEVELOPMENT_GUIDE.md)** ⭐ **(Thực hành)**

**Mục đích**: Setup, chạy, test và develop dự án

**Nội dung**:

- 💻 Yêu cầu hệ thống (Java 21, Maven, PostgreSQL)
- 🛠️ **Cài đặt môi trường** (step-by-step)
- 🔧 **Setup dự án** (database, config)
- ▶️ **Chạy dự án** (Maven, IDE, JAR)
- 🧪 **Test dự án** (database, Java, app)
- 📊 Sử dụng dữ liệu (view, add, reset)
- ✅ **Công việc đã hoàn thành** (40% - 8 components)
- 💡 **Hướng dẫn develop** (services, UI, repos với code templates)
- 🆘 **Troubleshooting** (8 vấn đề thường gặp)
- 🔄 Git workflow

**Đọc khi**: Cài đặt lần đầu, bắt đầu develop, gặp lỗi

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
- Password: admin123

User:
- Username: user1
- Password: user123
```

**Expected**: Cửa sổ JavaFX hiển thị danh sách movies & showtimes ✅

---

## 🏗️ KIẾN TRÚC & DESIGN PATTERNS

### 3-Layer Architecture

```
┌─────────────────────────────────┐
│   PRESENTATION LAYER (UI)       │
│   - FXML files                  │
│   - Controllers                 │
│   - JavaFX components           │
└─────────────────────────────────┘
            ↓ calls
┌─────────────────────────────────┐
│   BUSINESS LAYER (Services)     │
│   - MovieService                │
│   - BookingService              │
│   - AuthService                 │
│   - SessionManager              │
└─────────────────────────────────┘
            ↓ calls
┌─────────────────────────────────┐
│   DATA LAYER (Repositories)     │
│   - UserRepo, MovieRepo         │
│   - ShowtimeRepo, BookingRepo   │
│   - Database connection pool    │
└─────────────────────────────────┘
            ↓ connects
┌─────────────────────────────────┐
│   PostgreSQL Database           │
│   - 10+ tables                  │
│   - Relationships & indexes     │
└─────────────────────────────────┘
```

### Design Patterns Applied

1. **Singleton**: Database connection pool, SessionManager, SessionTimer
2. **Template Method**: BaseRepo, BaseService
3. **Repository**: Data access abstraction
4. **MVC**: Model-View-Controller separation
5. **Dependency Injection**: Services injected vào controllers

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

## 🧪 TESTING

```bash
# Test database connection
psql -d cinema -c "SELECT COUNT(*) FROM api_movie;"

# Compile project
mvn clean compile

# Run tests
mvn test

# Run application
mvn javafx:run
```

---

## 📊 TIẾN ĐỘ DỰ ÁN: 85% ✅

### ✅ Hoàn thành (85%)

**Backend & Data Layer**:

- ✅ Database schema (10+ tables with relationships)
- ✅ Connection pool (HikariCP + Singleton)
- ✅ Domain models (7 entities)
- ✅ Repository layer (UserRepo, MovieRepo, ShowtimeRepo, BookingRepo, SeatRepo)
- ✅ Transaction utility (Tx.java)

**Business Logic Layer**:

- ✅ AuthService (Login/Register với Django password)
- ✅ SessionManager (10-minute booking timer, auto-logout)
- ✅ MovieService (CRUD movies với genres)
- ✅ ShowtimeService (CRUD showtimes với validation)
- ✅ BookingService (QR payment approval workflow)

**UI Layer**:

- ✅ Login/Register screens
- ✅ Dashboard (User & Admin views)
- ✅ Movie List (Search, filter)
- ✅ Showtime List
- ✅ Seat Map (Interactive selection với timer)
- ✅ Payment screen (QR/Cash với timer)
- ✅ Admin Panel (Movies, Users, Showtimes, Auditoriums, Bookings, QR Approval, Statistics)
- ✅ Profile Settings (Update info, change password, logout)
- ✅ My Bookings (View booking history)

### � Đang phát triển (15%)

- 🔄 Ticket QR Code generation
- 🔄 Seat availability real-time updates
- 🔄 Advanced statistics & reports
- 🔄 Email notifications
- 🔄 Export booking data to PDF/Excel

---

## 📁 CẤU TRÚC PROJECT

```
Event_ticket_system_OOP_PTIT/
├── src/main/
│   ├── java/com/ptit/ticketing/
│   │   ├── MainApp.java                    # ✅ Entry point
│   │   ├── config/
│   │   │   └── Database.java              # ✅ HikariCP connection pool
│   │   ├── domain/                         # ✅ 7 entities
│   │   │   ├── User.java
│   │   │   ├── Movie.java
│   │   │   ├── Showtime.java
│   │   │   ├── Auditorium.java
│   │   │   ├── Seat.java
│   │   │   ├── Booking.java
│   │   │   └── Ticket.java
│   │   ├── repo/                           # ✅ Data Access Layer
│   │   │   ├── BaseRepo.java
│   │   │   ├── UserRepo.java
│   │   │   ├── MovieRepo.java
│   │   │   ├── ShowtimeRepo.java
│   │   │   ├── BookingRepo.java
│   │   │   └── SeatRepo.java
│   │   ├── service/                        # ✅ Business Logic
│   │   │   ├── BaseService.java
│   │   │   ├── AuthService.java
│   │   │   ├── SessionManager.java
│   │   │   ├── SessionTimer.java
│   │   │   ├── MovieService.java
│   │   │   ├── ShowtimeService.java
│   │   │   └── BookingService.java
│   │   ├── ui/                             # ✅ Controllers
│   │   │   ├── LoginController.java
│   │   │   ├── RegisterController.java
│   │   │   ├── DashboardController.java
│   │   │   ├── MovieListController.java
│   │   │   ├── EventController.java
│   │   │   ├── SeatMapController.java
│   │   │   ├── PaymentController.java
│   │   │   ├── AdminPanelController.java
│   │   │   ├── ProfileSettingsController.java
│   │   │   └── MyBookingsController.java
│   │   ├── auth/
│   │   │   └── DjangoPassword.java        # ✅ PBKDF2-SHA256
│   │   └── util/
│   │       └── Tx.java                    # ✅ Transaction helper
│   └── resources/
│       ├── application.properties          # ✅ DB config
│       └── ui/                             # ✅ FXML files
│           ├── login.fxml
│           ├── register.fxml
│           ├── dashboard.fxml
│           ├── movie-list.fxml
│           ├── EventView.fxml
│           ├── SeatMap.fxml
│           ├── payment.fxml
│           ├── admin-panel.fxml
│           ├── profile-settings.fxml
│           └── my-bookings.fxml
├── database/
│   ├── cinema_dump.sql                     # ✅ Full database dump
│   ├── cinema_schema.sql                   # ✅ Schema only
│   └── README.md                           # ✅ Database setup guide
├── pom.xml                                 # ✅ Maven dependencies
├── README.md                               # ✅ Main documentation (this file)
├── OVERVIEW.md                             # ✅ Project overview
└── Chạy.md                                 # ✅ Development guide
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

## 🎯 TÍNH NĂNG CHI TIẾT

### 🔐 Authentication & Authorization

- Login với Django PBKDF2-SHA256 password verification
- Register tài khoản mới với validation
- Session management với auto-logout
- Role-based access (Admin/User)

### 👤 User Features

- **Dashboard**: Xem movies đang chiếu, upcoming showtimes
- **Movie List**: Search, filter phim theo thể loại
- **Booking Flow**:
  1. Chọn phim → Xem showtimes
  2. Chọn suất chiếu → Seat map interactive
  3. Chọn ghế (Standard/VIP/Couple) với timer 10 phút
  4. Thanh toán (QR Code/Cash)
  5. Nhận booking confirmation
- **My Bookings**: Xem lịch sử đặt vé
- **Profile**: Cập nhật thông tin, đổi password

### 👨‍💼 Admin Features

- **Movies Management**: CRUD phim với poster, thể loại, thời lượng
- **Showtimes Management**: Tạo/sửa/xóa lịch chiếu với validation trùng lịch
- **Auditoriums Management**: Quản lý phòng chiếu và cấu hình ghế
- **Users Management**: Xem và quản lý tài khoản
- **Bookings Management**: Xem chi tiết booking và tickets
- **QR Payment Approval**: Phê duyệt/từ chối thanh toán QR
- **Statistics**: Dashboard thống kê tổng quan

### ⏱️ Special Features

- **10-Minute Booking Timer**: Tự động hủy booking khi hết thời gian
- **QR Payment Workflow**: Pending → Admin Approval → Confirmed
- **Real-time Seat Updates**: Auto-refresh seat availability mỗi 5 giây
- **Session Timer**: Auto-logout khi inactive
- **Password Security**: Django-compatible PBKDF2 hashing

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

## 📚 DOCUMENTATION

- **README.md** (this file): Quick start & overview
- **[Chạy.md](Chạy.md)**: Detailed development guide
- **[OVERVIEW.md](OVERVIEW.md)**: Project architecture & patterns
- **[database/README.md](database/README.md)**: Database setup guide

## 🤝 CONTRIBUTING

1. Fork repository
2. Create feature branch: `git checkout -b feature/new-feature`
3. Commit changes: `git commit -m "Add new feature"`
4. Push to branch: `git push origin feature/new-feature`
5. Create Pull Request

## 📞 CONTACT & SUPPORT

- **Repository**: https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT
- **Issues**: [GitHub Issues](https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT/issues)

---

## 📝 LICENSE

Educational project - PTIT University

---

**Made with ❤️ by PTIT Students** | **Java 21 + JavaFX 22 + PostgreSQL 16**
