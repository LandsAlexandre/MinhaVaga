/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavaga.minhavagaweb.persistencia.Conector;


public class PagamentoDAOImpl<GenericType> implements GenericDAO<GenericType> {
    private final String SELECT = "SELECT * FROM pagamento ";
    private final String INSERT = "INSERT INTO pagamento (id_pagamento,valor,"
            + "dataPagamento,pago,formaPagamento,id_cliente) VALUES (?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM pagamento WHERE id_pagamento = ?;";
    private final String UPDATE = "UPDATE pagamento SET (valor,"
            + "dataPagamento,pago,formaPagamento,id_cliente);"
            + " = (?,?,?,?,?) WHERE id_pagamento = ?;";
    
    private final String ID_PAGAMENTO = "id_pagamento", VALOR = "valor", DATA = "dataPagamento",
            PAGO = "pago", FORMA = "formaPagamento", ID_CLIENTE = "id_cliente";
    
    @Override
    public List<GenericType> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericType getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(GenericType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(GenericType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(GenericType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_pagamento ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT+ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(ID_PAGAMENTO);
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
