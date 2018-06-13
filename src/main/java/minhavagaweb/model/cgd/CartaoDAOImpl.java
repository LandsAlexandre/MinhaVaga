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
import minhavagaweb.model.Cartao;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class CartaoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM cartao ";
    private final String INSERT = "INSERT INTO cartao (id_cartao,nomeTitular,"
            + "numeroCartao,cvv,dataValidade) VALUES(?,?,?,?,?);";
    private final String DELETE = "DELETE FROM cartao WHERE id_cartao = ?;";
    private final String UPDATE = "UPDATE cartao SET (nomeTitular,"
            + "numeroCartao,cvv,dataValidade) = (?,?,?,?) WHERE id_cartao = ?;";

    private final String NOME = "nomeTitular", NUMERO = "numeroCartao",
            ID_CARTAO = "id_cartao", CVV = "cvv", DATA_VALIDADE = "dataValidade";

    List<Cartao> cartoes = new ArrayList<>();

    @Override
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
    public void insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();

        PreparedStatement statement = connection.prepareStatement(this.INSERT);
        String nomeTitular = ((Cartao) obj).getNomeTitular();
        String cvv = ((Cartao) obj).getCvv();
        String numero = ((Cartao) obj).getNumeroCartao();
        Calendar dataValidade = ((Cartao) obj).getDataValidade();

        statement.setInt(1, this.getNextId());
        statement.setString(2, nomeTitular);
        statement.setString(3, numero);
        statement.setString(4, cvv);
        statement.setDate(5, new java.sql.Date(dataValidade.getTimeInMillis()));

        statement.execute();
        this.closeConnection(connection);

    }

    @Override
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
        Cartao cartao = null;
        if (cartoes.isEmpty()) {
            cartoes = (List<Cartao>) this.getAll();
        }
        for (Cartao c : cartoes) {
            if (c.getId() == id) {
                cartao = c;
            }
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
    }
}
