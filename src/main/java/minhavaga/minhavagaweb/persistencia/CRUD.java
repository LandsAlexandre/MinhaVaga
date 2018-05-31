/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import minhavaga.minhavagaweb.cdp.*;

/**
 *
 * @author ISM INSERÇÃO OK
 */
public class CRUD {

    public void create(Pessoa p) throws SQLException, ClassNotFoundException {
        Connection con = Conector.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, email, cpf)VALUES( ?, ?, ?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getCpf());
            stmt.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Create Cliente Erro!" + ex);
        } finally {
            Conector.closeConnection(con, stmt);
        }

    }
    
    public void select(Pessoa p) throws SQLException, ClassNotFoundException {
        Connection con = Conector.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from cliente");

            //	executa	um	select
            ResultSet rs = stmt.executeQuery();
            // itera	no	ResultSet
            while (rs.next()) {

                String nome = rs.getString("nome");
                String email = rs.getString("email");

                System.out.println("Nome: " + nome + " ,email = " + email);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Select Erro!" + ex);
        }
    }
}
