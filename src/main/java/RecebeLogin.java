
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhavaga.minhavagaweb.cdp.Pessoa;
import minhavaga.minhavagaweb.cgt.AplCliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ISM
 */
@WebServlet("/Login")
public class RecebeLogin extends HttpServlet {

    AplCliente aplCliente = new AplCliente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException, ParseException {
        //PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Pessoa p = new Pessoa();
        p.setEmail(email);
        p.setSenha(senha);

        //Fazer alteração aqui, mas antes verificar com DAO
        aplCliente.fazerLogin(p);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet Recebe Dados";
    }// </editor-fold>

}
