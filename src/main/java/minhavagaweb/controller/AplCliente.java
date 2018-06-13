/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.CPF;
import minhavagaweb.model.cdp.Email;
import minhavagaweb.model.cdp.Pessoa;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.cgd.PessoaDAOImpl;
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

        try {
            if (CPF.isCPFValido(p.getCpf()) && Email.isEmailValido(p.getEmail())) {
                dao.insert(p);

                return "cliente-adicionado";
            }
        } catch (Exception ex) {
            return "emailRegistrado";
        }

        return "cliente";
    }

    public String alterarCliente() {

        return null;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {

        return new ModelAndView("login", "Pessoa", new Pessoa());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fazerLogin(Cliente p) throws SQLException, ClassNotFoundException {
        if (verificarLogin(p.getEmail(), p.getSenha())) {
            return "solicitarReserva";
        } else {
            return "loginIncorreto";
        }
    }

    private boolean verificarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        PessoaDAOImpl dao = new PessoaDAOImpl();

        return dao.selectLogin(email, senha);
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
