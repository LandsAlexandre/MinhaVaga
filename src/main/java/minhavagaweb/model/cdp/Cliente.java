/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

import minhavagaweb.model.Cartao;
import minhavagaweb.model.SolicitacaoReserva;
import minhavagaweb.model.Pagamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author landerson
 */
public class Cliente extends Pessoa {

    //private int id;
    private List<Cartao> cartoes = new ArrayList<>();
    private Pagamento pagamento;
    private SolicitacaoReserva solicitacao;

    public List<Cartao> getCartoes() {
        return this.cartoes;
    }

    public void addCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public SolicitacaoReserva getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoReserva solicitacao) {
        this.solicitacao = solicitacao;
    }
}
