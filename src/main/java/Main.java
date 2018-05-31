/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISM
 */
import java.sql.SQLException;
import java.util.Calendar;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgd.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //AplCliente aplCliente = new AplCliente();      
        ClienteDAOImpl dao = new ClienteDAOImpl();
        Cliente p = new Cliente();
        p.setId(1);
        p.setNome("Helen Franca Medeiros");
        p.setEmail("helen@gmail.com");
        p.setCpf("98765432198");
        p.setSenha("123456");
        p.setNascimento(Calendar.getInstance());
        //dao.insert(p);
        //dao.delete(p);
        dao.update(p);
        /*aplCliente.cadastrarCliente(p);*/
       
    }
}
