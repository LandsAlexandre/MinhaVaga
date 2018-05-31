/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavaga.minhavagaweb.cdp.Cliente;
import minhavaga.minhavagaweb.persistencia.Conector;


public class ClienteDAOImpl<GenericType> implements GenericDAO<GenericType> {
    private final String SELECT = "SELECT * FROM cliente;";
    private final String INSERT = "INSERT INTO cliente (id_cliente,nome,cpf,"
            + "email,senha,dataNascimento) VALUES(?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM cliente WHERE id_cliente = ?;";
    private final String UPDATE = "UPDATE cliente SET (nome,cpf,"
            + "email,senha,dataNascimento) = (?,?,?,?,?) WHERE id_cliente = ?;";
    
    private final String NOME = "nome", EMAIL = "email",
            SENHA = "senha", CPF = "cpf", DATA_NASCIMENTO = "dataNascimento";
    
    
    List<Cliente> clientes;
    
    @Override
    public List<GenericType> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                
                Cliente cliente;
                while (result.next()) {
                    cliente = new Cliente();
                    cliente.Pessoa().setNome(result.getString(NOME));
                    cliente.Pessoa().setEmail(result.getString(EMAIL));
                    cliente.Pessoa().setCpf((String)result.getString(CPF));
                    cliente.Pessoa().setSenha(result.getString(SENHA));
                    cliente.setId(0);                    
                    clientes.add(cliente);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>)clientes;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                String nome = ((Cliente)obj).getNome();
                String cpf = ((Cliente)obj).getCpf();
                String email = ((Cliente)obj).getEmail();
                String senha = ((Cliente)obj).getSenha();
                Calendar dataNascimento = ((Cliente)obj).getNascimento();
                
                statement.setInt(1, 1);
                statement.setString(2, nome);
                statement.setString(3, cpf);
                statement.setString(4, email);
                statement.setString(5, senha);
                statement.setDate(6, new java.sql.Date(dataNascimento.getTimeInMillis()));
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                String nome = ((Cliente)obj).getNome();
                String cpf = ((Cliente)obj).getCpf();
                String email = ((Cliente)obj).getEmail();
                String senha = ((Cliente)obj).getSenha();
                Calendar dataNascimento = ((Cliente)obj).getNascimento();
                
                statement.setString(1, nome);
                statement.setString(2, cpf);
                statement.setString(3, email);
                statement.setString(4, senha);
                statement.setDate(5, new java.sql.Date(dataNascimento.getTimeInMillis()));
                statement.setInt(6, 1);
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((Cliente)obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}