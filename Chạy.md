# 🚀 DEVELOPMENT GUIDE - HƯỚNG DẪN PHÁT TRIỂN

> **Dành cho**: All team members  
> **Mục đích**: Setup, run, test và develop dự án  
> **Prerequisites**: Đã đọc `PROJECT_OVERVIEW.md`

---

## 📋 MỤC LỤC

1. [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
2. [Cài đặt môi trường](#️-cài-đặt-môi-trường)
3. [Setup dự án](#-setup-dự-án)
4. [Chạy dự án](#-chạy-dự-án)
5. [Test dự án](#-test-dự-án)
6. [Sử dụng dữ liệu](#-sử-dụng-dữ-liệu)
7. [Công việc đã hoàn thành](#-công-việc-đã-hoàn-thành)
8. [Hướng dẫn develop](#-hướng-dẫn-develop)
9. [Troubleshooting](#-troubleshooting)
10. [Git Workflow](#-git-workflow)

---

## 💻 YÊU CẦU HỆ THỐNG

### Bắt buộc

| Phần mềm | Version tối thiểu | Cách check |
|----------|-------------------|------------|
| **Java JDK** | 21+ (LTS) | `java -version` |
| **Maven** | 3.8+ | `mvn --version` |
| **PostgreSQL** | 14+ | `psql --version` |
| **Git** | 2.0+ | `git --version` |

### Khuyến nghị

| Tool | Purpose |
|------|---------|
| **IntelliJ IDEA** / **VS Code** | IDE with Java support |
| **Scene Builder** | Visual FXML editor |
| **pgAdmin** / **DBeaver** | Database management |
| **Postman** | API testing (optional) |

---

## 🛠️ CÀI ĐẶT MÔI TRƯỜNG

### Bước 1: Cài đặt Java 21

#### macOS (Homebrew):
```bash
# Cài OpenJDK 21
brew install openjdk@21

# Add to PATH
echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc

# Verify
java -version
```

#### Windows:
1. Download: https://adoptium.net/temurin/releases/?version=21
2. Install MSI file
3. Verify: `java -version` trong Command Prompt

#### Linux:
```bash
sudo apt update
sudo apt install openjdk-21-jdk
java -version
```

---

### Bước 2: Cài đặt Maven

#### macOS:

**Option A: Homebrew (Khuyến nghị)**
```bash
# Install Homebrew (nếu chưa có)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Maven
brew install maven

# Verify
mvn --version
```

**Option B: Script tự động**
```bash
cd eventfx
bash install-maven.sh
```

**Option C: Manual Download**
```bash
# Download Maven
cd ~
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz

# Extract
tar -xzf apache-maven-3.9.9-bin.tar.gz

# Add to PATH
echo 'export PATH=$HOME/apache-maven-3.9.9/bin:$PATH' >> ~/.zshrc
source ~/.zshrc

# Verify
mvn --version
```

#### Windows:
1. Download: https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH:
   - System Properties → Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
4. Verify: `mvn --version`

#### Linux:
```bash
sudo apt update
sudo apt install maven
mvn --version
```

---

### Bước 3: Cài đặt PostgreSQL

#### macOS:
```bash
# Install PostgreSQL
brew install postgresql@16

# Start service
brew services start postgresql@16

# Verify
psql --version
```

#### Windows:
1. Download: https://www.postgresql.org/download/windows/
2. Install EDB installer
3. Remember password cho postgres user
4. Start PostgreSQL service

#### Linux:
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

---

### Bước 4: Clone Repository

```bash
# Clone project
git clone https://github.com/TrTuanDuong/Event_ticket_system_OOP_PTIT.git

# Navigate to JavaFX project
cd Event_ticket_system_OOP_PTIT/eventfx

# Check structure
ls -la
```

---

## 🔧 SETUP DỰ ÁN

### Bước 1: Setup Database

#### 1.1. Create Database

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE cinema;

# Create user (nếu chưa có)
CREATE USER trantuanduong WITH PASSWORD '';

# Grant permissions
GRANT ALL PRIVILEGES ON DATABASE cinema TO trantuanduong;

# Exit
\q
```

#### 1.2. Run Django Migrations (để tạo tables)

**Option A: Có Django backend**
```bash
# Navigate to Django backend
cd "../Bản sao BTL_CSDL_PTIT/backend"

# Activate virtual environment
source venv/bin/activate  # macOS/Linux
# hoặc
venv\Scripts\activate     # Windows

# Run migrations
python manage.py migrate

# Seed sample data
python manage.py seed_data

# Verify tables
psql -U trantuanduong -d cinema -c "\dt api_*"
```

**Option B: Không có Django backend (Import SQL)**
```bash
# If có file SQL dump
psql -U trantuanduong -d cinema < database_dump.sql

# Verify
psql -U trantuanduong -d cinema -c "SELECT COUNT(*) FROM api_movie;"
```

---

### Bước 2: Configure Application

#### 2.1. Check `application.properties`

File: `src/main/resources/application.properties`

```properties
# Database Configuration
db.url=jdbc:postgresql://localhost:5432/cinema
db.user=trantuanduong
db.password=
db.poolSize=10
db.schema=public

# Application Settings
app.name=Cinema Ticket Management System
booking.expiry.minutes=10
```

**Chỉnh sửa nếu cần**:
- `db.user`: Username PostgreSQL của bạn
- `db.password`: Password (nếu có)
- `db.url`: Đổi `localhost` nếu database ở máy khác

---

### Bước 3: Install Dependencies

```bash
cd eventfx

# Download dependencies
mvn clean install

# Hoặc chỉ resolve dependencies
mvn dependency:resolve
```

**Expected output**:
```
[INFO] BUILD SUCCESS
[INFO] Total time: 30 s
```

---

### Bước 4: Verify Setup

```bash
# Compile project
mvn clean compile

# Check for errors
echo $?  # Should return 0 if success
```

---

## ▶️ CHẠY DỰ ÁN

### Option 1: Maven Command (Khuyến nghị)

```bash
cd eventfx

# Run application
mvn javafx:run
```

**Expected**: Cửa sổ JavaFX hiển thị danh sách movies & showtimes

---

### Option 2: IDE (IntelliJ IDEA)

1. Open project: `File → Open → Select eventfx/pom.xml`
2. Wait for Maven import
3. Right-click `MainApp.java` → Run 'MainApp.main()'

---

### Option 3: IDE (VS Code)

1. Open folder: `eventfx`
2. Install extensions:
   - Extension Pack for Java
   - Maven for Java
3. Open `MainApp.java`
4. Click "Run" button above `main()` method

---

### Option 4: Build JAR file

```bash
# Build executable JAR
mvn clean package

# Run JAR
java -jar target/eventfx-1.0.0.jar
```

---

## 🧪 TEST DỰ ÁN

### Test 1: Database Connection (Terminal)

#### Quick Test (1 command)
```bash
psql -U trantuanduong -d cinema -c "SELECT COUNT(*) FROM api_movie;"
```

**Expected output**: Số lượng movies (ví dụ: 6)

#### Full Test (Script)
```bash
cd eventfx
bash test-db.sh
```

**Expected output**:
```
============================================================
🔍 KIỂM TRA KẾT NỐI DATABASE
============================================================

📌 Test 1: PostgreSQL Service
✅ PostgreSQL đang chạy

📌 Test 2: Database Exists
✅ Database 'cinema' tồn tại

📌 Test 3: Connection Test
✅ Kết nối thành công

📌 Test 4: Database Tables
✅ Tìm thấy 12 bảng (api_*)

📌 Test 5: Data Check
   Users      : 2
   Movies     : 6
   Showtimes  : 160
   Bookings   : 1
   Tickets    : 1

📌 Test 6: Admin Account
⚠️  Không có admin account (cần tạo)

📌 Test 7: Sample Movie Data
      title      | duration_min | rating 
-----------------+--------------+--------
 The Dark Knight |          152 | PG-13
 Inception       |          148 | PG-13
 Interstellar    |          169 | PG-13

============================================================
🎉 TẤT CẢ TESTS PASS!
============================================================
```

---

### Test 2: Database Connection (Java)

```bash
cd eventfx

# Compile
mvn clean compile

# Run test class
mvn exec:java -Dexec.mainClass="com.ptit.ticketing.TestConnection"
```

**Expected output**:
```
============================================================
🔍 KIỂM TRA KẾT NỐI DATABASE
============================================================

📌 Test 1: Database Singleton
✅ Database singleton initialized

📌 Test 2: DataSource
✅ DataSource obtained

📌 Test 3: Connection Pool
✅ Connection opened: jdbc:postgresql://localhost:5432/cinema
   Database: PostgreSQL
   Version: 16.x

📌 Test 4: Direct Query
✅ Users: 2
✅ Movies: 6
✅ Showtimes: 160

📌 Test 5: Repository Pattern
✅ UserRepo.findAll(): 2 users
✅ MovieRepo.findAll(): 6 movies
✅ ShowtimeRepo.findUpcoming(): 160 showtimes

📌 Test 6: Sample Data
✅ Sample Movie:
   ID: <uuid>
   Title: The Dark Knight
   Duration: 152 mins

📌 Test 7: Transaction Management
✅ Transaction successful

============================================================
🎉 TẤT CẢ TESTS ĐỀU PASS!
============================================================
```

---

### Test 3: Application Run

```bash
mvn javafx:run
```

**Check**:
- [ ] Cửa sổ JavaFX mở ra
- [ ] Hiển thị "Movies in System"
- [ ] Hiển thị danh sách movies
- [ ] Hiển thị "Upcoming Showtimes"
- [ ] Hiển thị danh sách showtimes
- [ ] Không có error trong console

---

### Test 4: Unit Tests (Optional)

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=AppTest

# With coverage
mvn clean test jacoco:report
```

---

## 📊 SỬ DỤNG DỮ LIỆU

### Dữ liệu có sẵn

Sau khi run Django `seed_data`, database có:

| Entity | Count | Description |
|--------|-------|-------------|
| Users | 2 | 1 admin + 1 user |
| Movies | 6 | Sample movies |
| Genres | ~10 | Action, Drama, Sci-Fi, etc. |
| Auditoriums | ~3 | Hall A, B, C |
| Seats | ~100 per hall | Standard, VIP, Couple |
| Showtimes | 160 | Next 7 days |
| Bookings | 1 | Sample booking |
| Tickets | 1 | Sample ticket |

---

### Xem dữ liệu

#### View trong Terminal

```bash
# Connect to database
psql -U trantuanduong -d cinema

# List all tables
\dt api_*

# View movies
SELECT id, title, duration_min, rating FROM api_movie;

# View users
SELECT id, username, email, role FROM api_user;

# View showtimes (với JOIN)
SELECT 
  s.id,
  m.title as movie,
  a.name as auditorium,
  s.start_time,
  s.base_price
FROM api_showtime s
JOIN api_movie m ON s.movie_id = m.id
JOIN api_auditorium a ON s.auditorium_id = a.id
LIMIT 10;

# Exit
\q
```

#### View trong GUI (pgAdmin/DBeaver)

1. Connect to `cinema` database
2. Browse tables: `api_*`
3. Right-click table → View Data

---

### Thêm dữ liệu mới

#### Option A: Django Admin

```bash
cd backend
python manage.py runserver

# Open browser: http://localhost:8000/admin/
# Login với superuser
# Add movies, showtimes, etc.
```

#### Option B: SQL Insert

```bash
psql -U trantuanduong -d cinema
```

```sql
-- Thêm movie mới
INSERT INTO api_movie (id, title, duration_min, rating, release_date, description)
VALUES (
  gen_random_uuid(),
  'Avatar 2',
  192,
  'PG-13',
  '2022-12-16',
  'Return to Pandora'
);

-- Thêm showtime
INSERT INTO api_showtime (id, movie_id, auditorium_id, start_time, end_time, base_price, status)
VALUES (
  gen_random_uuid(),
  (SELECT id FROM api_movie WHERE title='Avatar 2'),
  (SELECT id FROM api_auditorium WHERE name='Hall A'),
  '2025-10-20 19:00:00+07',
  '2025-10-20 22:15:00+07',
  100000,
  'active'
);
```

#### Option C: JavaFX Application (sau khi implement CRUD UI)

```
1. Login as admin
2. Navigate to "Movies" → "Add New Movie"
3. Fill form → Save
```

---

### Tạo Admin Account

**Cách 1: Django**
```bash
cd backend
python manage.py createsuperuser

# Username: admin
# Email: admin@example.com
# Password: admin123 (nhập 2 lần)
```

**Cách 2: SQL**
```sql
-- Generate Django password hash
-- Password: admin123
-- Hash: pbkdf2_sha256$260000$YourSaltHere$YourHashHere

-- Insert admin user
INSERT INTO api_user (id, username, email, password, role, created_at)
VALUES (
  gen_random_uuid(),
  'admin',
  'admin@example.com',
  'pbkdf2_sha256$260000$...',  -- Django password hash
  'admin',
  NOW()
);
```

**Cách 3: Python Script**
```python
# create_admin.py
from django.contrib.auth.hashers import make_password
import psycopg2
import uuid

conn = psycopg2.connect("dbname=cinema user=trantuanduong")
cur = conn.cursor()

password_hash = make_password('admin123')

cur.execute("""
  INSERT INTO api_user (id, username, email, password, role, created_at)
  VALUES (%s, %s, %s, %s, %s, NOW())
""", (str(uuid.uuid4()), 'admin', 'admin@example.com', password_hash, 'admin'))

conn.commit()
cur.close()
conn.close()
print("Admin created!")
```

---

### Reset Database

```bash
# Drop và recreate database
psql -U postgres -c "DROP DATABASE cinema;"
psql -U postgres -c "CREATE DATABASE cinema;"
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE cinema TO trantuanduong;"

# Run migrations lại
cd backend
python manage.py migrate
python manage.py seed_data
```

---

## ✅ CÔNG VIỆC ĐÃ HOÀN THÀNH

### Phase 1: Foundation (100% - Member A)

#### 1. Database & Schema ✅

**Files**:
- Django migrations (backend/)
- 10+ tables với relationships
- Indexes & constraints

**Verification**:
```bash
psql -U trantuanduong -d cinema -c "\dt api_*"
# Should show 12 tables
```

---

#### 2. Connection Pool ✅

**File**: `src/main/java/com/ptit/ticketing/config/Database.java`

**Features**:
- Singleton pattern
- HikariCP connection pool
- Configuration từ properties file
- Thread-safe

**Test**:
```java
Database db = Database.get();  // Singleton instance
DataSource ds = db.ds();       // Get connection pool
```

---

#### 3. Domain Models ✅

**Files**: `src/main/java/com/ptit/ticketing/domain/*.java`

| Entity | File | Django Table | Status |
|--------|------|--------------|--------|
| User | User.java | api_user | ✅ |
| Movie | Movie.java | api_movie | ✅ |
| Auditorium | Auditorium.java | api_auditorium | ✅ |
| Seat | Seat.java | api_seat | ✅ |
| Showtime | Showtime.java | api_showtime | ✅ |
| Booking | Booking.java | api_booking | ✅ |
| Ticket | Ticket.java | api_ticket | ✅ |

**Features**:
- UUID primary keys
- OffsetDateTime (timezone-aware)
- BigDecimal for money
- Business methods (isAdmin(), isExpired(), etc.)

---

#### 4. Repository Layer ✅

**Files**: `src/main/java/com/ptit/ticketing/repo/*.java`

| Repository | Methods | Status |
|------------|---------|--------|
| BaseRepo | Template Method base class | ✅ |
| UserRepo | findByUsername, findAll, findByRole | ✅ |
| MovieRepo | Full CRUD + search | ✅ |
| ShowtimeRepo | findUpcoming + JOIN queries | ✅ |
| BookingRepo | findAll, cleanupExpired | ✅ |

**Features**:
- Template Method pattern
- Parameterized queries
- Optional<T> return types
- Exception handling

**Test**:
```java
DataSource ds = Database.get().ds();
List<Movie> movies = Tx.withTx(ds, conn -> {
  MovieRepo repo = new MovieRepo(ds);
  return repo.findAll(conn);
});
```

---

#### 5. Transaction Utility ✅

**File**: `src/main/java/com/ptit/ticketing/util/Tx.java`

**Features**:
- Functional programming style
- Auto commit/rollback
- Generic types
- Resource management

**Usage**:
```java
// Execute query in transaction
String result = Tx.withTx(ds, conn -> {
  // Your database operations
  return "Success";
});
```

---

#### 6. Authentication ✅

**Files**:
- `src/main/java/com/ptit/ticketing/auth/DjangoPassword.java`
- `src/main/java/com/ptit/ticketing/service/AuthService.java`
- `src/main/java/com/ptit/ticketing/service/BaseService.java`

**Features**:
- Django PBKDF2-SHA256 compatible
- Constant-time comparison
- Login verification

**Usage**:
```java
AuthService auth = new AuthService(ds);
Optional<User> user = auth.login("admin", "admin123");
if (user.isPresent()) {
  System.out.println("Login success: " + user.get().getUsername());
}
```

---

#### 7. Test UI ✅

**File**: `src/main/java/com/ptit/ticketing/MainApp.java`

**Features**:
- JavaFX Application
- Display movies from database
- Display showtimes from database
- ListView components

**Run**:
```bash
mvn javafx:run
```

---

#### 8. Documentation ✅

**Files created**:
- `PROJECT_OVERVIEW.md` - Tổng quan dự án
- `DEVELOPMENT_GUIDE.md` - This file
- `README.md` - Quick start
- `test-db.sh` - Database test script
- `install-maven.sh` - Maven install script

---

### Summary: 40% Project Complete

**Completed**:
- ✅ Database (100%)
- ✅ Connection Pool (100%)
- ✅ Domain Models (100%)
- ✅ Repositories (100%)
- ✅ Transaction Util (100%)
- ✅ Authentication (100%)
- ✅ Test UI (100%)
- ✅ Documentation (100%)

**Remaining**:
- ❌ Session Management (0%)
- ❌ Service Layer (30% - only 2/7 services)
- ❌ FXML UI (0%)
- ❌ Business Logic (0%)
- ❌ Advanced Features (0%)

---

## 💡 HƯỚNG DẪN DEVELOP

### Workflow chung

1. **Pull latest code**
   ```bash
   git pull origin main
   ```

2. **Create feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Develop & Test**
   ```bash
   # Make changes
   # ...
   
   # Compile
   mvn clean compile
   
   # Run
   mvn javafx:run
   
   # Test
   mvn test
   ```

4. **Commit & Push**
   ```bash
   git add .
   git commit -m "Add: your feature description"
   git push origin feature/your-feature-name
   ```

5. **Create Pull Request**
   - Go to GitHub
   - Create PR from your branch → main
   - Request review từ Member A

---

### Develop Services (Member A, B, C, D)

#### Step 1: Create Service class

**Template**: `src/main/java/com/ptit/ticketing/service/YourService.java`

```java
package com.ptit.ticketing.service;

import com.ptit.ticketing.repo.YourRepo;
import com.ptit.ticketing.util.Tx;
import javax.sql.DataSource;

public class YourService extends BaseService {
    private final YourRepo repo;
    
    public YourService(DataSource ds) {
        super(ds);
        this.repo = new YourRepo(ds);
    }
    
    // Business methods
    public List<YourEntity> getAll() {
        return Tx.withTx(ds, conn -> repo.findAll(conn));
    }
    
    public Optional<YourEntity> getById(UUID id) {
        return Tx.withTx(ds, conn -> repo.findById(conn, id));
    }
    
    // Add business logic methods...
}
```

#### Step 2: Test Service

```java
// In MainApp.java or test class
DataSource ds = Database.get().ds();
YourService service = new YourService(ds);

List<YourEntity> items = service.getAll();
items.forEach(System.out::println);
```

---

### Develop UI (Member B, C, D)

#### Step 1: Create FXML file

**Location**: `src/main/resources/ui/your-screen.fxml`

**Template**:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox xmlns:fx="http://javafx.com/fxml/1"
      xmlns="http://javafx.com/javafx/21"
      fx:controller="com.ptit.ticketing.ui.YourController"
      spacing="10"
      padding="20">
    
    <Label text="Your Screen Title" style="-fx-font-size: 24px;"/>
    
    <!-- Add your UI components here -->
    
</VBox>
```

#### Step 2: Create Controller

**Location**: `src/main/java/com/ptit/ticketing/ui/YourController.java`

**Template**:
```java
package com.ptit.ticketing.ui;

import com.ptit.ticketing.service.YourService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class YourController {
    @FXML private Label titleLabel;
    @FXML private ListView<String> itemList;
    @FXML private Button actionButton;
    
    private YourService service;
    
    @FXML
    public void initialize() {
        // Initialize service
        service = new YourService(Database.get().ds());
        
        // Load data
        loadData();
    }
    
    private void loadData() {
        // Fetch data from service
        List<YourEntity> items = service.getAll();
        
        // Update UI
        itemList.getItems().clear();
        items.forEach(item -> itemList.getItems().add(item.toString()));
    }
    
    @FXML
    private void handleAction() {
        // Handle button click
    }
}
```

#### Step 3: Navigate to your screen

```java
// In another controller or MainApp
FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/your-screen.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root, 800, 600);
stage.setScene(scene);
stage.show();
```

---

### Develop Repositories (Member B, C, D)

**Template**: `src/main/java/com/ptit/ticketing/repo/YourRepo.java`

```java
package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.YourEntity;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class YourRepo extends BaseRepo {
    public YourRepo(DataSource ds) {
        super(ds);
    }
    
    public List<YourEntity> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM api_your_table ORDER BY created_at DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            List<YourEntity> results = new ArrayList<>();
            while (rs.next()) {
                results.add(mapRow(rs));
            }
            return results;
        }
    }
    
    public Optional<YourEntity> findById(Connection conn, UUID id) throws SQLException {
        String sql = "SELECT * FROM api_your_table WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }
    
    private YourEntity mapRow(ResultSet rs) throws SQLException {
        YourEntity entity = new YourEntity();
        entity.setId((UUID) rs.getObject("id"));
        entity.setName(rs.getString("name"));
        // Map other fields...
        return entity;
    }
}
```

---

## 🆘 TROUBLESHOOTING

### Problem: "mvn: command not found"

**Cause**: Maven chưa cài hoặc chưa có trong PATH

**Solution**:
```bash
# macOS
brew install maven

# Or run install script
bash install-maven.sh

# Verify
mvn --version
```

---

### Problem: "java: error: invalid target release: 21"

**Cause**: Java version sai

**Solution**:
```bash
# Check Java version
java -version

# Should be 21+
# If not, install Java 21:
brew install openjdk@21

# Set JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

---

### Problem: "Connection refused" khi run app

**Cause**: PostgreSQL không chạy

**Solution**:
```bash
# macOS
brew services start postgresql@16

# Linux
sudo systemctl start postgresql

# Windows
# Start "PostgreSQL" service in Services

# Verify
pg_isready
```

---

### Problem: "relation api_movie does not exist"

**Cause**: Database chưa có tables

**Solution**:
```bash
# Run Django migrations
cd backend
python manage.py migrate
python manage.py seed_data
```

---

### Problem: "No admin account" khi test

**Cause**: Chưa tạo admin user

**Solution**:
```bash
cd backend
python manage.py createsuperuser
```

---

### Problem: FXML not found

**Cause**: File path sai

**Solution**:
```java
// Correct path (with leading slash)
FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));

// NOT: getClass().getResource("ui/login.fxml")
```

---

### Problem: JavaFX runtime components missing

**Cause**: JavaFX không được include

**Solution**:
```bash
# Clean và rebuild
mvn clean install -U

# Or update pom.xml với correct JavaFX version
```

---

### Problem: HikariCP connection timeout

**Cause**: Database config sai

**Solution**:
```properties
# Check application.properties
db.url=jdbc:postgresql://localhost:5432/cinema
db.user=trantuanduong
db.password=

# Test connection manually
psql -U trantuanduong -d cinema -c "SELECT 1"
```

---

## 🔄 GIT WORKFLOW

### Branch Strategy

```
main (production-ready)
  ├─ feature/member-a-login
  ├─ feature/member-b-movie-ui
  ├─ feature/member-c-booking
  └─ feature/member-d-reports
```

### Workflow

1. **Always start from main**
   ```bash
   git checkout main
   git pull origin main
   ```

2. **Create feature branch**
   ```bash
   git checkout -b feature/your-feature
   ```

3. **Work on feature**
   ```bash
   # Edit files...
   mvn clean compile
   mvn javafx:run
   ```

4. **Commit often**
   ```bash
   git add .
   git commit -m "Add: feature description"
   ```

5. **Push to GitHub**
   ```bash
   git push origin feature/your-feature
   ```

6. **Create Pull Request**
   - Go to GitHub repository
   - Click "New Pull Request"
   - Select your branch → main
   - Add description
   - Request review từ Member A

7. **After PR merged**
   ```bash
   git checkout main
   git pull origin main
   git branch -d feature/your-feature
   ```

### Commit Message Convention

```
Add: Thêm feature mới
Fix: Sửa bug
Update: Cập nhật existing feature
Refactor: Refactor code
Docs: Update documentation
Test: Add/update tests
Style: Format code
```

**Examples**:
```bash
git commit -m "Add: Login UI with FXML"
git commit -m "Fix: Database connection timeout"
git commit -m "Update: MovieService with search functionality"
```

---

## 📞 LIÊN HỆ HỖ TRỢ

### Member A (Technical Leader)
- Database issues
- Architecture questions
- Integration problems
- Code reviews

### Các thành viên khác
- Member B: UI issues
- Member C: Business logic
- Member D: Advanced features

### Resources
- GitHub Issues: Report bugs
- Project Wiki: Additional docs
- Team Chat: Quick questions

---

## 🎯 NEXT STEPS

### For All Members

1. **Setup environment** (Day 1)
   - [ ] Install Java 21
   - [ ] Install Maven
   - [ ] Install PostgreSQL
   - [ ] Clone repository
   - [ ] Run tests

2. **Understand foundation** (Day 1-2)
   - [ ] Read PROJECT_OVERVIEW.md
   - [ ] Study existing code (domain, repo, service)
   - [ ] Run application
   - [ ] Explore database

3. **Start developing** (Day 3+)
   - [ ] Pick your assigned tasks
   - [ ] Create feature branch
   - [ ] Implement features
   - [ ] Test thoroughly
   - [ ] Create PR

---

## 🎉 CONCLUSION

Sau khi follow guide này, bạn có thể:

✅ Setup môi trường development  
✅ Chạy được dự án  
✅ Test database connection  
✅ Hiểu cấu trúc code  
✅ Sử dụng dữ liệu  
✅ Bắt đầu develop features  

**Happy coding! 🚀**

---

*Document Version: 1.0*  
*Last Updated: 17/10/2025*  
*Maintainer: Member A (Technical Leader)*
