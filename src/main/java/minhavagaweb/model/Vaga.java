/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model;

/**
 *
 * @author landerson
 */
public class Vaga {
    private int id;
    private boolean status;
    private boolean cobertura;
    
    private TipoVaga tipo;
    private Localizacao local;
    private Estacionamento estacionamento;
    
    public int getId() {
        return id;
    }

    public void setId(int idVaga) {
        this.id = idVaga;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isCobertura() {
        return cobertura;
    }

    public void setCobertura(boolean cobertura) {
        this.cobertura = cobertura;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public TipoVaga getTipo() {
        return tipo;
    }

    public void setTipo(TipoVaga tipo) {
        this.tipo = tipo;
    }
}
