/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cdp;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author landerson
 */
public class Reserva {
    private LocalTime horaChegada;
    private LocalTime horaSaida;
    private Date dataChegada;
    private Date dataSaida;
    
    private StatusReserva status;
    private Vaga vagaReservada;
    
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

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Vaga getVagaReservada() {
        return vagaReservada;
    }

    public void setVagaReservada(Vaga vagaReservada) {
        this.vagaReservada = vagaReservada;
    }
    
}
