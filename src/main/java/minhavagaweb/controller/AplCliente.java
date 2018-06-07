/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import java.util.Date;
import minhavagaweb.model.Pessoa;
import minhavagaweb.model.Cliente;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgd.PessoaDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 20142bsi0070
 */
@Controller
public class AplCliente {

    /* public void setCliente(Cliente cliente) {
    this.cliente = cliente;
    }*/
    @RequestMapping(value = "cliente", method = RequestMethod.GET)
    public ModelAndView cliente() {
        
        return new ModelAndView("cliente", "command", new Cliente());
    }

    @RequestMapping("cliente")
    public String cadastrarCliente(
            Cliente p, @RequestParam("datanascimento") String datanascimento) throws SQLException, ClassNotFoundException, ParseException {
        p.toString();
        PessoaDAOImpl dao = new PessoaDAOImpl();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(formato.parse(datanascimento).getTime());
        p.setNascimento(data);
        
        System.out.println("AQUIIII!> " + p.getNome() + " - " + p.getEmail() + " - " + p.getNascimento());
        dao.insert(p);
        return "cliente-adicionado";

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
