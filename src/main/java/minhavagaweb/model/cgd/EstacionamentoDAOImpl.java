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
<<<<<<< HEAD
import minhavagaweb.model.persistencia.Conector;

public class EstacionamentoDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM estacionamento;";
    private static final String INSERT = "INSERT INTO estacionamento (id_estacionamento,nome,capacidade,"
=======
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class EstacionamentoDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM estacionamento;";
    private final String INSERT = "INSERT INTO estacionamento (id_estacionamento,nome,capacidade,"
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) VALUES(?,?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM estacionamento WHERE id_estacionamento = ?;";
    private static final String UPDATE = "UPDATE estacionamento SET (nome,capacidade,"
            + "valor_hora,hora_abre,hora_fecha,id_localizacao) = (?,?,?,?,?) WHERE id_estacionamento = ?;";

<<<<<<< HEAD
    private static final String ID_ESTACIONAMENTO = "id_estacionamento";
    private static final String NOME = "nome";
    private static final String CAPACIDADE = "capacidade";
    private static final String VALOR_HORA = "valor_hora";
    private static final String HORA_ABERTURA = "hora_abre";
    private static final String HORA_FECHAMENTO = "hora_fecha";
    private static final String ID_LOCAL = "id_localizacao";
    private static final String ORDER = "ORDER BY id_estacionamento ASC";
=======
    private final String ID_ESTACIONAMENTO = "id_estacionamento", NOME = "nome", CAPACIDADE = "capacidade",
            VALOR_HORA = "valor_hora", HORA_ABERTURA = "hora_abre",
            HORA_FECHAMENTO = "hora_fecha", ID_LOCAL = "id_localizacao";
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

    List<Estacionamento> estacionamentos = new ArrayList<>();

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
        this.closeConnection(connection);
<<<<<<< HEAD
        return (List<G>) estacionamentos;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
        return (List<GenericType>) estacionamentos;
    }

    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Estacionamento estacionamento = null;
        if (estacionamentos.isEmpty()) {
            estacionamentos = (List<Estacionamento>) this.getAll();
        }
        for (Estacionamento park : estacionamentos) {
            if (park.getId() == id) {
                estacionamento = park;
            }
        }
<<<<<<< HEAD
        return (G) estacionamento;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

=======
        return (GenericType) estacionamento;
    }

    @Override
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);

        String nome = ((Estacionamento) obj).getNome();
        int capacidade = ((Estacionamento) obj).getCapacidade();
        float valor = ((Estacionamento) obj).getValorPorHora();
        LocalTime abertura = ((Estacionamento) obj).getHorarioAbertura();
        LocalTime fechamento = ((Estacionamento) obj).getHorarioFechamento();
        Localizacao local = ((Estacionamento) obj).getLocal();

        statement.setInt(1, this.getNextId());
        statement.setString(2, nome);
        statement.setInt(3, capacidade);
        statement.setFloat(4, valor);
        statement.setTime(5, java.sql.Time.valueOf(abertura));
        statement.setTime(6, java.sql.Time.valueOf(fechamento));
        statement.setInt(7, local.getId());
        boolean stat = statement.execute();
        
        this.closeConnection(connection);
        return stat;
    }

    @Override
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        int id = ((Estacionamento) obj).getId();
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        String nome = ((Estacionamento) obj).getNome();
        int capacidade = ((Estacionamento) obj).getCapacidade();
        float valor = ((Estacionamento) obj).getValorPorHora();
        LocalTime abertura = ((Estacionamento) obj).getHorarioAbertura();
        LocalTime fechamento = ((Estacionamento) obj).getHorarioFechamento();
        Localizacao local = ((Estacionamento) obj).getLocal();

<<<<<<< HEAD
        statement.setInt(1, this.getNextId(ORDER, SELECT, ID_ESTACIONAMENTO));
        statement.setString(2, nome);
        statement.setInt(3, capacidade);
        statement.setFloat(4, valor);
        statement.setTime(5, java.sql.Time.valueOf(abertura));
        statement.setTime(6, java.sql.Time.valueOf(fechamento));
        statement.setInt(7, local.getId());
        boolean stat = statement.execute();

        this.closeConnection(connection);
        return stat;
    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

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

        this.closeConnection(connection);
    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Estacionamento) obj).getId());
        statement.execute();

=======
        statement.setString(1, nome);
        statement.setInt(2, capacidade);
        statement.setFloat(3, valor);
        statement.setTime(4, java.sql.Time.valueOf(abertura));
        statement.setTime(5, java.sql.Time.valueOf(fechamento));
        statement.setInt(6, local.getId());
        statement.setInt(7, id);
        statement.execute();

        this.closeConnection(connection);
    }

    @Override
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((Estacionamento) obj).getId());
        statement.execute();

    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_estacionamento ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(ID_ESTACIONAMENTO);
            return res + 1;
        }

        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }

}
