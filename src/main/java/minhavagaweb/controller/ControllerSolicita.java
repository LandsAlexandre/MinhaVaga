/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cgt.AplSolicitacao;

/**
 *
 * @author ISM
 */
@Controller
public class ControllerSolicita {
	public static final String TELACONFIRMARSOLICITACAO = "confirmaSolicitacao";
	public static final String TELAMINHASRESERVAS = "reservas"; 
    @RequestMapping(value = "encontrarVaga")
    public String encontrarVaga(@RequestParam("selectLocal") int estacionamento, @RequestParam("selectTipo") 
            int tipo) throws ClassNotFoundException {
    	return AplSolicitacao.encontrarVaga(estacionamento, tipo);
    }
    
    @RequestMapping(value = "solicitacaoConfirmada")
    public String confirmada(Model model, HttpSession sessao) {
        AplSolicitacao.confirmarSolicitacao();
        Cliente p = (Cliente) sessao.getAttribute(LoginInterceptor.USERLOGGED);
        model.addAttribute("nome", p.getNome());
        return GenController.HOMEPAGE;
    }
    

    @RequestMapping(TELAMINHASRESERVAS)
    public ModelAndView minhasReservas() {
    	return new ModelAndView(TELAMINHASRESERVAS);
    }

}
