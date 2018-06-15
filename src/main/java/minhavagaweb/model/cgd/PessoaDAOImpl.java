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
    private static final String SELECT_LOGIN = "SELECT email,senha FROM cliente where email = ? and senha = ?;";
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

    List<Pessoa> pessoas = new ArrayList<>();

    public boolean selectLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        boolean result = false;

        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN);) {
            statement.setString(1, email);
            statement.setString(2, senha);
            result = statement.execute();
        } finally {
            this.closeConnection(con);
        }
        return result;
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

            Pessoa pessoa;
            while (result.next()) {
                pessoa = new Pessoa();
                pessoa.setNome(result.getString(PessoaDAOImpl.NOME));
                pessoa.setEmail(result.getString(PessoaDAOImpl.EMAIL));
                pessoa.setCpf((String) result.getString(PessoaDAOImpl.CPF));
                pessoa.setSenha(result.getString(PessoaDAOImpl.SENHA));
                pessoa.setId(result.getInt(PessoaDAOImpl.ID_CLIENTE));
                pessoas.add(pessoa);
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

            statement.setInt(1, this.getNextId(ORDER, SELECT, ID_CLIENTE));
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

        Pessoa pessoa = null;
        if (pessoas.isEmpty()) {
            pessoas = (List<Pessoa>) this.getAll();
        }
        for (Pessoa c : pessoas) {
            if (c.getId() == id) {
                pessoa = c;
            }
        }
        return (G) pessoa;
    }

}
