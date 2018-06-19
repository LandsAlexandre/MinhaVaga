/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lantrous
 */
public class Pessoa implements Serializable{

    private int id;
    private String nome;
    private Email email = new Email();
    private String senha;
    private CPF cpf = new CPF();
    private Date datanascimento;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email.getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return this.cpf.getCpf();
    }

    /**
     * @param cpf1
     */
    public void setCpf(String cpf1) {
        this.cpf.setCpf(cpf1);
    }

    public Date getNascimento() {
        return datanascimento;
    }

    public void setNascimento(Date nascimento) {
        this.datanascimento = nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "CPF: " + this.getCpf();
    }
}
