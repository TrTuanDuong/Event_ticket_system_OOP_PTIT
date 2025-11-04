#!/bin/bash

# Script tự động export database
# Usage: bash export-database.sh

echo "🗄️  Exporting database 'cinema'..."

DB_USER="trantuanduong"
DB_NAME="cinema"

# Export full dump
echo "📦 Exporting full dump..."
pg_dump -U $DB_USER $DB_NAME > cinema_dump.sql
echo "✅ cinema_dump.sql"

# Export schema only
echo "📋 Exporting schema..."
pg_dump -U $DB_USER --schema-only $DB_NAME > cinema_schema.sql
echo "✅ cinema_schema.sql"

# Export data only
echo "💾 Exporting data..."
pg_dump -U $DB_USER --data-only --inserts $DB_NAME > cinema_data.sql
echo "✅ cinema_data.sql"

# Stats
echo ""
echo "📊 Database stats:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_movie;" -t | xargs echo "   Movies:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_auditorium;" -t | xargs echo "   Auditoriums:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_seat;" -t | xargs echo "   Seats:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_showtime;" -t | xargs echo "   Showtimes:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_booking;" -t | xargs echo "   Bookings:"
psql -U $DB_USER -d $DB_NAME -c "SELECT COUNT(*) FROM api_user;" -t | xargs echo "   Users:"

echo ""
echo "✅ Export done!"
echo ""
echo "🚀 Next: Commit to Git"
echo "   git add database/*.sql"
echo "   git commit -m 'Update database dump'"
echo "   git push"
