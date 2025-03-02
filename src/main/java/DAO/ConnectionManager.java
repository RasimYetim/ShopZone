package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DBMS;trustServerCertificate=true;encrypt=true;";
    private static final String USERNAME = "ShopZone";  // Veritabanı kullanıcı adınız
    private static final String PASSWORD = "1234ASdf";  // Veritabanı parolanız
    private static Connection connection = null;

    // Veritabanına bağlantı sağlayan metot
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // SQL Server JDBC sürücüsünü yükleme
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Bağlantıyı oluşturma
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Veritabanına bağlantı başarılı!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver yüklenemedi!", e);
            } catch (SQLException e) {
                throw new SQLException("Veritabanı bağlantısı sağlanamadı!", e);
            }
        }
        return connection;
    }

    // Bağlantıyı kapatma metodu
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Veritabanı bağlantısı kapatıldı.");
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı kapatılırken bir hata oluştu: " + e.getMessage());
        }
    }
}

