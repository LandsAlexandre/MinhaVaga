/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgt;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Calendar;

import minhavagaweb.controller.ControllerSolicita;
import minhavagaweb.controller.GenController;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.*;

/**
 *
 * @author ISM
 */
public class AplSolicitacao {
    private static EstacionamentoDAOImpl<Estacionamento> estacionaDAO = new EstacionamentoDAOImpl<>();
    private static Vaga vagaSolicitada = new Vaga();
    
    public boolean verificaPendencia(Cliente cliente) {
        return cliente.estahPendente();
    }
    
    public static String encontrarVaga(int idEstacionamento,int idTipo) throws ClassNotFoundException {
    	Estacionamento park = new Estacionamento();
    	try {
			park = estacionaDAO.getById(idEstacionamento);
			vagaSolicitada = park.getVagaDisponivel(idTipo);
			if (vagaSolicitada.getId() != 0 || park.getId() != 0) {
				throw new NullPointerException();
			}
		}
		catch(NullPointerException | SQLException e) {
			return GenController.TELAVAGANENCONTRADA;
		}
		return ControllerSolicita.TELACONFIRMARSOLICITACAO;
	}

    public static void confirmarSolicitacao() {
    	SolicitacaoReserva solicitacao = new SolicitacaoReserva();
        Reserva reserva = new Reserva();
    	solicitacao.setDataSolicitacao(Calendar.getInstance());
    	solicitacao.setHoraSolicitacao(LocalTime.now());
    	
    	reserva.setDataChegada(solicitacao.getDataSolicitacao());
        reserva.setVagaReservada(vagaSolicitada);
        
        solicitacao.setReserva(reserva);
    }
}
