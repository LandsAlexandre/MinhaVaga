package minhavagaweb.model.cgd;

import minhavagaweb.model.cdp.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import minhavagaweb.model.persistencia.Conector;

public class SolicitacaoReservaDAOImpl<G> extends Conector implements GenericDAO<G> {

    private static final String SELECT = "SELECT * FROM reserva ";
    private static final String INSERT = "INSERT INTO reserva (id_reserva,dataReserva,"
            + "horaReserva,horaChegada,dataSaida,horaSaida,id_cliente, id_vaga,"
            + "id_pagamento) VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String DELETE = "DELETE FROM reserva WHERE id_reserva = ?;";
    private static final String UPDATE = "UPDATE reserva SET (dataReserva,"
            + "horaReserva,horaChegada,dataSaida,horaSaida,id_cliente, id_vaga,"
            + "id_pagamento) = (?,?,?,?,?,?,?,?) WHERE id_reserva = ?;";

    private static final String ID_RESERVA = "id_reserva";
    private static final String DATA_R = "dataReserva";
    private static final String HORA_R = "horaReserva";
    private static final String HORA_C = "horaChegada";
    private static final String DATA_S = "dataSaida";
    private static final String HORA_S = "horaSaida";
    private static final String ID_C = "id_cliente";
    private static final String ID_V = "id_vaga";
    private static final String ID_P = "id_pagamento";
    private static final String ORDER = "ORDER BY id_reserva ASC";

    List<SolicitacaoReserva> reservas = new ArrayList<>();

    @Override
    public List<G> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            SolicitacaoReserva solicitacaoReserva = new SolicitacaoReserva();
            Reserva reserva = new Reserva();

            Calendar dataR = Calendar.getInstance();
            dataR.setTime(result.getDate(SolicitacaoReservaDAOImpl.DATA_R));

            solicitacaoReserva.setDataSolicitacao(dataR);
            solicitacaoReserva.setHoraSolicitacao(result.getTime(SolicitacaoReservaDAOImpl.HORA_R).toLocalTime());

            reserva.setId(result.getInt(SolicitacaoReservaDAOImpl.ID_RESERVA));
            reserva.setDataChegada(dataR);
            reserva.setHoraChegada(result.getTime(SolicitacaoReservaDAOImpl.HORA_C).toLocalTime());

            dataR.setTime(result.getDate(SolicitacaoReservaDAOImpl.DATA_S));
            reserva.setDataSaida(dataR);
            reserva.setHoraSaida(result.getTime(SolicitacaoReservaDAOImpl.HORA_S).toLocalTime());

            PessoaDAOImpl dao1 = new PessoaDAOImpl();
            Cliente c = (Cliente) dao1.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_C));
            reserva.setCliente(c);

            VagaDAOImpl dao2 = new VagaDAOImpl();
            Vaga v = (Vaga) dao2.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_V));
            reserva.setVagaReservada(v);

            PagamentoDAOImpl dao3 = new PagamentoDAOImpl();
            Pagamento p = (Pagamento) dao3.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_P));
            reserva.setPagamento(p);

            solicitacaoReserva.setReserva(reserva);
            reservas.add(solicitacaoReserva);
        }
        this.closeConnection(connection);
        return (List<G>) reservas;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
        SolicitacaoReserva solicitacaoReserva = null;
        if (reservas.isEmpty()) {
            reservas = (List<SolicitacaoReserva>) this.getAll();
        }
        for (SolicitacaoReserva sr : reservas) {
            if (sr.getReserva().getId() == id) {
                solicitacaoReserva = sr;
            }
        }
        return (G) solicitacaoReserva;
    }

    @Override
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        int idR = this.getNextId(ORDER, SELECT, ID_RESERVA);

        Calendar dataR = ((SolicitacaoReserva) obj).getDataSolicitacao();
        LocalTime horaR = ((SolicitacaoReserva) obj).getHoraSolicitacao();
        LocalTime horaC = ((SolicitacaoReserva) obj).getReserva().getHoraChegada();
        Calendar dataS = ((SolicitacaoReserva) obj).getReserva().getDataSaida();
        LocalTime horaS = ((SolicitacaoReserva) obj).getReserva().getHoraSaida();

        int idC = ((SolicitacaoReserva) obj).getReserva().getCliente().getId();
        int idV = ((SolicitacaoReserva) obj).getReserva().getVagaReservada().getId();
        int idP = ((SolicitacaoReserva) obj).getReserva().getPagamento().getId();

        statement.setInt(1, idR);
        statement.setDate(2, new java.sql.Date(dataR.getTimeInMillis()));
        statement.setTime(3, java.sql.Time.valueOf(horaR));
        statement.setTime(4, java.sql.Time.valueOf(horaC));
        statement.setDate(5, new java.sql.Date(dataS.getTimeInMillis()));
        statement.setTime(6, java.sql.Time.valueOf(horaS));
        statement.setInt(7, idC);
        statement.setInt(8, idV);
        statement.setInt(9, idP);

        boolean stat = statement.execute();

        this.closeConnection(connection);

        return stat;

    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        int idR = ((SolicitacaoReserva) obj).getReserva().getId();
        Calendar dataR = ((SolicitacaoReserva) obj).getDataSolicitacao();
        LocalTime horaR = ((SolicitacaoReserva) obj).getHoraSolicitacao();
        LocalTime horaC = ((SolicitacaoReserva) obj).getReserva().getHoraChegada();
        Calendar dataS = ((SolicitacaoReserva) obj).getReserva().getDataSaida();
        LocalTime horaS = ((SolicitacaoReserva) obj).getReserva().getHoraSaida();
        int idC = ((SolicitacaoReserva) obj).getReserva().getCliente().getId();
        int idV = ((SolicitacaoReserva) obj).getReserva().getVagaReservada().getId();
        int idP = ((SolicitacaoReserva) obj).getReserva().getPagamento().getId();

        statement.setDate(1, new java.sql.Date(dataR.getTimeInMillis()));
        statement.setTime(2, java.sql.Time.valueOf(horaR));
        statement.setTime(3, java.sql.Time.valueOf(horaC));
        statement.setDate(4, new java.sql.Date(dataS.getTimeInMillis()));
        statement.setTime(5, java.sql.Time.valueOf(horaS));
        statement.setInt(6, idC);
        statement.setInt(7, idV);
        statement.setInt(8, idP);
        statement.setInt(9, idR);

        statement.executeQuery();
        this.closeConnection(connection);

    }

    @Override
    public void delete(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((SolicitacaoReserva) obj).getReserva().getId());
        statement.execute();
        this.closeConnection(connection);
    }
}
