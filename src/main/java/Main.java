/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISM
 */
import static java.lang.System.getenv;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgd.*;
import minhavaga.minhavagaweb.cgt.AplCliente;
import minhavaga.minhavagaweb.persistencia.Conector;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        AplCliente aplCliente = new AplCliente();      
        Cliente p = new Cliente();
        p.setNome("Helen Franca Medeiros");
        p.setEmail("helen@gmail.com");
        p.setCpf("48765432198");
        p.setSenha("123456");
        p.setNascimento(Calendar.getInstance());

        aplCliente.cadastrarCliente(p);

    }
}
