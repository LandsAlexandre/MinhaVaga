/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgt;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import minhavagaweb.controller.GenController;
import minhavagaweb.controller.LoginInterceptor;
import minhavagaweb.model.cdp.CPF;
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.Email;
import minhavagaweb.model.cdp.ValidaCartao;
import minhavagaweb.model.cgd.CartaoDAOImpl;
import minhavagaweb.model.cgd.PessoaDAOImpl;

/**
 *
 * @author landerson
 */
public class AplCliente {

    public static final String TELACADASTROCARTAO = "cartao";
    public static final String TELAEMAILREGISTRADO = "emailRegistrado";

    private AplCliente() {
        throw new IllegalStateException("AplCliente");
    }

    public static String cadastrarCliente(
            Cliente p, String datanascimento,
            String cadastrar) throws ParseException {

        PessoaDAOImpl<Cliente> dao = new PessoaDAOImpl<>();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(formato.parse(datanascimento).getTime());
        p.setNascimento(data);

        if (CPF.isCPFValido(p.getCpf()) && Email.isEmailValido(p.getEmail())) {
            try {
                dao.insert(p);
                if (cadastrar != null) {
                    return AplCliente.TELACADASTROCARTAO;
                } else {
                    return GenController.TELASOLICITACAO;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return TELAEMAILREGISTRADO;
            }
        }
        return GenController.TELACADASTROCLIENTE;
    }

    public static String fazerLogin(Cliente p, HttpSession session) {
        if (verificarLogin(p.getEmail(), p.getSenha())) {
            session.setAttribute(LoginInterceptor.USERLOGGED, p);
            return GenController.TELASOLICITACAO;
        } else {
            return LoginInterceptor.LOGININCORRETO;
        }
    }

    private static boolean verificarLogin(String email, String senha) {
        PessoaDAOImpl<Cliente> dao = new PessoaDAOImpl<>();
        Boolean result;
        try {
            result = dao.selectLogin(email, senha);
        } catch (ClassNotFoundException | SQLException e) {
            result = false;
        }
        return result;
    }

    public static String cadastrarCartao(Cartao c) {
        CartaoDAOImpl<Cartao> dao = new CartaoDAOImpl<>();
        String pagina;
        if (ValidaCartao.validCC(c.getNumeroCartao())) {
            try {
                dao.insert(c);
            }
            catch (ClassNotFoundException | SQLException e) {
                return AplCliente.TELACADASTROCARTAO;
            }
            pagina = GenController.TELASOLICITACAO;
        } else {
            pagina = GenController.CARTAO_INVALIDO;
        }
        return pagina;
    }
}
