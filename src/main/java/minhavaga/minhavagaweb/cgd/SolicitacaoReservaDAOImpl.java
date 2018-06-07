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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minhavagaweb.model.Cliente;
import minhavaga.minhavagaweb.cdp.Pagamento;
import minhavaga.minhavagaweb.cdp.Reserva;
import minhavaga.minhavagaweb.cdp.SolicitacaoReserva;
import minhavaga.minhavagaweb.cdp.Vaga;
import minhavaga.minhavagaweb.utilitarioPersistencia.Conector;

public class SolicitacaoReservaDAOImpl<GenericType> implements GenericDAO<GenericType> {

    private final String SELECT = "SELECT * FROM reserva ";
    private final String INSERT = "INSERT INTO reserva (id_reserva,dataReserva,"
            + "horaReserva,horaChegada,dataSaida,horaSaida,id_cliente, id_vaga,"
            + "id_pagamento) VALUES (?,?,?,?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM reserva WHERE id_reserva = ?;";
    private final String UPDATE = "UPDATE reserva SET (dataReserva,"
            + "horaReserva,horaChegada,dataSaida,horaSaida,id_cliente, id_vaga,"
            + "id_pagamento) = (?,?,?,?,?,?,?,?) WHERE id_reserva = ?;";

    private final String ID_RESERVA = "id_reserva", DATA_R = "dataReserva",
            HORA_R = "horaReserva", HORA_C = "horaChegada", DATA_S = "dataSaida",
            HORA_S = "horaSaida", ID_C = "id_cliente", ID_V = "id_vaga",
            ID_P = "id_pagamento";

    List<SolicitacaoReserva> reservas = new ArrayList<>();

    @Override
    public List<GenericType> getAll() {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
                statement.execute();
                ResultSet result = statement.executeQuery();

                SolicitacaoReserva solicitacaoReserva;
                Reserva reserva;
                while (result.next()) {
                    solicitacaoReserva = new SolicitacaoReserva();
                    reserva = new Reserva();

                    Calendar dataR = Calendar.getInstance();
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
                    reserva.setPagamento(p);

                    solicitacaoReserva.setReserva(reserva);
                    reservas.add(solicitacaoReserva);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SolicitacaoReservaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SolicitacaoReservaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (List<GenericType>) reservas;
    }

    @Override
    public GenericType getById(int id) {
        SolicitacaoReserva solicitacaoReserva = null;
        if (reservas.isEmpty()) {
            reservas = (List<SolicitacaoReserva>) this.getAll();
        }
        for (SolicitacaoReserva sr : reservas) {
            if (sr.getReserva().getId() == id) {
                solicitacaoReserva = sr;
            }
        }
        return (GenericType) solicitacaoReserva;
    }

    @Override
    public void insert(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                int idR = this.getNextId();
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

                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GenericType obj) {
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
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
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GenericType obj) {
        try (Connection connection = Conector.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, ((SolicitacaoReserva) obj).getReserva().getId());
            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getNextId() {
        int res = -0;
        String ORDER = "ORDER BY id_vaga ASC;";
        try (Connection connection = Conector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT + ORDER,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                statement.execute();
                ResultSet result = statement.executeQuery();
                if (result.last()) {
                    res = result.getInt(this.ID_RESERVA);
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
