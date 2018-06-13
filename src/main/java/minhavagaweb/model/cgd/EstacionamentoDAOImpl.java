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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.Estacionamento;
import minhavagaweb.model.Localizacao;
import minhavagaweb.model.utilitarioPersistencia.Conector;


public class EstacionamentoDAOImpl<GenericType> implements GenericDAO<GenericType> {
    
    private final String SELECT = "SELECT * FROM estacionamento;";
    private final String INSERT = "INSERT INTO estacionamento (id_estacionamento,nome,capacidade,"
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) VALUES(?,?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM estacionamento WHERE id_estacionamento = ?;";
    private final String UPDATE = "UPDATE estacionamento SET (nome,capacidade,"
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) = (?,?,?,?,?) WHERE id_estacionamento = ?;";
    
    private final String ID_ESTACIONAMENTO = "id_estacionamento", NOME = "nome", CAPACIDADE = "capacidade",
            VALOR_HORA = "valor_hora", HORA_ABERTURA = "hora_abre",
            HORA_FECHAMENTO = "hora_fecha", ID_LOCAL = "id_localizacao";

    List<Estacionamento> estacionamentos = new ArrayList<>();
    
    @Override
    public List<GenericType> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                
                Estacionamento estacionamento;
                while (result.next()) {
                    estacionamento = new Estacionamento();
                    estacionamento.setId(result.getInt(ID_ESTACIONAMENTO));
                    estacionamento.setNome(result.getString(NOME));
                    estacionamento.setCapacidade(result.getInt(CAPACIDADE));
                    estacionamento.setValorPorHora(result.getFloat(VALOR_HORA));
                    estacionamento.setHorarioAbertura(result.getTime(HORA_ABERTURA).toLocalTime());
                    estacionamento.setHorarioFechamento(result.getTime(HORA_FECHAMENTO).toLocalTime());
                    
                    LocalizacaoDAOImpl dao = new LocalizacaoDAOImpl();
                    Localizacao local = (Localizacao)dao.getById(result.getInt(ID_LOCAL));
                    estacionamento.setLocal(local);
                    
                    estacionamentos.add(estacionamento);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>)estacionamentos;
    }
    
    @Override
    public GenericType getById(int id) {
        Estacionamento estacionamento = null;
        if (estacionamentos.isEmpty()) {
            estacionamentos = (List<Estacionamento>) this.getAll();
        }
        for (Estacionamento park : estacionamentos) {
            if (park.getId() == id)
                estacionamento = park;
        }
        return (GenericType) estacionamento;
    }
    
    @Override
    public void insert(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                
                String nome = ((Estacionamento)obj).getNome();
                int capacidade = ((Estacionamento)obj).getCapacidade();
                float valor = ((Estacionamento)obj).getValorPorHora();
                LocalTime abertura = ((Estacionamento)obj).getHorarioAbertura();
                LocalTime fechamento = ((Estacionamento)obj).getHorarioFechamento();
                Localizacao local = ((Estacionamento)obj).getLocal();
                
                statement.setInt(1, this.getNextId());
                statement.setString(2, nome);
                statement.setInt(3, capacidade);
                statement.setFloat(4, valor);
                statement.setTime(5, java.sql.Time.valueOf(abertura));
                statement.setTime(6, java.sql.Time.valueOf(fechamento));
                statement.setInt(7, local.getId());
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                
                int id = ((Estacionamento)obj).getId();
                String nome = ((Estacionamento)obj).getNome();
                int capacidade = ((Estacionamento)obj).getCapacidade();
                float valor = ((Estacionamento)obj).getValorPorHora();
                LocalTime abertura = ((Estacionamento)obj).getHorarioAbertura();
                LocalTime fechamento = ((Estacionamento)obj).getHorarioFechamento();
                Localizacao local = ((Estacionamento)obj).getLocal();
                

                statement.setString(1, nome);
                statement.setInt(2, capacidade);
                statement.setFloat(3, valor);
                statement.setTime(4, java.sql.Time.valueOf(abertura));
                statement.setTime(5, java.sql.Time.valueOf(fechamento));
                statement.setInt(6, local.getId());
                statement.setInt(7, id);
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((Estacionamento)obj).getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_estacionamento ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT+ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(ID_ESTACIONAMENTO);
                    return res+1;
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
