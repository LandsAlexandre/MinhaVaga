/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import minhavagaweb.model.cgd.VagaDAOImpl;

import minhavagaweb.model.cgd.EstacionamentoDAOImpl;
import minhavagaweb.model.cgd.VagaDAOImpl;

/**
 *
 * @author landerson
 */
public class Estacionamento implements Serializable{
    private int id;
    private String nome;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private float valorPorHora;
    private int capacidade;
    
    private transient List<Vaga> vagas = new ArrayList<>();
    private transient Localizacao local = new Localizacao();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public float getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(float valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }
    
    public void adicionarVaga(Vaga vaga) {
        this.vagas.add(vaga);
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public Vaga getVagaDisponivel(int idTipo) {

    	 if (vagas.isEmpty()) {
    		 VagaDAOImpl<Vaga> vagaDAO = new VagaDAOImpl<>();
    		 try {
				vagas = vagaDAO.getAll(this.id);
			} catch (ClassNotFoundException | SQLException e) {
				return null;
			}
		 }

    	 for (Vaga vaga : vagas) {
            if (vaga.getTipo().getValue() == idTipo && vaga.isStatus()) {
                return vaga;
            }
        }
        return null;
    }
    
}
