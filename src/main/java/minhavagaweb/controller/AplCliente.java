/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import minhavagaweb.model.cdp.Cartao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.*;
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

    private static final String SOLICITACAO = "solicitarReserva";

    @RequestMapping(value = "cliente", method = RequestMethod.GET)
    public ModelAndView cliente() {

        return new ModelAndView("cliente", "command", new Cliente());
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {

        return new ModelAndView("login", "Pessoa", new Pessoa());
    }

    @RequestMapping("cliente")
    public String cadastrarCliente(
            Cliente p, @RequestParam("datanascimento") String datanascimento,
            @RequestParam(value = "cadastrarCartao", required = false) String cadastrar) throws ParseException {

        PessoaDAOImpl dao = new PessoaDAOImpl();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(formato.parse(datanascimento).getTime());
        p.setNascimento(data);
 
        if (CPF.isCPFValido(p.getCpf()) && Email.isEmailValido(p.getEmail())) {
            try {
                dao.insert(p);
                if (cadastrar != null) {
                    return "cartao";
                } else {
                    return SOLICITACAO;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return "emailRegistrado";
            }
        }
        return "cliente";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fazerLogin(Cliente p) throws SQLException, ClassNotFoundException {
        if (verificarLogin(p.getEmail(), p.getSenha())) {
            return SOLICITACAO;
        } else {
            return "loginIncorreto";
        }
    }

    private boolean verificarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        PessoaDAOImpl dao = new PessoaDAOImpl();
        return dao.selectLogin(email, senha);
    }

    /**
     *
     * @param c
     * @return
     * @throws Exception
     */
    @RequestMapping("cartao")
    public String cadastrarCartao(Cartao c) throws Exception {

        CartaoDAOImpl dao = new CartaoDAOImpl();
        if (ValidaCartao.validCC(c.getNumeroCartao())) {
            dao.insert(c);
            return SOLICITACAO;
        } else {
            return "cartao-invalido";
        }

    }
}
