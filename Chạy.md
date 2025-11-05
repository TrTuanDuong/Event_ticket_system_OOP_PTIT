# 🚀 SETUP GUIDE - HƯỚNG DẪN CÀI ĐẶT NHANH

> **Thời gian**: 10 phút  
> **Độ khó**: ⭐⭐☆☆☆ (Dễ)  
> **Cập nhật**: Tháng 11/2025

---

## ✅ YÊU CẦU HỆ THỐNG

| Phần mềm       | Version | Cách check       |
| -------------- | ------- | ---------------- |
| **Java JDK**   | 21+     | `java -version`  |
| **Maven**      | 3.8+    | `mvn --version`  |
| **PostgreSQL** | 14+     | `psql --version` |

---

## 📦 CÀI ĐẶT NHANH (3 BƯỚC)

### Bước 1: Clone Repository

```bash
git clone https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT.git
cd Event_ticket_system_OOP_PTIT
```

### Bước 2: Setup Database (2 phút)

```bash
# Tạo database
createdb cinema

# Import data mẫu (đã có sẵn 6 phim, 160 showtimes, 2 users)
psql -d cinema < database/cinema_dump.sql
```

**Xác nhận thành công**:

```bash
psql -d cinema -c "SELECT COUNT(*) FROM api_movie;"
# Output: 6 ✅
```

### Bước 3: Configure & Run

```bash
# Sửa file cấu hình (nếu cần)
# src/main/resources/application.properties
# Đổi db.user và db.password theo PostgreSQL của bạn

# Build và chạy
mvn clean compile
mvn javafx:run
```

---

## 🔑 TÀI KHOẢN MẶC ĐỊNH

### Admin

```
Username: admin
Password: admin
```

### User

```
Username: test
Password: 123456
```

---

## 🎬 DEMO WORKFLOW

### User Flow

1. Login với `test / 123456`
2. Dashboard → Xem phim đang chiếu
3. Click phim → Xem showtimes
4. Chọn suất chiếu → Seat map interactive
5. Chọn ghế (Standard/VIP/Couple)
6. Timer 10 phút bắt đầu đếm ngược
7. Click "Tiếp tục" → Payment screen
8. Chọn QR Code hoặc Cash → Confirm
9. QR: Chờ admin duyệt | Cash: Paid ngay

### Admin Flow

1. Login với `admin / admin`
2. Click "Admin Panel"
3. Tab "📽️ Quản lý Phim" → CRUD movies
4. Tab "🎬 Quản lý Suất chiếu" → Tạo/sửa/xóa showtimes
5. Tab "🏛️ Quản lý Phòng" → Quản lý auditoriums
6. Tab "💳 Phê duyệt QR" → Approve/Reject QR payments
7. Tab "📊 Thống kê" → Xem tổng quan hệ thống

---

## 🗄️ DATABASE

### Tables có sẵn

- `api_user`: 2 users (1 admin + 1 user)
- `api_movie`: 6 phim mẫu (The Dark Knight, Inception, Interstellar...)
- `api_showtime`: 160 suất chiếu (7 ngày tới)
- `api_auditorium`: 3 phòng chiếu
- `api_seat`: ~300 ghế (Standard/VIP/Couple)
- `api_booking`: Booking records
- `api_ticket`: Ticket records

### Xem dữ liệu

```bash
# Connect database
psql -d cinema

# Xem movies
SELECT title, duration_min, rating FROM api_movie;

# Xem showtimes
SELECT s.id, m.title, a.name, s.start_time
FROM api_showtime s
JOIN api_movie m ON s.movie_id = m.id
JOIN api_auditorium a ON s.auditorium_id = a.id
LIMIT 10;

# Exit
\q
```

---

## ❌ GẶP LỖI?

### "Connection refused"

```bash
# Check PostgreSQL running
brew services list | grep postgresql

# Start nếu chưa chạy
brew services start postgresql@16
```

### "Database does not exist"

```bash
createdb cinema
psql -d cinema < database/cinema_dump.sql
```

### "Password authentication failed"

Sửa `src/main/resources/application.properties`:

```properties
db.user=postgres
db.password=your_password_here
```

### "Maven not found"

```bash
# macOS
brew install maven

# Windows: Download từ
# https://maven.apache.org/download.cgi
```

### "Java version error"

```bash
# Cần Java 21+
brew install openjdk@21

# Verify
java -version
```

---

## 🔄 RESET DATABASE

```bash
# Drop và tạo lại
dropdb cinema
createdb cinema
psql -d cinema < database/cinema_dump.sql
```

---

## 📱 SCREENSHOTS

### Login Screen

- Username/Password fields
- Login button
- Register link

### Dashboard

- Welcome message với username
- Movies đang chiếu (grid layout)
- Quick actions

### Seat Map

- Interactive seat selection
- Color coding: Green=Available, Red=Booked, Blue=Selected
- Timer 10 phút countdown
- Price calculation

### Admin Panel

- 7 tabs: Movies, Users, Showtimes, Auditoriums, Bookings, QR Approval, Statistics
- Full CRUD operations
- Search & filter
- Statistics dashboard

---

## 📚 NEXT STEPS

1. ✅ Chạy được application
2. ✅ Login thành công (`test/123456` hoặc `admin/admin`)
3. ✅ Test booking flow (chọn phim → showtime → ghế → thanh toán)
4. ✅ Test admin panel (CRUD movies, showtimes, duyệt QR)
5. → Đọc [README.md](README.md) để hiểu đầy đủ features & architecture

---

## 🆘 SUPPORT

- **Repository**: [GitHub](https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT)
- **Issues**: [GitHub Issues](https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT/issues)

---

**🎓 Dự án OOP - PTIT**  
**Made with ❤️ by CODESEAT** | **Setup in 10 minutes!** ⚡
