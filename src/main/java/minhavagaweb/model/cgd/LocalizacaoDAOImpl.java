/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhavagaweb.model.cdp.Localizacao;
<<<<<<< HEAD
import minhavagaweb.model.persistencia.Conector;

public class LocalizacaoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM localizacao ";
    private static final String INSERT = "INSERT INTO localizacao (id_localizacao,latitude,"
=======
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class LocalizacaoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {
    
    private final String SELECT = "SELECT * FROM localizacao ";
    private final String INSERT = "INSERT INTO localizacao (id_localizacao,latitude,"
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            + "longitude) VALUES (?,?,?);";
    private static final String DELETE = "DELETE FROM localizacao WHERE id_localizacao = ?;";
    private static final String UPDATE = "UPDATE localizacao SET (latitude,longitude)"
            + " = (?,?) WHERE id_localizacao = ?;";

    private static final String ID_LOCAL = "id_localizacao";
    private static final String LAT = "latitude";
    private static final String LON = "longitude";
    private static final String ORDER = "ORDER BY id_localizacao ASC";
    
    List<Localizacao> localizacoes = new ArrayList<>();
<<<<<<< HEAD

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
=======
    
    @Override
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Localizacao localizacao;
        while (result.next()) {
            localizacao = new Localizacao();
            localizacao.setId(result.getInt(ID_LOCAL));
            localizacao.setLatitude(result.getDouble(LAT));
            localizacao.setLongitude(result.getDouble(LON));
            localizacoes.add(localizacao);
        }
        this.closeConnection(connection);
<<<<<<< HEAD
        return (List<G>) localizacoes;
=======
        return (List<GenericType>) localizacoes;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
    
    @Override
<<<<<<< HEAD
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Localizacao localizacao = null;
        if (localizacoes.isEmpty()) {
            localizacoes = (List<Localizacao>) this.getAll();
        }
        for (Localizacao local : localizacoes) {
            if (local.getId() == id) {
                localizacao = local;
            }
        }
<<<<<<< HEAD
        return (G) localizacao;
=======
        return (GenericType) localizacao;
    }
    
    @Override
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        
        Double lat = ((Localizacao) obj).getLatitude();
        Double lon = ((Localizacao) obj).getLongitude();
        
        statement.setInt(1, this.getNextId());
        statement.setDouble(2, lat);
        statement.setDouble(3, lon);
        
        boolean stat =statement.execute();
        this.closeConnection(connection);
        return stat;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
    
    @Override
<<<<<<< HEAD
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Double lat = ((Localizacao) obj).getLatitude();
        Double lon = ((Localizacao) obj).getLongitude();

        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_LOCAL));
        statement.setDouble(2, lat);
        statement.setDouble(3, lon);

        boolean stat = statement.execute();
        this.closeConnection(connection);
        return stat;
=======
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        
        int id = ((Localizacao) obj).getId();
        Double lat = ((Localizacao) obj).getLatitude();
        Double lon = ((Localizacao) obj).getLongitude();
        
        statement.setDouble(1, lat);
        statement.setDouble(2, lon);
        statement.setInt(3, id);
        statement.execute();
        
        this.closeConnection(connection);
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
    
    @Override
<<<<<<< HEAD
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        int id = ((Localizacao) obj).getId();
        Double lat = ((Localizacao) obj).getLatitude();
        Double lon = ((Localizacao) obj).getLongitude();

        statement.setDouble(1, lat);
        statement.setDouble(2, lon);
        statement.setInt(3, id);
        statement.execute();

=======
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Localizacao) obj).getId());
        statement.execute();
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        this.closeConnection(connection);
    }
    
    @Override
<<<<<<< HEAD
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Localizacao) obj).getId());
        statement.execute();
        this.closeConnection(connection);
=======
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_localizacao ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(ID_LOCAL);
            return res + 1;
            
        }
        this.closeConnection(connection);
        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }

   

}
