/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cdp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author landerson
 */
public class Cartao {
    private String nomeTitular;
    private String numeroCartao;
    private Date dataValidade;
    private String cvv;
    
    private Cliente cliente;
    private List<Pagamento> pagamentos = new ArrayList<>();

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    
    public void addPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }
    
}
