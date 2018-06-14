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
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class PessoaDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM cliente ";
    private final String SELECT_LOGIN = "SELECT email,senha FROM cliente where email = ? and senha = ?;";
    private final String INSERT = "INSERT INTO cliente (id_cliente,nome,cpf,"
            + "email,senha,dataNascimento) VALUES(?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM cliente WHERE id_cliente = ?;";
    private final String UPDATE = "UPDATE cliente SET (nome,cpf,"
            + "email,senha,dataNascimento) = (?,?,?,?,?) WHERE id_cliente = ?;";

    private final String ID_CLIENTE = "id_cliente", NOME = "nome", EMAIL = "email",
            SENHA = "senha", CPF = "cpf", DATA_NASCIMENTO = "dataNascimento";

    List<Pessoa> pessoas = new ArrayList<>();

    public boolean selectLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        boolean result = false;

        Connection con = this.openConnection();

        PreparedStatement statement = con.prepareStatement(SELECT_LOGIN);
        statement.setString(1, email);
        statement.setString(2, senha);
        ResultSet rs = statement.executeQuery();

        result = rs.next();
        this.closeConnection(con);
        return result;
    }

    @Override
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {

        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        Pessoa pessoa;
        while (result.next()) {
            pessoa = new Pessoa();
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
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        Date data = ((Pessoa) obj).getNascimento();
        java.sql.Date date = new java.sql.Date(data.getTime());

        statement.setInt(1, this.getNextId());
        statement.setString(2, ((Pessoa) obj).getNome());
        statement.setString(3, ((Pessoa) obj).getCpf());
        statement.setString(4, ((Pessoa) obj).getEmail());
        statement.setString(5, ((Pessoa) obj).getSenha());
        statement.setDate(6, date);

        boolean stat = statement.execute();
        this.closeConnection(connection);
        
        return stat;

    }

    @Override
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {

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
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {

        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Pessoa) obj).getId());
        statement.execute();
        this.closeConnection(connection);

    }

    @Override
    public GenericType getById(int id) throws ClassNotFoundException, SQLException {
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
    }

}
