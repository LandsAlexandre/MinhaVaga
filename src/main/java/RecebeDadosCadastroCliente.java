
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhavaga.minhavagaweb.cdp.*;
import minhavaga.minhavagaweb.cgt.AplCliente;
import minhavaga.minhavagaweb.valida.ValidaCPF;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 20142bsi0070
 */
@WebServlet("/CadastraCliente")
public class RecebeDadosCadastroCliente extends HttpServlet {

    AplCliente aplCliente = new AplCliente();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {

        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String dataNasc = request.getParameter("data");
        String senha_a = request.getParameter("senha_a");
        String senha_b = request.getParameter("senha_b");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(dataNasc));
        if (senha_a.equals(senha_b)) {

            ValidaCPF validador = new ValidaCPF();
            if ((validador.isValido(cpf))) {

                Pessoa p = new Pessoa();
                p.setNome(nome);
                p.setEmail(email);
                p.setCpf(cpf);
                p.setNascimento(c);
                p.setSenha(senha_a);
                aplCliente.cadastrarCliente(p);
            } else {
                System.out.println("cpf inválido!");
            }
        } else {
            System.out.println("Senha não coincide!");
            //Retornar para tela de Cadastro
        }

        //out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(RecebeDadosCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Recebendo alguma coisa");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeDadosCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(RecebeDadosCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeDadosCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet Recebe Dados";
    }// </editor-fold>

}
