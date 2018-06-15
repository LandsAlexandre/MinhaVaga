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
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.persistencia.Conector;

public class PagamentoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM pagamento ";
    private static final String INSERT = "INSERT INTO pagamento (id_pagamento,valor,"
            + "dataPagamento,pago,formaPagamento,id_pagamento) VALUES (?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM pagamento WHERE id_pagamento = ?;";
    private static final String UPDATE = "UPDATE pagamento SET (valor,"
            + "dataPagamento,pago,formaPagamento,id_pagamento)"
            + " = (?,?,?,?,?) WHERE id_pagamento = ?;";

    private static final String ID_PAGAMENTO = "id_pagamento";
    private static final String VALOR = "valor";
    private static final String DATA = "dataPagamento";
    private static final String PAGO = "pago";
    private static final String FORMA = "formaPagamento";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String ORDER = "ORDER BY id_pagamento ASC";

    List<Pagamento> pagamentos = new ArrayList<>();

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT);
                ResultSet result = statement.executeQuery();) {

            Pagamento pagamento;
            while (result.next()) {
                pagamento = new Pagamento();
                pagamento.setId(result.getInt(ID_PAGAMENTO));
                pagamento.setValor(result.getDouble(VALOR));
                Calendar data = Calendar.getInstance();
                data.setTime(result.getDate(DATA));

                pagamento.setDataPagamento(data);
                pagamento.setPago(result.getBoolean(PAGO));
                pagamento.setFormaPagamento(result.getString(FORMA));

                Cliente c;
                PessoaDAOImpl dao = new PessoaDAOImpl();
                c = (Cliente) dao.getById(result.getInt(ID_CLIENTE));
                pagamento.setCliente(c);

                pagamentos.add(pagamento);
            }
        } finally {
            this.closeConnection(con);
        }
        return (List<G>) pagamentos;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
        Pagamento pagamento = null;
        if (pagamentos.isEmpty()) {
            pagamentos = (List<Pagamento>) this.getAll();
        }
        for (Pagamento p : pagamentos) {
            if (p.getId() == id) {
                pagamento = p;
            }
        }
        return (G) pagamento;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        boolean stat = false;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT);) {
            Double valor = ((Pagamento) obj).getValor();
            Calendar data = ((Pagamento) obj).getDataPagamento();
            Boolean pago = ((Pagamento) obj).isPago();
            String forma = ((Pagamento) obj).getFormaPagamento();
            int id_cliente = ((Pagamento) obj).getCliente().getId();

            statement.setInt(1, this.getNextId(ORDER, SELECT, ID_PAGAMENTO));
            statement.setDouble(2, valor);
            statement.setDate(3, new java.sql.Date(data.getTimeInMillis()));
            statement.setBoolean(4, pago);
            statement.setString(5, forma);
            statement.setInt(6, id_cliente);
            stat = statement.execute();
        } finally {
            this.closeConnection(con);
        }
        return stat;
    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            int id = ((Pagamento) obj).getId();
            Double valor = ((Pagamento) obj).getValor();
            Calendar data = ((Pagamento) obj).getDataPagamento();
            Boolean pago = ((Pagamento) obj).isPago();
            String forma = ((Pagamento) obj).getFormaPagamento();
            int id_cliente = ((Pagamento) obj).getCliente().getId();

            statement.setDouble(2, valor);
            statement.setDate(3, new java.sql.Date(data.getTimeInMillis()));
            statement.setBoolean(4, pago);
            statement.setString(5, forma);
            statement.setInt(6, id_cliente);

            statement.setInt(6, id);
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, ((Pagamento) obj).getId());
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

}
