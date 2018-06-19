/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import minhavagaweb.model.cdp.Cartao;
import java.sql.SQLException;
import java.text.ParseException;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.*;
import minhavagaweb.model.cgt.AplCliente;
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
public class GenController {

    private static final String SOLICITACAO = "solicitarReserva";
    private static final String HOMEPAGE = "home";
    private static final String CARTAO_INVALIDO = "cartao-invalido";
    private static final String INDEX = "index";
    private static final String TELACADASTROCLIENTE = "cliente";
    private static final String TELALOGIN = "login";

    @RequestMapping(value = TELACADASTROCLIENTE, method = RequestMethod.GET)
    public ModelAndView cliente() {
        return new ModelAndView(TELACADASTROCLIENTE, "command", new Cliente());
    }

    @RequestMapping(HOMEPAGE)
    public ModelAndView home() {
        return new ModelAndView(HOMEPAGE);
    }

    @RequestMapping(SOLICITACAO)
    public ModelAndView solicitarReserva() {
        return new ModelAndView(SOLICITACAO);
    }

    @RequestMapping(INDEX)
    public ModelAndView index() {
        return new ModelAndView(INDEX);
    }

    @RequestMapping(TELALOGIN)
    public ModelAndView login() {
        return new ModelAndView(TELALOGIN, "Pessoa", new Pessoa());
    }

    @RequestMapping(TELACADASTROCLIENTE)
    public String cadastrarCliente(
            Cliente p, @RequestParam("datanascimento") String datanascimento,
            @RequestParam(value = "cadastrarCartao", required = false) String cadastrar) throws ParseException {
        return AplCliente.cadastrarCliente(p, datanascimento, cadastrar);
    }

    /*
     *
     * @param c
     * @return
     * @throws Exception
     */
    @RequestMapping("cartao")
    public String cadastrarCartao(Cartao c) throws SQLException, ClassNotFoundException {

        CartaoDAOImpl dao = new CartaoDAOImpl();
        if (ValidaCartao.validCC(c.getNumeroCartao()) && dao.insert(c)) {
            return SOLICITACAO;
        } else {
            return CARTAO_INVALIDO;
        }
    }
}