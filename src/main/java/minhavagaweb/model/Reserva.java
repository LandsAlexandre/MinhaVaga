/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model;

import minhavagaweb.model.cdp.Vaga;
import java.time.LocalTime;
import java.util.Calendar;
<<<<<<< HEAD:src/main/java/minhavagaweb/model/cdp/Reserva.java
=======
import minhavagaweb.model.cdp.*;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8:src/main/java/minhavagaweb/model/Reserva.java

/**
 *
 * @author landerson
 */
public class Reserva {
    private int id;
    private LocalTime horaChegada;
    private LocalTime horaSaida;
    private Calendar dataChegada;
    private Calendar dataSaida;

    private Vaga vagaReservada;
    private Cliente cliente;
    private Pagamento pagamento;

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Calendar getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Calendar dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Calendar getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Calendar dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Vaga getVagaReservada() {
        return vagaReservada;
    }

    public void setVagaReservada(Vaga vagaReservada) {
        this.vagaReservada = vagaReservada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
