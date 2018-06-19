/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import javax.servlet.http.HttpSession;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cgt.AplCliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author landerson
 */
@Controller
public class LoginController {
    public static final String EFETUALOGIN = "efetuaLogin";
    
    @RequestMapping(EFETUALOGIN)
    public String fazerLogin(Cliente p, HttpSession session) {
        return AplCliente.fazerLogin(p, session);
    }
}
