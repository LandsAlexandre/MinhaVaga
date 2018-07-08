/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.persistencia.Conector;

public class PessoaDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM cliente ";
    private static final String SELECT_LOGIN = "SELECT * FROM cliente where email = ? and senha = ?;";
    private static final String INSERT = "INSERT INTO cliente (id_cliente,nome,cpf,"
            + "email,senha,dataNascimento) VALUES(?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM cliente WHERE id_cliente = ?;";
    private static final String UPDATE = "UPDATE cliente SET (nome,cpf,"
            + "email,senha,dataNascimento) = (?,?,?,?,?) WHERE id_cliente = ?;";

    private static final String ID_CLIENTE = "id_cliente";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String CPF = "cpf";
    private static final String ORDER = "ORDER BY id_cliente ASC";
    private static final String DATA = "dataNascimento";

    List<Pessoa> pessoas = new ArrayList<>();
    private static Pessoa pessoa = new Pessoa();
    public boolean selectLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        boolean result = false;

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN);) {
            statement.setString(1, email);
            statement.setString(2, senha);
            try (ResultSet resultadoConsulta = statement.executeQuery()) {
            	result = resultadoConsulta.next();
            	if(result) {
	            	pessoa.setNome(resultadoConsulta.getString(NOME));
	                pessoa.setEmail(resultadoConsulta.getString(EMAIL));
	                pessoa.setCpf((String) resultadoConsulta.getString(CPF));
	                pessoa.setSenha(resultadoConsulta.getString(SENHA));
	                pessoa.setId(resultadoConsulta.getInt(ID_CLIENTE));
	                pessoa.setNascimento(resultadoConsulta.getDate(DATA));
            	}
            }
        } finally {
            this.closeConnection(con);
        }
        return result;
    }
    
    public static Pessoa recuperarPessoa(String email, String senha) throws ClassNotFoundException, SQLException {
		PessoaDAOImpl<Pessoa> dao = new PessoaDAOImpl<>();
    	if (pessoa == null) {
			dao.selectLogin(email, senha);
		}
    	return pessoa;
    }
    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT);
                ResultSet result = statement.executeQuery();) {

            Pessoa pessoa1;
            while (result.next()) {
                pessoa1 = new Pessoa();
                pessoa1.setNome(result.getString(PessoaDAOImpl.NOME));
                pessoa1.setEmail(result.getString(PessoaDAOImpl.EMAIL));
                pessoa1.setCpf((String) result.getString(PessoaDAOImpl.CPF));
                pessoa1.setSenha(result.getString(PessoaDAOImpl.SENHA));
                pessoa1.setId(result.getInt(PessoaDAOImpl.ID_CLIENTE));
                pessoa1.setNascimento(result.getDate(PessoaDAOImpl.DATA));
                pessoas.add(pessoa1);
            }
        } finally {
            this.closeConnection(con);
        }
        return (List<G>) pessoas;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        boolean stat = false;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT);) {

            Date data = ((Pessoa) obj).getNascimento();
            java.sql.Date date = new java.sql.Date(data.getTime());

            statement.setInt(1, this.getNextId(SELECT + ORDER, ID_CLIENTE));
            statement.setString(2, ((Pessoa) obj).getNome());
            statement.setString(3, ((Pessoa) obj).getCpf());
            statement.setString(4, ((Pessoa) obj).getEmail());
            statement.setString(5, ((Pessoa) obj).getSenha());
            statement.setDate(6, date);

            stat = statement.execute();
        } finally {
            this.closeConnection(con);
        }
        return stat;

    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            String nome = ((Pessoa) obj).getNome();
            String cpf = ((Pessoa) obj).getCpf();
            String email = ((Pessoa) obj).getEmail();
            String senha = ((Pessoa) obj).getSenha();

            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, email);
            statement.setString(4, senha);
            statement.setInt(6, 1);
            statement.execute();
        } finally {
            this.closeConnection(con);
        }

    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, ((Pessoa) obj).getId());
            statement.execute();
        } finally {
            this.closeConnection(con);
        }

    }

    @Override
    public G getById(int id) throws ClassNotFoundException, SQLException {

        Pessoa pessoa2 = null;
        if (pessoas.isEmpty()) {
            pessoas = (List<Pessoa>) this.getAll();
        }
        for (Pessoa c : pessoas) {
            if (c.getId() == id) {
                pessoa2 = c;
            }
        }
        return (G) pessoa2;
    }

}
