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
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.persistencia.Conector;

public class CartaoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM cartao ";
    private static final String INSERT = "INSERT INTO cartao (id_cartao,nomeTitular,"
            + "numeroCartao,cvv,dataValidade) VALUES(?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM cartao WHERE id_cartao = ?;";
    private static final String UPDATE = "UPDATE cartao SET (nomeTitular,"
            + "numeroCartao,cvv,dataValidade) = (?,?,?,?) WHERE id_cartao = ?;";

    private static final String NOME = "nomeTitular";
    private static final String NUMERO = "numeroCartao";
    private static final String ID_CARTAO = "id_cartao";
    private static final String CVV = "cvv";
    private static final String DATA_VALIDADE = "dataValidade";
    private static final String ORDER = "ORDER BY id_cartao ASC";

    List<Cartao> cartoes = new ArrayList<>();

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.SELECT);
                ResultSet result = statement.executeQuery();) {

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

        }finally {
            this.closeConnection(con);
        }
        return (List<G>) cartoes;

    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        boolean stat = false;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.INSERT)) {

            String nomeTitular = ((Cartao) obj).getNomeTitular();
            String cvv = ((Cartao) obj).getCvv();
            String numero = ((Cartao) obj).getNumeroCartao();
            Calendar dataValidade = ((Cartao) obj).getDataValidade();

            statement.setInt(1, this.getNextId(SELECT + ORDER, ID_CARTAO));
            statement.setString(2, nomeTitular);
            statement.setString(3, numero);
            statement.setString(4, cvv);
            statement.setDate(5, new java.sql.Date(dataValidade.getTimeInMillis()));
            stat = statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection(con);
        }

        return stat;
    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.UPDATE)) {
            String nomeTitular = ((Cartao) obj).getNomeTitular();
            String cvv = ((Cartao) obj).getCvv();
            String numero = ((Cartao) obj).getNumeroCartao();
            Calendar dataValidade = ((Cartao) obj).getDataValidade();
            int id = ((Cartao) obj).getId();

            statement.setString(1, nomeTitular);
            statement.setString(2, numero);
            statement.setString(3, cvv);
            statement.setDate(4, new java.sql.Date(dataValidade.getTimeInMillis()));

            statement.setInt(5, id);
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(CartaoDAOImpl.DELETE)) {
            statement.setInt(1, ((Cartao) obj).getId());
            statement.execute();
            this.closeConnection(connection);
        }finally{
            this.closeConnection(con);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
        Cartao cartao = null;
        if (cartoes.isEmpty()) {
            cartoes = (List<Cartao>) this.getAll();
        }
        for (Cartao c : cartoes) {
            if (c.getId() == id) {
                cartao = c;
            }
        }
        return (G) cartao;
    }
}
