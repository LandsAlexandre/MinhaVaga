/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author landerson
 */
public class Cliente extends Pessoa {

    private final List<Cartao> cartoes = new ArrayList<>();
    private final List<Pagamento> pagamentos = new ArrayList<>();
    private SolicitacaoReserva solicitacao;

    public List<Cartao> getCartoes() {
        return this.cartoes;
    }

    public void addCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }

    public SolicitacaoReserva getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoReserva solicitacao) {
        this.solicitacao = solicitacao;
    }

    public boolean estahPendente() {
        List<Pagamento> listaPagamentos;
        listaPagamentos = this.getPagamentos();

        for (Pagamento pagamento : listaPagamentos) {
            if (!pagamento.isPago()) {
                return true;
            }
        }
        return false;
    }
}
