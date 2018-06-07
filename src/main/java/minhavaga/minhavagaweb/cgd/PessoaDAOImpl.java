/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgd;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.Pessoa;
import minhavaga.minhavagaweb.utilitarioPersistencia.Conector;

public class PessoaDAOImpl<GenericType> implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM cliente ";
    private final String SELECT_LOGIN = "SELECT * FROM cliente where email = ? and senha = ?;";
    private final String INSERT = "INSERT INTO cliente (id_cliente,nome,cpf,"
            + "email,senha,dataNascimento) VALUES(?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM cliente WHERE id_cliente = ?;";
    private final String UPDATE = "UPDATE cliente SET (nome,cpf,"
            + "email,senha,dataNascimento) = (?,?,?,?,?) WHERE id_cliente = ?;";

    private final String ID_CLIENTE = "id_cliente", NOME = "nome", EMAIL = "email",
            SENHA = "senha", CPF = "cpf", DATA_NASCIMENTO = "dataNascimento";

    List<Pessoa> pessoas = new ArrayList<>();

    public void selectLogin(Pessoa p) throws SQLException, ClassNotFoundException, ParseException {

        Connection con = Conector.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(SELECT_LOGIN);

            String email = p.getEmail();
            String senha = p.getSenha();

            statement.setString(1, email);
            statement.setString(2, senha);
            statement.execute();

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                p.setNome(rs.getString(NOME));
                p.setCpf(rs.getString(CPF));
                SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(formatoData.parse(rs.getString(DATA_NASCIMENTO)));
                //p.setNascimento(c);
                //Redirecionar para pagina de reserva
                System.out.println("Olá, " + p.getNome() + "! :D ");

            }

            rs.close();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(">> " + ex);
            //JOptionPane.showMessageDialog(null, "Select Erro!" + ex);
        }

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

            } catch (Exception ex) {
                System.out.println("DEU B.O." + ex.toString());
            }
        } catch (SQLException ex) { // Tratando registro duplicado de EMAIL e CPF

            if (ex.toString().contains("cpf")) {
                out.println("CPF já registrado!");
            } else if (ex.toString().contains("email")) {
                out.println("Email já registrado!");
                //Redirecionar para Recuperar Senha
            }
        } catch (ClassNotFoundException ex) {
            //System.out.println("EXXXXXX: " + ex);
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
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

    /*
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PessoaDAOImpl dao = new PessoaDAOImpl();
        Pessoa p = new Pessoa();
        p.setNome("Helen Franca Medeiros");
        p.setEmail("helen123@gmail.com");
        p.setCpf("14302380705");
        p.setSenha("123456");
        p.setNascimento(Calendar.getInstance());
        //dao.insert(p);
        dao.delete(p);
        //dao.update(p);
    }*/
}
