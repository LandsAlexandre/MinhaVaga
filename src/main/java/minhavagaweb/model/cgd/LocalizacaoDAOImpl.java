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
import minhavagaweb.model.persistencia.Conector;

public class LocalizacaoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM localizacao ";
    private static final String INSERT = "INSERT INTO localizacao (id_localizacao,latitude,"
            + "longitude) VALUES (?,?,?);";
    private static final String DELETE = "DELETE FROM localizacao WHERE id_localizacao = ?;";
    private static final String UPDATE = "UPDATE localizacao SET (latitude,longitude)"
            + " = (?,?) WHERE id_localizacao = ?;";

    private static final String ID_LOCAL = "id_localizacao";
    private static final String LAT = "latitude";
    private static final String LON = "longitude";
    private static final String ORDER = "ORDER BY id_localizacao ASC";
    
    List<Localizacao> localizacoes = new ArrayList<>();

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
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
        return (List<G>) localizacoes;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
        Localizacao localizacao = null;
        if (localizacoes.isEmpty()) {
            localizacoes = (List<Localizacao>) this.getAll();
        }
        for (Localizacao local : localizacoes) {
            if (local.getId() == id) {
                localizacao = local;
            }
        }
        return (G) localizacao;
    }

    @Override
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
    }

    @Override
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

        this.closeConnection(connection);
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Localizacao) obj).getId());
        statement.execute();
        this.closeConnection(connection);
    }

   

}
