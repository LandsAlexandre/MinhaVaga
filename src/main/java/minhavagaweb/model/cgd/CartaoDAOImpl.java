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
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.persistencia.Conector;

public class CartaoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM cartao ";
    private static final String INSERT = "INSERT INTO cartao (id_cartao,nomeTitular,"
=======
import minhavagaweb.model.Cartao;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class CartaoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM cartao ";
    private final String INSERT = "INSERT INTO cartao (id_cartao,nomeTitular,"
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            + "numeroCartao,cvv,dataValidade) VALUES(?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM cartao WHERE id_cartao = ?;";
    private static final String UPDATE = "UPDATE cartao SET (nomeTitular,"
            + "numeroCartao,cvv,dataValidade) = (?,?,?,?) WHERE id_cartao = ?;";

<<<<<<< HEAD
    private static final String NOME = "nomeTitular";
    private static final String NUMERO = "numeroCartao";
    private static final String ID_CARTAO = "id_cartao";
    private static final String CVV = "cvv";
    private static final String DATA_VALIDADE = "dataValidade";
    private static final String ORDER = "ORDER BY id_cartao ASC";
=======
    private final String NOME = "nomeTitular", NUMERO = "numeroCartao",
            ID_CARTAO = "id_cartao", CVV = "cvv", DATA_VALIDADE = "dataValidade";
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

    List<Cartao> cartoes = new ArrayList<>();

    @Override
<<<<<<< HEAD
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.SELECT);
        statement.execute();

        try {
            ResultSet result = statement.executeQuery();

            Cartao cartao;
            while (result.next()) {
                cartao = new Cartao();
                cartao.setId(result.getInt(CartaoDAOImpl.ID_CARTAO));
                cartao.setNomeTitular(result.getString(CartaoDAOImpl.NOME));
                cartao.setNumeroCartao(result.getString(CartaoDAOImpl.NUMERO));
                cartao.setCvv(result.getString(CartaoDAOImpl.CVV));

                Calendar data;
                data = Calendar.getInstance();
                data.setTime(result.getDate(CartaoDAOImpl.DATA_VALIDADE));

                cartao.setDataValidade(data);
                cartoes.add(cartao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection(connection);
        }
        return (List<G>) cartoes;

    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();

        PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.INSERT);
=======
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(this.SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        Cartao cartao;
        while (result.next()) {
            cartao = new Cartao();
            cartao.setId(result.getInt(this.ID_CARTAO));
            cartao.setNomeTitular(result.getString(this.NOME));
            cartao.setNumeroCartao(result.getString(this.NUMERO));
            cartao.setCvv(result.getString(this.CVV));

            Calendar data;
            data = Calendar.getInstance();
            data.setTime(result.getDate(this.DATA_VALIDADE));

            cartao.setDataValidade(data);
            cartoes.add(cartao);
        }
        this.closeConnection(connection);
        return (List<GenericType>) cartoes;
    }

    @Override
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();

        PreparedStatement statement = connection.prepareStatement(this.INSERT);
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        String nomeTitular = ((Cartao) obj).getNomeTitular();
        String cvv = ((Cartao) obj).getCvv();
        String numero = ((Cartao) obj).getNumeroCartao();
        Calendar dataValidade = ((Cartao) obj).getDataValidade();

<<<<<<< HEAD
        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_CARTAO));
=======
        statement.setInt(1, this.getNextId());
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setString(2, nomeTitular);
        statement.setString(3, numero);
        statement.setString(4, cvv);
        statement.setDate(5, new java.sql.Date(dataValidade.getTimeInMillis()));

        boolean stat = statement.execute();
        this.closeConnection(connection);
        return stat;
    }

    @Override
<<<<<<< HEAD
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.UPDATE);
        String nomeTitular = ((Cartao) obj).getNomeTitular();
        String cvv = ((Cartao) obj).getCvv();
        String numero = ((Cartao) obj).getNumeroCartao();
        Calendar dataValidade = ((Cartao) obj).getDataValidade();

        statement.setString(1, nomeTitular);
        statement.setString(2, numero);
        statement.setString(3, cvv);
        statement.setDate(4, new java.sql.Date(dataValidade.getTimeInMillis()));

        statement.setInt(5, 1);
        statement.execute();
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.DELETE);
        statement.setInt(1, ((Cartao) obj).getId());
        statement.execute();

    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
    public void update(GenericType obj) {
        try (Connection connection = this.openConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(this.UPDATE)) {
                String nomeTitular = ((Cartao) obj).getNomeTitular();
                String cvv = ((Cartao) obj).getCvv();
                String numero = ((Cartao) obj).getNumeroCartao();
                Calendar dataValidade = ((Cartao) obj).getDataValidade();

                statement.setString(1, nomeTitular);
                statement.setString(2, numero);
                statement.setString(3, cvv);
                statement.setDate(4, new java.sql.Date(dataValidade.getTimeInMillis()));

                statement.setInt(5, 1);
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(this.DELETE)) {
            statement.setInt(1, ((Cartao) obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Cartao cartao = null;
        if (cartoes.isEmpty()) {
            cartoes = (List<Cartao>) this.getAll();
        }
        for (Cartao c : cartoes) {
            if (c.getId() == id) {
                cartao = c;
            }
<<<<<<< HEAD
        }
        return (G) cartao;
=======
        }
        return (GenericType) cartao;
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_cartao ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(this.SELECT + ORDER, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(ID_CARTAO);
            return res + 1;
        }
        this.closeConnection(connection);
        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
}
