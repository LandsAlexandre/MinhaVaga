/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model;

<<<<<<< HEAD:src/main/java/minhavagaweb/model/cdp/Cartao.java

=======
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8:src/main/java/minhavagaweb/model/Cartao.java
import java.util.Calendar;

/**
 *
 * @author landerson
 */
public class Cartao {

    private int id;
    private String nomeTitular;
    private String numeroCartao;
    private Calendar dataValidade;
    private String cvv;


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

    public Calendar getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Calendar dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
