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
<<<<<<< HEAD
import minhavagaweb.model.persistencia.Conector;

public class PessoaDAOImpl<G> extends Conector implements GenericDAO<G> {
=======
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class PessoaDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

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
<<<<<<< HEAD
        boolean result;
=======
        boolean result = false;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

        Connection con = this.openConnection();

        PreparedStatement statement = con.prepareStatement(SELECT_LOGIN);
        statement.setString(1, email);
        statement.setString(2, senha);
        ResultSet rs = statement.executeQuery();

        result = rs.next();
        this.closeConnection(con);
        return result;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
<<<<<<< HEAD
    public List<G> getAll() throws SQLException, ClassNotFoundException {
=======
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        Pessoa pessoa;
        while (result.next()) {
            pessoa = new Pessoa();
<<<<<<< HEAD
            pessoa.setNome(result.getString(PessoaDAOImpl.NOME));
            pessoa.setEmail(result.getString(PessoaDAOImpl.EMAIL));
            pessoa.setCpf((String) result.getString(PessoaDAOImpl.CPF));
            pessoa.setSenha(result.getString(PessoaDAOImpl.SENHA));
            pessoa.setId(result.getInt(PessoaDAOImpl.ID_CLIENTE));
            pessoas.add(pessoa);
        }
        this.closeConnection(connection);
        return (List<G>) pessoas;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
=======
            pessoa.setNome(result.getString(this.NOME));
            pessoa.setEmail(result.getString(this.EMAIL));
            pessoa.setCpf((String) result.getString(this.CPF));
            pessoa.setSenha(result.getString(this.SENHA));
            pessoa.setId(result.getInt(this.ID_CLIENTE));
            pessoas.add(pessoa);
        }
        this.closeConnection(connection);
        return (List<GenericType>) pessoas;
    }

    @Override
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Date data = ((Pessoa) obj).getNascimento();
        java.sql.Date date = new java.sql.Date(data.getTime());

<<<<<<< HEAD
        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_CLIENTE));
=======
        statement.setInt(1, this.getNextId());
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        statement.setString(2, ((Pessoa) obj).getNome());
        statement.setString(3, ((Pessoa) obj).getCpf());
        statement.setString(4, ((Pessoa) obj).getEmail());
        statement.setString(5, ((Pessoa) obj).getSenha());
        statement.setDate(6, date);

        boolean stat = statement.execute();
        this.closeConnection(connection);
<<<<<<< HEAD

=======
        
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        return stat;

    }

    @Override
<<<<<<< HEAD
    public void update(G obj) throws SQLException, ClassNotFoundException {
=======
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
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
        this.closeConnection(connection);

    }

    @Override
<<<<<<< HEAD
    public void delete(G obj) throws SQLException, ClassNotFoundException {
=======
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Pessoa) obj).getId());
        statement.execute();
        this.closeConnection(connection);

    }

    @Override
<<<<<<< HEAD
    public G getById(int id) throws ClassNotFoundException, SQLException {
=======
    public GenericType getById(int id) throws ClassNotFoundException, SQLException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection con = this.openConnection();

        Pessoa pessoa = null;
        if (pessoas.isEmpty()) {
            pessoas = (List<Pessoa>) this.getAll();
        }
        for (Pessoa c : pessoas) {
            if (c.getId() == id) {
                pessoa = c;
            }
        }
        this.closeConnection(con);
<<<<<<< HEAD
        return (G) pessoa;
=======
        return (GenericType) pessoa;
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_cliente ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(ID_CLIENTE);
            return res + 1;
        }
        this.closeConnection(connection);
        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }

}
