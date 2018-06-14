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
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.utilitarioPersistencia.Conector;


public class LocalizacaoDAOImpl<GENERICTYPE> implements GenericDAO<GenericType> {
    
    private static final String SELECT = "SELECT * FROM localizacao ";
    private static final String INSERT = "INSERT INTO localizacao (id_localizacao,latitude,"
            + "longitude) VALUES (?,?,?);";
    private static final String DELETE = "DELETE FROM localizacao WHERE id_localizacao = ?;";
    private static final String UPDATE = "UPDATE localizacao SET (latitude,longitude)"
            + " = (?,?) WHERE id_localizacao = ?;";
    
    private static final String ID_LOCAL = "id_localizacao";
    private static final String LAT = "latitude";
    private static final String LON = "longitude";
    
    List<Localizacao> localizacoes = new ArrayList<>();
    @Override
    public List<GenericType> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
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
            } catch (SQLException ex) {
                Logger.getLogger(LocalizacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LocalizacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>)localizacoes;
    }

    @Override
    public GenericType getById(int id) {
        Localizacao localizacao = null;
        if (localizacoes.isEmpty()) {
            localizacoes = (List<Localizacao>) this.getAll();
        }
        for (Localizacao local : localizacoes) {
            if (local.getId() == id)
                localizacao = local;
        }
        return (GenericType) localizacao;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                
                Double lat = ((Localizacao)obj).getLatitude();
                Double lon = ((Localizacao)obj).getLongitude();

                statement.setInt(1, this.getNextId());
                statement.setDouble(2, lat);
                statement.setDouble(3, lon);

                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LocalizacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                
                int id = ((Localizacao)obj).getId();
                Double lat = ((Localizacao)obj).getLatitude();
                Double lon = ((Localizacao)obj).getLongitude();

                statement.setDouble(1, lat);
                statement.setDouble(2, lon);
                statement.setInt(3, id);
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LocalizacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((Localizacao)obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LocalizacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String order = "ORDER BY id_localizacao ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT+ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(ID_LOCAL);
                    return res+1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
