/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

/**
 *
 * @author ISM
 */
public class CPF {
    
<<<<<<< HEAD:src/main/java/minhavagaweb/model/cdp/CPF.java
    private String numCpf;

    public CPF(String cpf) {
        this.numCpf = cpf;
    }

    public String getCpf() {
        return numCpf;
    }

    public void setCpf(String cpf) {
        this.numCpf = cpf;
=======
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8:src/main/java/minhavagaweb/model/cdp/CPF.java
    }

    public static boolean isCPFValido(String cpfSemFormatacao) {

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
}
