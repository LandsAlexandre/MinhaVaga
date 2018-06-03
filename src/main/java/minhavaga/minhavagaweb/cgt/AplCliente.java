/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgt;

import java.sql.SQLException;
import java.text.ParseException;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgd.PessoaDAOImpl;

/**
 *
 * @author 20142bsi0070
 */
public class AplCliente {

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void cadastrarCliente(Pessoa p) throws SQLException, ClassNotFoundException{  
        PessoaDAOImpl dao = new PessoaDAOImpl();
        dao.insert(p);
            
    }
    
    public void fazerLogin(Pessoa p) throws SQLException, ClassNotFoundException, ParseException {
        try {
            PessoaDAOImpl dao = new PessoaDAOImpl();
            dao.selectLogin(p);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro no Login!");
        }
    }
}
