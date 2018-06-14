/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

<<<<<<< HEAD
import minhavagaweb.model.cdp.Cartao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.*;
=======
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.CPF;
import minhavagaweb.model.cdp.Email;
import minhavagaweb.model.cdp.Pessoa;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.Cartao;
import minhavagaweb.model.cdp.ValidaCartao;
import minhavagaweb.model.cgd.CartaoDAOImpl;
import minhavagaweb.model.cgd.PessoaDAOImpl;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
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

<<<<<<< HEAD
=======
    @RequestMapping(value = "home", method = RequestMethod.POST)
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
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
<<<<<<< HEAD
            @RequestParam(value = "cadastrarCartao", required = false) String cadastrar) throws ParseException {
=======
            @RequestParam(value = "cadastrarCartao", required = false) String cadastrar) throws SQLException, ClassNotFoundException, ParseException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

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
<<<<<<< HEAD
                    return SOLICITACAO;
=======
                    return "solicitarReserva";
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
                }
            } catch (ClassNotFoundException | SQLException e) {
                return "emailRegistrado";
            }
        }
        return "cliente";
    }

<<<<<<< HEAD
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fazerLogin(Cliente p) throws SQLException, ClassNotFoundException {
        if (verificarLogin(p.getEmail(), p.getSenha())) {
            return SOLICITACAO;
=======
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
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        } else {
            return "loginIncorreto";
        }
    }

    private boolean verificarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        PessoaDAOImpl dao = new PessoaDAOImpl();
        return dao.selectLogin(email, senha);
    }

<<<<<<< HEAD
    /**
     *
     * @param c
     * @return
     * @throws Exception
     */
    @RequestMapping("cartao")
    public String cadastrarCartao(Cartao c) throws Exception {
=======
    @RequestMapping("cartao")
    public String cadastrarCartao(Cartao c) throws SQLException, ClassNotFoundException, ParseException, Exception {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

        CartaoDAOImpl dao = new CartaoDAOImpl();
        if (ValidaCartao.validCC(c.getNumeroCartao())) {
            dao.insert(c);
<<<<<<< HEAD
            return SOLICITACAO;
=======
            return "solicitarReserva";
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        } else {
            return "cartao-invalido";
        }

    }

}
