package com.ptit.ticketing;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.repo.UserRepo;
import com.ptit.ticketing.repo.MovieRepo;
import com.ptit.ticketing.repo.ShowtimeRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Test class để kiểm tra kết nối database
 * Chạy: mvn compile exec:java
 * -Dexec.mainClass="com.ptit.ticketing.TestConnection"
 */
public class TestConnection {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("🔍 KIỂM TRA KẾT NỐI DATABASE");
        System.out.println("=".repeat(60));

        try {
            // Test 1: Kiểm tra Database singleton
            System.out.println("\n📌 Test 1: Database Singleton");
            Database db = Database.get();
            System.out.println("✅ Database singleton initialized");

            // Test 2: Kiểm tra DataSource
            System.out.println("\n📌 Test 2: DataSource");
            DataSource ds = db.ds();
            System.out.println("✅ DataSource obtained");

            // Test 3: Kiểm tra Connection
            System.out.println("\n📌 Test 3: Connection Pool");
            try (Connection conn = ds.getConnection()) {
                System.out.println("✅ Connection opened: " + conn.getMetaData().getURL());
                System.out.println("   Database: " + conn.getMetaData().getDatabaseProductName());
                System.out.println("   Version: " + conn.getMetaData().getDatabaseProductVersion());
            }

            // Test 4: Query trực tiếp
            System.out.println("\n📌 Test 4: Direct Query");
            try (Connection conn = ds.getConnection();
                    Statement stmt = conn.createStatement()) {

                // Count users
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM api_user");
                if (rs.next()) {
                    System.out.println("✅ Users: " + rs.getInt(1));
                }
                rs.close();

                // Count movies
                rs = stmt.executeQuery("SELECT COUNT(*) FROM api_movie");
                if (rs.next()) {
                    System.out.println("✅ Movies: " + rs.getInt(1));
                }
                rs.close();

                // Count showtimes
                rs = stmt.executeQuery("SELECT COUNT(*) FROM api_showtime");
                if (rs.next()) {
                    System.out.println("✅ Showtimes: " + rs.getInt(1));
                }
                rs.close();
            }

            // Test 5: Repository Pattern
            System.out.println("\n📌 Test 5: Repository Pattern");

            // UserRepo
            int userCount = Tx.withTx(ds, conn -> {
                UserRepo repo = new UserRepo(ds);
                return repo.findAll(conn).size();
            });
            System.out.println("✅ UserRepo.findAll(): " + userCount + " users");

            // MovieRepo
            int movieCount = Tx.withTx(ds, conn -> {
                MovieRepo repo = new MovieRepo(ds);
                return repo.findAll(conn).size();
            });
            System.out.println("✅ MovieRepo.findAll(): " + movieCount + " movies");

            // ShowtimeRepo
            int showtimeCount = Tx.withTx(ds, conn -> {
                ShowtimeRepo repo = new ShowtimeRepo(ds);
                return repo.findUpcoming(conn).size();
            });
            System.out.println("✅ ShowtimeRepo.findUpcoming(): " + showtimeCount + " showtimes");

            // Test 6: Sample Data
            System.out.println("\n📌 Test 6: Sample Data");
            Tx.withTx(ds, conn -> {
                MovieRepo repo = new MovieRepo(ds);
                List<Movie> movies = repo.findAll(conn);
                if (!movies.isEmpty()) {
                    Movie movie = movies.get(0);
                    System.out.println("✅ Sample Movie:");
                    System.out.println("   ID: " + movie.getId());
                    System.out.println("   Title: " + movie.getTitle());
                    System.out.println("   Duration: " + movie.getDurationMin() + " mins");
                }
                return null;
            });

            // Test 7: Transaction
            System.out.println("\n📌 Test 7: Transaction Management");
            String result = Tx.withTx(ds, conn -> {
                return "Transaction successful";
            });
            System.out.println("✅ " + result);

            // Summary
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🎉 TẤT CẢ TESTS ĐỀU PASS!");
            System.out.println("=".repeat(60));
            System.out.println("✅ Database connection: OK");
            System.out.println("✅ Connection pool: OK");
            System.out.println("✅ Query execution: OK");
            System.out.println("✅ Repository pattern: OK");
            System.out.println("✅ Transaction management: OK");
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.err.println("\n❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
