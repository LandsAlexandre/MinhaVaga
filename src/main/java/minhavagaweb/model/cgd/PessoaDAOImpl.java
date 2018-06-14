/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.*;
import minhavagaweb.model.utilitarioPersistencia.Conector;

public class PessoaDAOImpl<GenericType> implements GenericDAO<GenericType> {

    private static final String SELECT = "SELECT * FROM cliente ";
    private static final String SELECT_LOGIN = "SELECT email,senha FROM cliente where email = ? and senha = ?;";
    private static final String INSERT = "INSERT INTO cliente (id_cliente,nome,cpf,"
            + "email,senha,dataNascimento) VALUES(?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM cliente WHERE id_cliente = ?;";
    private static final String UPDATE = "UPDATE cliente SET (nome,cpf,"
            + "email,senha,dataNascimento) = (?,?,?,?,?) WHERE id_cliente = ?;";

    private static final String ID_CLIENTE = "id_cliente", NOME = "nome", EMAIL = "email",
            SENHA = "senha", CPF = "cpf", DATA_NASCIMENTO = "dataNascimento";

    List<Pessoa> pessoas = new ArrayList<>();

    public boolean selectLogin(String email, String senha) {
        boolean result = false;
        try {

            Connection con = Conector.getConnection();

            try {
                try (PreparedStatement statement = con.prepareStatement(SELECT_LOGIN)) {
                    statement.setString(1, email);
                    statement.setString(2, senha);

                    try (ResultSet rs = statement.executeQuery()) {
                        result = rs.next();
                    }
                }
            } catch (SQLException ex) {
                System.out.println(">> " + ex);
                //JOptionPane.showMessageDialog(null, "Select Erro!" + ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<GenericType> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
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
            } catch (SQLException ex) {
                Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>) pessoas;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                String nome = ((Pessoa) obj).getNome();
                String cpf = ((Pessoa) obj).getCpf();
                String email = ((Pessoa) obj).getEmail();
                String senha = ((Pessoa) obj).getSenha();
                Date data = ((Pessoa) obj).getNascimento();
                //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date date = new java.sql.Date(data.getTime());

                statement.setInt(1, this.getNextId());
                statement.setString(2, nome);
                statement.setString(3, cpf);
                statement.setString(4, email);
                statement.setString(5, senha);
                statement.setDate(6, date);

                statement.execute();
                System.out.println("Inseriu!");
            } catch (SQLException ex) {

                if (ex.toString().contains("cpf")) {
                    out.println("CPF já registrado!");
                } else if (ex.toString().contains("email")) {
                    System.out.println("Email já registrado!");

                    //Redirecionar para Recuperar Senha
                }

            }
            // Tratando registro duplicado de EMAIL e CPF
            //System.out.println("EXXXXXX: " + ex);

            //System.out.println("EXXXXXX: " + ex);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro de Conexão!");
        }

    }

    public String insert1(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                String nome = ((Pessoa) obj).getNome();
                String cpf = ((Pessoa) obj).getCpf();
                String email = ((Pessoa) obj).getEmail();
                String senha = ((Pessoa) obj).getSenha();
                Date data = ((Pessoa) obj).getNascimento();
                //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date date = new java.sql.Date(data.getTime());

                statement.setInt(1, this.getNextId());
                statement.setString(2, nome);
                statement.setString(3, cpf);
                statement.setString(4, email);
                statement.setString(5, senha);
                statement.setDate(6, date);

                statement.execute();
                System.out.println("Inseriu!");
            } catch (SQLException ex) {

                if (ex.toString().contains("cpf")) {
                    out.println("CPF já registrado!");
                } else if (ex.toString().contains("email")) {
                    System.out.println("Email já registrado!");
                    return "Email já registrado!";
                    //Redirecionar para Recuperar Senha
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro de Conexão!");
        }
        return null;
    }

    @Override
    public void update(GenericType obj) {

        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                String nome = ((Pessoa) obj).getNome();
                String cpf = ((Pessoa) obj).getCpf();
                String email = ((Pessoa) obj).getEmail();
                String senha = ((Pessoa) obj).getSenha();
                //Calendar dataNascimento = ((Pessoa) obj).getNascimento();

                statement.setString(1, nome);
                statement.setString(2, cpf);
                statement.setString(3, email);
                statement.setString(4, senha);
                // statement.setDate(5, new java.sql.Date(dataNascimento.getTimeInMillis()));
                statement.setInt(6, 1);
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((Pessoa) obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericType getById(int id) {
        Pessoa pessoa = null;
        if (pessoas.isEmpty()) {
            pessoas = (List<Pessoa>) this.getAll();
        }
        for (Pessoa c : pessoas) {
            if (c.getId() == id) {
                pessoa = c;
            }
        }
        return (GenericType) pessoa;
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_cliente ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(ID_CLIENTE);
                    return res + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
