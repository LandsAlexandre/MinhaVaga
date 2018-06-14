/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

import java.util.Date;

/**
 *
 * @author lantrous
 */
public class Pessoa {

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
     * @param cpf the cpf to set
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

    public static boolean isValido(String cpfSemFormatacao) {

        if (cpfSemFormatacao.length() != 11 || cpfSemFormatacao.equals("00000000000")
                || cpfSemFormatacao.equals("99999999999")) {
            return false;
        }

        String digitos = cpfSemFormatacao.substring(0, 9);
        String dvs = cpfSemFormatacao.substring(9, 11);

        String dv1 = gerarDV(digitos);
        String dv2 = gerarDV(digitos + dv1);

        return dvs.equals(dv1 + dv2);
    }

    private static String gerarDV(String digitos) {
        int peso = digitos.length() + 1;
        int dv = 0;
        for (int i = 0; i < digitos.length(); i++) {
            dv += Integer.parseInt(digitos.substring(i, i + 1)) * peso;
            peso--;
        }

        dv = 11 - (dv % 11);

        if (dv > 9) {
            return "0";
        }

        return String.valueOf(dv);
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "CPF: " + this.getCpf();
    }
}
