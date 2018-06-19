/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import minhavagaweb.model.cdp.Cartao;
import java.text.ParseException;
import minhavagaweb.model.cdp.*;
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

    public static final String TELASOLICITACAO = "solicitarReserva";
    public static final String HOMEPAGE = "home";
    public static final String CARTAO_INVALIDO = "cartao-invalido";
    public static final String TELAINDEX = "index";
    public static final String TELACADASTROCLIENTE = "cliente";
    public static final String TELALOGIN = "login";

    @RequestMapping(value = GenController.TELACADASTROCLIENTE, method = RequestMethod.GET)
    public ModelAndView cliente() {
        return new ModelAndView(GenController.TELACADASTROCLIENTE, "command", new Cliente());
    }

    @RequestMapping(HOMEPAGE)
    public ModelAndView home() {
        return new ModelAndView(GenController.HOMEPAGE);
    }

    @RequestMapping(TELASOLICITACAO)
    public ModelAndView solicitarReserva() {
        return new ModelAndView(GenController.TELASOLICITACAO);
    }

    @RequestMapping(TELAINDEX)
    public ModelAndView index() {
        return new ModelAndView(GenController.TELAINDEX);
    }

    @RequestMapping(TELALOGIN)
    public ModelAndView login() {
        return new ModelAndView(GenController.TELALOGIN, "Pessoa", new Pessoa());
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
    public String cadastrarCartao(Cartao c) {
        return AplCliente.cadastrarCartao(c);
    }
}
