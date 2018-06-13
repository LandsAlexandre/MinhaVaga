package minhavagaweb.model.utilitarioPersistencia;

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

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //String dbUrl = System.getenv("DATABASE_URL");
        //String dbUrl = "<your local database url>";

        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        //"jdbc:postgresql://localhost:5432/ForTests", "postgres", "123"
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
