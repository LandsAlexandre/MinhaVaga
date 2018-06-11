/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import minhavagaweb.model.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.cgd.PessoaDAOImpl;
import minhavagaweb.valida.ValidaCPF;
import minhavagaweb.valida.ValidaEmail;
import org.springframework.stereotype.Controller;
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

        PessoaDAOImpl dao = new PessoaDAOImpl();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(formato.parse(datanascimento).getTime());
        p.setNascimento(data);

        if(ValidaCPF.isValido(p.getCpf()) && ValidaEmail.isEmailValido(p.getEmail())) {
            dao.insert(p);
            System.out.println("CADASTRADO> " + p.getNome() + " - " + p.getEmail() + " - " + p.getNascimento());
            return "cliente-adicionado";
        }
        System.out.println("Email Inválido!");
        return "cliente";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {

        return new ModelAndView("login", "Pessoa", new Pessoa());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fazerLogin(Cliente p){
        try {
            verificarLogin(p.getEmail(),p.getSenha());
            System.out.println("LOGUEI");
        }
        catch(RuntimeException e) {
            System.out.println("Login incorreto!");
            return "login";
        }
        return "home";
    }
    
    private void verificarLogin(String email, String senha) {
        PessoaDAOImpl dao = new PessoaDAOImpl();
        boolean result = dao.selectLogin(email, senha);
        if(!result) {
            throw new RuntimeException("Login incorreto");
        }
    }
    @RequestMapping(value = "home", method = RequestMethod.POST)
    public ModelAndView home() {
        return new ModelAndView("home");
    }
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
