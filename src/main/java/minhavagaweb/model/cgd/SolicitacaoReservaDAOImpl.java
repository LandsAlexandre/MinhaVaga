/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import minhavagaweb.model.cdp.Vaga;
<<<<<<< HEAD
import minhavagaweb.model.cdp.Reserva;
import minhavagaweb.model.cdp.SolicitacaoReserva;
=======
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
<<<<<<< HEAD
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.persistencia.Conector;

public class SolicitacaoReservaDAOImpl<G> extends Conector implements GenericDAO<G> {
=======
import minhavagaweb.model.*;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.utilitarioPersistencia.DAOGeneric;

public class SolicitacaoReservaDAOImpl<GenericType> extends DAOGeneric implements GenericDAO<GenericType> {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8

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
<<<<<<< HEAD
    public List<G> getAll() throws SQLException, ClassNotFoundException {
=======
    public List<GenericType> getAll() throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.execute();
        ResultSet result = statement.executeQuery();

        SolicitacaoReserva solicitacaoReserva;
        Reserva reserva;
        while (result.next()) {
            solicitacaoReserva = new SolicitacaoReserva();
            reserva = new Reserva();

            Calendar dataR = Calendar.getInstance();
<<<<<<< HEAD
            dataR.setTime(result.getDate(SolicitacaoReservaDAOImpl.DATA_R));

            solicitacaoReserva.setDataSolicitacao(dataR);
            solicitacaoReserva.setHoraSolicitacao(
                    result.getTime(SolicitacaoReservaDAOImpl.HORA_R).toLocalTime());

            reserva.setId(result.getInt(SolicitacaoReservaDAOImpl.ID_RESERVA));
            reserva.setDataChegada(dataR);
            reserva.setHoraChegada(result.getTime(
                    SolicitacaoReservaDAOImpl.HORA_C).toLocalTime());

            dataR.setTime(result.getDate(SolicitacaoReservaDAOImpl.DATA_S));
            reserva.setDataSaida(dataR);
            reserva.setHoraSaida(
                    result.getTime(SolicitacaoReservaDAOImpl.HORA_S).toLocalTime());

            PessoaDAOImpl dao1 = new PessoaDAOImpl();
            Cliente c = (Cliente) dao1.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_C));
            reserva.setCliente(c);

            VagaDAOImpl dao2 = new VagaDAOImpl();
            Vaga v = (Vaga) dao2.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_V));
            reserva.setVagaReservada(v);

            PagamentoDAOImpl dao3 = new PagamentoDAOImpl();
            Pagamento p = (Pagamento) dao3.getById(result.getInt(SolicitacaoReservaDAOImpl.ID_P));
=======
            dataR.setTime(result.getDate(this.DATA_R));

            solicitacaoReserva.setDataSolicitacao(dataR);
            solicitacaoReserva.setHoraSolicitacao(
                    result.getTime(this.HORA_R).toLocalTime());

            reserva.setId(result.getInt(this.ID_RESERVA));
            reserva.setDataChegada(dataR);
            reserva.setHoraChegada(result.getTime(
                    this.HORA_C).toLocalTime());

            dataR.setTime(result.getDate(this.DATA_S));
            reserva.setDataSaida(dataR);
            reserva.setHoraSaida(
                    result.getTime(this.HORA_S).toLocalTime());

            PessoaDAOImpl dao1 = new PessoaDAOImpl();
            Cliente c = (Cliente) dao1.getById(result.getInt(this.ID_C));
            reserva.setCliente(c);

            VagaDAOImpl dao2 = new VagaDAOImpl();
            Vaga v = (Vaga) dao2.getById(result.getInt(this.ID_V));
            reserva.setVagaReservada(v);

            PagamentoDAOImpl dao3 = new PagamentoDAOImpl();
            Pagamento p = (Pagamento) dao3.getById(result.getInt(this.ID_P));
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
            reserva.setPagamento(p);

            solicitacaoReserva.setReserva(reserva);
            reservas.add(solicitacaoReserva);
        }
        this.closeConnection(connection);
<<<<<<< HEAD
        return (List<G>) reservas;
    }

    @Override
    public G getById(int id) throws SQLException, ClassNotFoundException {
=======
        return (List<GenericType>) reservas;
    }

    @Override
    public GenericType getById(int id) throws SQLException, ClassNotFoundException {
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
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
<<<<<<< HEAD
    public boolean insert(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        int idR = this.getNextId(ORDER, SELECT, ID_RESERVA);
=======
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        int idR = this.getNextId();
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
        Calendar dataR = ((SolicitacaoReserva) obj).
                getDataSolicitacao();
        LocalTime horaR = ((SolicitacaoReserva) obj).
                getHoraSolicitacao();
        LocalTime horaC = ((SolicitacaoReserva) obj).getReserva()
                .getHoraChegada();
        Calendar dataS = ((SolicitacaoReserva) obj).getReserva()
                .getDataSaida();
        LocalTime horaS = ((SolicitacaoReserva) obj).getReserva()
                .getHoraSaida();
        int idC = ((SolicitacaoReserva) obj).getReserva().getCliente()
                .getId();
        int idV = ((SolicitacaoReserva) obj).getReserva().getVagaReservada()
                .getId();
        int idP = ((SolicitacaoReserva) obj).getReserva().getPagamento()
                .getId();

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
<<<<<<< HEAD

    }

    @Override
    public void update(G obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        int idR = ((SolicitacaoReserva) obj).getReserva().getId();
        Calendar dataR = ((SolicitacaoReserva) obj).
                getDataSolicitacao();
        LocalTime horaR = ((SolicitacaoReserva) obj).
                getHoraSolicitacao();
        LocalTime horaC = ((SolicitacaoReserva) obj).getReserva()
                .getHoraChegada();
        Calendar dataS = ((SolicitacaoReserva) obj).getReserva()
                .getDataSaida();
        LocalTime horaS = ((SolicitacaoReserva) obj).getReserva()
                .getHoraSaida();
        int idC = ((SolicitacaoReserva) obj).getReserva().getCliente()
                .getId();
        int idV = ((SolicitacaoReserva) obj).getReserva().getVagaReservada()
                .getId();
        int idP = ((SolicitacaoReserva) obj).getReserva().getPagamento()
                .getId();

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
=======

    }

    @Override
    public void update(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        int idR = ((SolicitacaoReserva) obj).getReserva().getId();
        Calendar dataR = ((SolicitacaoReserva) obj).
                getDataSolicitacao();
        LocalTime horaR = ((SolicitacaoReserva) obj).
                getHoraSolicitacao();
        LocalTime horaC = ((SolicitacaoReserva) obj).getReserva()
                .getHoraChegada();
        Calendar dataS = ((SolicitacaoReserva) obj).getReserva()
                .getDataSaida();
        LocalTime horaS = ((SolicitacaoReserva) obj).getReserva()
                .getHoraSaida();
        int idC = ((SolicitacaoReserva) obj).getReserva().getCliente()
                .getId();
        int idV = ((SolicitacaoReserva) obj).getReserva().getVagaReservada()
                .getId();
        int idP = ((SolicitacaoReserva) obj).getReserva().getPagamento()
                .getId();

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
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException {
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, ((SolicitacaoReserva) obj).getReserva().getId());
        statement.execute();
        this.closeConnection(connection);
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        int res = -0;
        String ORDER = "ORDER BY id_vaga ASC;";
        Connection connection = this.openConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute();
        ResultSet result = statement.executeQuery();
        if (result.last()) {
            res = result.getInt(this.ID_RESERVA);
            return res + 1;
        }

        this.closeConnection(connection);
        return res;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
    }
}