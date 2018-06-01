/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgt;

import java.sql.SQLException;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgd.ClienteDAOImpl;

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
        ClienteDAOImpl dao = new ClienteDAOImpl();
        dao.insert(p);//c.select(p); 
            
    }
    
}
