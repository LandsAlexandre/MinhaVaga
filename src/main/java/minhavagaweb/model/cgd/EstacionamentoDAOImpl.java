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
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.persistencia.Conector;

public class EstacionamentoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM estacionamento;";
    private static final String INSERT = "INSERT INTO estacionamento (id_estacionamento,nome,capacidade,"
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) VALUES(?,?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM estacionamento WHERE id_estacionamento = ?;";
    private static final String UPDATE = "UPDATE estacionamento SET (nome,capacidade,"
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) = (?,?,?,?,?,?) WHERE id_estacionamento = ?;";

    private static final String ID_ESTACIONAMENTO = "id_estacionamento";
    private static final String NOME = "nome";
    private static final String CAPACIDADE = "capacidade";
    private static final String VALOR_HORA = "valor_hora";
    private static final String HORA_ABERTURA = "hora_abre";
    private static final String HORA_FECHAMENTO = "hora_fecha";
    private static final String ID_LOCAL = "id_localizacao";
    private static final String ORDER = "ORDER BY id_estacionamento ASC";

    List<Estacionamento> estacionamentos = new ArrayList<>();

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT);
                ResultSet result = statement.executeQuery();) {

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
                Localizacao local = (Localizacao) dao.getById(result.getInt(ID_LOCAL));
                estacionamento.setLocal(local);

                estacionamentos.add(estacionamento);
            }
        } finally {
            this.closeConnection(con);
        }

        return (List<G>) estacionamentos;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
        Estacionamento estacionamento = null;
        if (estacionamentos.isEmpty()) {
            estacionamentos = (List<Estacionamento>) this.getAll();
        }
        for (Estacionamento park : estacionamentos) {
            if (park.getId() == id) {
                estacionamento = park;
            }
        }
        return (G) estacionamento;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        boolean stat = false;
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT);) {

            String nome = ((Estacionamento) obj).getNome();
            int capacidade = ((Estacionamento) obj).getCapacidade();
            float valor = ((Estacionamento) obj).getValorPorHora();
            LocalTime abertura = ((Estacionamento) obj).getHorarioAbertura();
            LocalTime fechamento = ((Estacionamento) obj).getHorarioFechamento();
            Localizacao local = ((Estacionamento) obj).getLocal();

            statement.setInt(1, this.getNextId(SELECT + ORDER, ID_ESTACIONAMENTO));
            statement.setString(2, nome);
            statement.setInt(3, capacidade);
            statement.setFloat(4, valor);
            statement.setTime(5, java.sql.Time.valueOf(abertura));
            statement.setTime(6, java.sql.Time.valueOf(fechamento));
            statement.setInt(7, local.getId());
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

            int id = ((Estacionamento) obj).getId();
            String nome = ((Estacionamento) obj).getNome();
            int capacidade = ((Estacionamento) obj).getCapacidade();
            float valor = ((Estacionamento) obj).getValorPorHora();
            LocalTime abertura = ((Estacionamento) obj).getHorarioAbertura();
            LocalTime fechamento = ((Estacionamento) obj).getHorarioFechamento();
            Localizacao local = ((Estacionamento) obj).getLocal();

            statement.setString(1, nome);
            statement.setInt(2, capacidade);
            statement.setFloat(3, valor);
            statement.setTime(4, java.sql.Time.valueOf(abertura));
            statement.setTime(5, java.sql.Time.valueOf(fechamento));
            statement.setInt(6, local.getId());
            statement.setInt(7, id);
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        try (Connection connection = this.openConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, ((Estacionamento) obj).getId());
            statement.execute();
        } finally {
            this.closeConnection(con);
        }
    }

}
