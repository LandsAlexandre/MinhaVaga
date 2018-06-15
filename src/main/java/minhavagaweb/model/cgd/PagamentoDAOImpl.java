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
<<<<<<< HEAD
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.persistencia.Conector;

public class PagamentoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM pagamento ";
    private static final String INSERT = "INSERT INTO pagamento (id_pagamento,valor,"
=======
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.*;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class PagamentoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {
    
    private final String SELECT = "SELECT * FROM pagamento ";
    private final String INSERT = "INSERT INTO pagamento (id_pagamento,valor,"
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            + "dataPagamento,pago,formaPagamento,id_pagamento) VALUES (?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM pagamento WHERE id_pagamento = ?;";
    private static final String UPDATE = "UPDATE pagamento SET (valor,"
            + "dataPagamento,pago,formaPagamento,id_pagamento)"
            + " = (?,?,?,?,?) WHERE id_pagamento = ?;";
<<<<<<< HEAD

    private static final String ID_PAGAMENTO = "id_pagamento";
    private static final String VALOR = "valor";
    private static final String DATA = "dataPagamento";
    private static final String PAGO = "pago";
    private static final String FORMA = "formaPagamento";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String ORDER = "ORDER BY id_pagamento ASC";

=======
    
    private final String ID_PAGAMENTO = "id_pagamento", VALOR = "valor", DATA = "dataPagamento",
            PAGO = "pago", FORMA = "formaPagamento", ID_CLIENTE = "id_cliente";
    
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    List<Pagamento> pagamentos = new ArrayList<>();
    
    @Override
<<<<<<< HEAD
    public List<G> getAll() throws SQLException, ClassNotFoundException {
=======
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Pagamento pagamento;
        while (result.next()) {
            pagamento = new Pagamento();
            pagamento.setId(result.getInt(ID_PAGAMENTO));
            pagamento.setValor(result.getDouble(VALOR));
<<<<<<< HEAD
            Calendar data = Calendar.getInstance();
            data.setTime(result.getDate(DATA));

            pagamento.setDataPagamento(data);
            pagamento.setPago(result.getBoolean(PAGO));
            pagamento.setFormaPagamento(result.getString(FORMA));

=======
            // convertendo data //
            Calendar data = Calendar.getInstance();
            data.setTime(result.getDate(DATA));
            
            pagamento.setDataPagamento(data);
            pagamento.setPago(result.getBoolean(PAGO));
            pagamento.setFormaPagamento(result.getString(FORMA));
            
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            Cliente c;
            PessoaDAOImpl dao = new PessoaDAOImpl();
            c = (Cliente) dao.getById(result.getInt(ID_CLIENTE));
            pagamento.setCliente(c);
<<<<<<< HEAD

            pagamentos.add(pagamento);
        }
        this.closeConnection(connection);
        return (List<G>) pagamentos;
=======
            
            pagamentos.add(pagamento);
        }
        this.closeConnection(connection);
        return (List<GenericType>) pagamentos;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
    
    @Override
<<<<<<< HEAD
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
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
<<<<<<< HEAD
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
=======
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        Double valor = ((Pagamento) obj).getValor();
        Calendar data = ((Pagamento) obj).getDataPagamento();
        Boolean pago = ((Pagamento) obj).isPago();
        String forma = ((Pagamento) obj).getFormaPagamento();
        int id_cliente = ((Pagamento) obj).getCliente().getId();
<<<<<<< HEAD

        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_PAGAMENTO));
=======
        
        statement.setInt(1, this.getNextId());
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setDouble(2, valor);
        statement.setDate(3, new java.sql.Date(data.getTimeInMillis()));
        statement.setBoolean(4, pago);
        statement.setString(5, forma);
        statement.setInt(6, id_cliente);
        boolean stat = statement.execute();
        this.closeConnection(connection);
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        return stat;
    }
    
    @Override
<<<<<<< HEAD
    public void update(G obj) throws SQLException, ClassNotFoundException {
=======
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        int id = ((Pagamento) obj).getId();
        Double valor = ((Pagamento) obj).getValor();
        Calendar data = ((Pagamento) obj).getDataPagamento();
        Boolean pago = ((Pagamento) obj).isPago();
        String forma = ((Pagamento) obj).getFormaPagamento();
        int id_cliente = ((Pagamento) obj).getCliente().getId();
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setDouble(2, valor);
        statement.setDate(3, new java.sql.Date(data.getTimeInMillis()));
        statement.setBoolean(4, pago);
        statement.setString(5, forma);
        statement.setInt(6, id_cliente);
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setInt(6, id);
        statement.execute();
        this.closeConnection(connection);
    }
    
    @Override
<<<<<<< HEAD
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
=======
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Pagamento) obj).getId());
        statement.execute();
        this.closeConnection(connection);
<<<<<<< HEAD
=======
    }
    
    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_pagamento ASC;";
        Connection connection = this.openConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.execute();
            ResultSet result = statement.executeQuery();
            if (result.last()) {
                res = result.getInt(ID_PAGAMENTO);
                return res + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConnection(connection);
        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
    
}
