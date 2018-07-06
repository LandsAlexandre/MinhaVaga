/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgt;

import java.sql.SQLException;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.*;

/**
 *
 * @author ISM
 */
public class AplSolicitacao {
    Estacionamento park = new Estacionamento();
    EstacionamentoDAOImpl<Estacionamento> estacionaDAO = new EstacionamentoDAOImpl<>();
    VagaDAOImpl<Vaga> vagaDAO = new VagaDAOImpl<>();
  
    public boolean verificaPendencia(Cliente cliente) {
        return cliente.estahPendente();
    }

    public Vaga solicita(int idEstacionamento, int idTipo) throws SQLException, ClassNotFoundException {
        park = estacionaDAO.getById(idEstacionamento);
        return park.getVagaDisponivel(idTipo);
    }
    
}
