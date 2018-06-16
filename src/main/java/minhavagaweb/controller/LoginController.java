/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cgd.PessoaDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author landerson
 */
@Controller
public class LoginController {
    
    @RequestMapping(value = "efetuaLogin")
    public String fazerLogin(Cliente p, HttpSession session) throws SQLException, ClassNotFoundException {
        if (verificarLogin(p.getEmail(), p.getSenha())) {
            session.setAttribute("usuarioLogado", p);
            return "solicitarReserva";
        } else {
            return "loginIncorreto";
        }
    }
    private boolean verificarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        PessoaDAOImpl dao = new PessoaDAOImpl();
        return dao.selectLogin(email, senha);
    }
}
