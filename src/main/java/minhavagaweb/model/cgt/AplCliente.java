/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgt;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import minhavagaweb.model.cdp.CPF;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.Email;
import minhavagaweb.model.cgd.PessoaDAOImpl;

/**
 *
 * @author landerson
 */
public class AplCliente {
    private static final String TELACADASTROCARTAO = "cartao";
    private static final String TELACADASTROCLIENTE = "cliente";
    private static final String TELASOLICITACAO = "solicitarReserva";
    private static final String TELAEMAILREGISTRADO = "emailRegistrado";
    
    private AplCliente() {
        throw new IllegalStateException("AplCliente");
    }
    
    public static String cadastrarCliente(
            Cliente p, String datanascimento,
            String cadastrar) throws ParseException {

        PessoaDAOImpl dao = new PessoaDAOImpl();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(formato.parse(datanascimento).getTime());
        p.setNascimento(data);

        if (CPF.isCPFValido(p.getCpf()) && Email.isEmailValido(p.getEmail())) {
            try {
                dao.insert(p);
                if (cadastrar != null) {
                    return TELACADASTROCARTAO;
                } else {
                    return TELASOLICITACAO;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return TELAEMAILREGISTRADO;
            }
        }
        return TELACADASTROCLIENTE;
    }
}
