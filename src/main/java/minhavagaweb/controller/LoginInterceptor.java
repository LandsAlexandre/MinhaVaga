/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author landerson
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String USERLOGGED = "usuarioLogado";
    private static final String LOGIN = "login";
    private static final String CLIENTE = "cliente";
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object controller) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith(LOGIN) || uri.endsWith(CLIENTE)) {
            return true;
        }
        else if (request.getSession().getAttribute(USERLOGGED) != null) {
            request.setAttribute(USERLOGGED, request.getSession().getAttribute(USERLOGGED));
            return true;
        }
        response.sendRedirect(LOGIN);
        return false;
    }
}
