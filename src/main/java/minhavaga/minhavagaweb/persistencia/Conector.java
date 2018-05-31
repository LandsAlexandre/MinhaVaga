package minhavaga.minhavagaweb.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ISM
 */
public class Conector {

    /* private static final String DRIVER = "org.postgresql.Driver";
     private static final String URL = "jdbc:postgresql://localhost:5432/MinhaVaga2.0";
     private static final String USER = "postgres";
     private static final String PASS = "123";
    
     public static Connection getConnection() {
     try {
     Class.forName(DRIVER);
     return DriverManager.getConnection(URL, USER, PASS);
     } catch (ClassNotFoundException | SQLException ex) {
     throw new RuntimeException("Erro na conexão: ", ex);
     }
     }*/
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ForTests", "postgres", "123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex);
        }
    }
}
