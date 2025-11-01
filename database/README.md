# 🗄️ DATABASE SETUP GUIDE

> **Thời gian setup**: 2 phút ⚡  
> **File sử dụng**: `cinema_dump.sql` (có sẵn trong repo)  
> **Cập nhật**: Tháng 11/2025

---

## 📌 QUICK START (2 BƯỚC)

### Bước 1: Tạo database

```bash
createdb cinema
```

### Bước 2: Import dump file

```bash
psql -d cinema < database/cinema_dump.sql
```

**Xác nhận thành công**:

```bash
psql -d cinema -c "SELECT COUNT(*) FROM api_movie;"
# Output: 6 ✅
```

**Done!** Database đã sẵn sàng với:

- ✅ 10+ tables
- ✅ 6 movies mẫu
- ✅ 160 showtimes
- ✅ 2 users (admin + user1)
- ✅ 3 auditoriums
- ✅ All relationships & indexes

---

## ⚙️ CẤU HÌNH APP

Sửa file `src/main/resources/application.properties`:

```properties
db.url=jdbc:postgresql://localhost:5432/cinema
db.user=postgres              # ← ĐỔI THÀNH USERNAME CỦA BẠN
db.password=your_password     # ← ĐỔI THÀNH PASSWORD CỦA BẠN
db.poolSize=10
db.schema=public
```

**Ví dụ**:

```properties
# macOS (Homebrew - không password)
db.user=trantuanduong
db.password=

# Windows (có password)
db.user=postgres
db.password=admin123
```

---

## ✅ TEST KẾT NỐI

```bash
cd eventfx
mvn clean compile
mvn javafx:run
```

**Expected**: Cửa sổ JavaFX hiển thị danh sách movies ✅

---

## 📊 DATABASE CÓ GÌ?

| Table        | Records | Mô tả               |
| ------------ | ------- | ------------------- |
| api_user     | 2       | Admin + user1       |
| api_movie    | 6       | Phim mẫu            |
| api_showtime | 160     | Lịch chiếu (7 ngày) |
| api_booking  | 1       | Booking mẫu         |
| api_ticket   | 1       | Vé mẫu              |

### 🔑 Login mặc định

```
Admin:
- Username: admin
- Password: admin123

User:
- Username: user1
- Password: user123
```

---

## 🛠️ GẶP LỖI?

### ❌ "database does not exist"

```bash
createdb cinema
```

---

### ❌ "role does not exist"

```bash
createuser -U postgres <your_username>
```

---

### ❌ "password authentication failed"

**Cách 1**: Đổi password

```bash
psql -U postgres
ALTER USER postgres PASSWORD 'new_password';
```

**Cách 2**: Sửa `pg_hba.conf` (development only)

```bash
# macOS
nano /opt/homebrew/var/postgresql@16/pg_hba.conf

# Đổi dòng này:
host    all    all    127.0.0.1/32    md5
# Thành:
host    all    all    127.0.0.1/32    trust

# Restart
brew services restart postgresql@16
```

---

### ❌ "psql: command not found"

```bash
# macOS - Add to PATH
echo 'export PATH="/opt/homebrew/opt/postgresql@16/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

---

## 🔄 RESET DATABASE

```bash
dropdb cinema
createdb cinema
psql -d cinema < database/cinema_dump.sql
```

---

## 📦 BACKUP DATABASE

```bash
# Backup trước khi develop
pg_dump cinema > backup_$(date +%Y%m%d).sql

# Restore
psql -d cinema < backup_20251017.sql
```

---

## 🎯 NEXT STEPS

1. ✅ Database setup xong
2. ✅ Config `application.properties`
3. ✅ Test: `mvn javafx:run`
4. → Đọc `DEVELOPMENT_GUIDE.md` để bắt đầu code

---

## 🔧 EXPORT DATABASE (MEMBER A ONLY)

> Dành cho Member A khi cần update database cho team

### Khi nào cần export?

- ✅ Thêm movies/showtimes mới
- ✅ Update data mẫu
- ✅ Sửa schema (thêm/xóa tables)
- ✅ Cuối mỗi sprint

### Cách 1: Dùng script (Khuyến nghị) ⚡

```bash
cd eventfx/database
bash export-database.sh
```

**Output**:

```
🗄️  Exporting database 'cinema'...
📦 Exporting full dump...
✅ cinema_dump.sql
📋 Exporting schema...
✅ cinema_schema.sql
💾 Exporting data...
✅ cinema_data.sql

📊 Database stats:
   Movies: 6
   Showtimes: 160
   Users: 2

✅ Export done!
```

### Cách 2: Manual export

```bash
cd eventfx/database

# Full dump
pg_dump -U trantuanduong cinema > cinema_dump.sql

# Schema only
pg_dump -U trantuanduong --schema-only cinema > cinema_schema.sql

# Data only
pg_dump -U trantuanduong --data-only cinema > cinema_data.sql
```

### Commit lên GitHub

```bash
# Add files
git add cinema_dump.sql cinema_schema.sql cinema_data.sql

# Commit
git commit -m "Update database: Added 3 new movies"

# Push
git push

# Thông báo team
# "Database updated! Run: git pull && psql -d cinema < database/cinema_dump.sql"
```

### Team members update

```bash
# Pull changes
git pull

# Re-import database
psql -d cinema < database/cinema_dump.sql

# Done! Có data mới
```

---

**💡 TIP**: Dùng pgAdmin4 để xem database dễ hơn (GUI)
