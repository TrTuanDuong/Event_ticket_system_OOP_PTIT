# 🎬 Cinema Ticket Management System - JavaFX Edition

> **Dự án OOP - PTIT**  
> **Tech Stack**: Java 21 + JavaFX 22 + PostgreSQL 16 + Maven  
> **Team**: 4 members | **Timeline**: 10 days | **Progress**: 40% ✅

Ứng dụng quản lý rạp chiếu phim desktop sử dụng JavaFX, kết nối **trực tiếp** với PostgreSQL database.

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

### 1. Đọc documentation (15 phút)

```bash
# Đọc overview trước
open PROJECT_OVERVIEW.md

# Sau đó đọc development guide
open DEVELOPMENT_GUIDE.md
```

### 2. Cài đặt môi trường (30 phút)

```bash
# Check requirements
java -version    # Need Java 21+
mvn --version    # Need Maven 3.8+
psql --version   # Need PostgreSQL 14+

# Install Maven (nếu chưa có)
brew install maven
```

### 3. Setup database (10 phút)

```bash
# Create database
psql -U postgres -c "CREATE DATABASE cinema;"

# Run Django migrations (tạo tables)
cd "../Bản sao BTL_CSDL_PTIT/backend"
source venv/bin/activate
python manage.py migrate
python manage.py seed_data
```

### 4. Chạy dự án (2 phút)

```bash
cd eventfx

# Compile & Run
mvn clean compile
mvn javafx:run
```

**Expected**: Cửa sổ JavaFX hiển thị danh sách movies & showtimes ✅

---

## 🧪 TEST NHANH

```bash
# Test database connection (1 command)
bash test-db.sh

# Test Java code
mvn exec:java -Dexec.mainClass="com.ptit.ticketing.TestConnection"
```

---

## 📊 TIẾN ĐỘ DỰ ÁN: 40% ✅

**Đã hoàn thành**: Database (10+ tables), Connection Pool, Domain Models (7 entities), Repositories (4 repos), Authentication, Test UI

**Đang làm (Day 4-6)**: SessionManager, MovieService, ShowtimeService, Login UI, Dashboard

**Chi tiết đầy đủ** → Xem [PROJECT_OVERVIEW.md](PROJECT_OVERVIEW.md) phần **"📊 CHI TIẾT CÁC THÀNH PHẦN ĐÃ HOÀN THÀNH"**

---

## 📁 CẤU TRÚC PROJECT (Tóm tắt)

```
eventfx/
├── src/main/
│   ├── java/com/ptit/ticketing/
│   │   ├── App.java                 # Entry point ✅
│   │   ├── config/Database.java     # HikariCP connection pool ✅
│   │   ├── domain/                  # 7 entities ✅
│   │   ├── repo/                    # 4 repositories ✅
│   │   ├── service/                 # 2/7 services 🚧
│   │   ├── auth/                    # Django password verification ✅
│   │   └── util/Tx.java            # Transaction helper ✅
│   └── resources/
│       ├── application.properties   # DB config ✅
│       └── ui/                      # FXML files (TODO) ❌
├── test-db.sh                       # Database test script ✅
└── pom.xml                          # Maven config ✅
```

**Chi tiết đầy đủ** → Xem [PROJECT_OVERVIEW.md](PROJECT_OVERVIEW.md) phần **"📁 CẤU TRÚC DỰ ÁN"**

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

## 🎯 NEXT STEPS (Member A - Day 4)

1. **SessionManager.java** (1h) - Quản lý user session
2. **MovieService.java** (2h) - Business logic cho movies
3. **ShowtimeService.java** (2h) - Business logic cho showtimes

**Hướng dẫn develop chi tiết với code templates** → Xem [DEVELOPMENT_GUIDE.md](DEVELOPMENT_GUIDE.md) phần **"💡 HƯỚNG DẪN DEVELOP"**

---

## 📝 LICENSE

Educational project - PTIT University
