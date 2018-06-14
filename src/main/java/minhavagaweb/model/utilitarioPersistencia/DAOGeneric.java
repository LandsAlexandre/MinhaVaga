package minhavagaweb.model.utilitarioPersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ISM
 */
public class DAOGeneric {

    private Connection con;

    protected Connection openConnection() throws SQLException, ClassNotFoundException {
      
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        //"jdbc:postgresql://localhost:5432/ForTests", "postgres", "123"
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                     "jdbc:postgresql://stampy.db.elephantsql.com:5432/msklitrp", "msklitrp","zOCACHP4G1LbxXVuIPSVUoQ40uDFZ8Jn");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection con) throws SQLException {
        con.close();
    }

    protected void execute(String query) throws SQLException {
        Statement statement = con.createStatement();
        // Comando para criar
        statement.execute(query);

        statement.close();
    }

    protected ResultSet executeQuery(String query) throws SQLException {
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(query);

        statement.close();

        return rs;
    }

    protected int executeUpdate(String query) throws SQLException {
        Statement statement = con.createStatement();

        int numero = 0;
        // Comando para update, insert e delete		
        statement.executeUpdate(query);

        statement.close();

        return numero;
    }
}
