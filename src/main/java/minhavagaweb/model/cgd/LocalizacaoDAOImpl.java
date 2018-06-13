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
import minhavagaweb.model.Localizacao;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class LocalizacaoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {
    
    private final String SELECT = "SELECT * FROM localizacao ";
    private final String INSERT = "INSERT INTO localizacao (id_localizacao,latitude,"
            + "longitude) VALUES (?,?,?);";
    private final String DELETE = "DELETE FROM localizacao WHERE id_localizacao = ?;";
    private final String UPDATE = "UPDATE localizacao SET (latitude,longitude)"
            + " = (?,?) WHERE id_localizacao = ?;";
    
    private final String ID_LOCAL = "id_localizacao", LAT = "latitude", LON = "longitude";
    
    List<Localizacao> localizacoes = new ArrayList<>();
    
    @Override
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();
        
        Localizacao localizacao;
        while (result.next()) {
            localizacao = new Localizacao();
            localizacao.setId(result.getInt(ID_LOCAL));
            localizacao.setLatitude(result.getDouble(LAT));
            localizacao.setLongitude(result.getDouble(LON));
            localizacoes.add(localizacao);
        }
        this.closeConnection(connection);
        return (List<GenericType>) localizacoes;
    }
    
    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
        Localizacao localizacao = null;
        if (localizacoes.isEmpty()) {
            localizacoes = (List<Localizacao>) this.getAll();
        }
        for (Localizacao local : localizacoes) {
            if (local.getId() == id) {
                localizacao = local;
            }
        }
        return (GenericType) localizacao;
    }
    
    @Override
    public void insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        
        Double lat = ((Localizacao) obj).getLatitude();
        Double lon = ((Localizacao) obj).getLongitude();
        
        statement.setInt(1, this.getNextId());
        statement.setDouble(2, lat);
        statement.setDouble(3, lon);
        
        statement.execute();
        this.closeConnection(connection);
    }
    
    @Override
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
    }
    
    @Override
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Localizacao) obj).getId());
        statement.execute();
        this.closeConnection(connection);
    }
    
    @Override
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
    }
    
}
