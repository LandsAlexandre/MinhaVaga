/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import minhavagaweb.model.cgt.AplCliente;
import minhavagaweb.model.cdp.*;
import minhavagaweb.model.cgd.PessoaDAOImpl;
import static org.junit.Assert.assertEquals;

import minhavagaweb.model.cgd.GenericDAO;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author matheus
 */
public class EfetuarLogin {

    PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();


    @Given("^usuário cadastrado$")
    public void usuário_cadastrado() throws Throwable {
        Pessoa pessoa = (Pessoa) pessoaDAO.getById(22);
        assertNotNull(pessoa);
    }

    @When("^eu tentar logar com dados válidos$")
    public void eu_tentar_logar_com_dados_válidos() throws Throwable {
        boolean loginValido = pessoaDAO.selectLogin("helenfranca93@gmail.com", "lela123");
        assertEquals(true, loginValido);
    }

    @Then("^devo ver a mensagem de saudação personalizada$")
    public void devo_ver_a_mensagem_de_saudação_personalizada() throws Throwable {
        System.out.println("Olá 'Fulano'!");
    }

    
    @When("^eu logar com dados inválidos$")
    public void eu_logar_com_dados_inválidos() throws Throwable {
        boolean loginValido = pessoaDAO.selectLogin("helenfranca93@gmail.com", "senhaerrada");
        assertEquals(false, loginValido);
    }

    @Then("^devo ver o texto \"([^\"]*)\"$")
    public void devo_ver_o_texto(String arg1) throws Throwable {
        assertEquals("Login ou senha incorretos", arg1);
    }
    
    
    @Given("^usuário não cadastrado$")
    public void usuário_não_cadastrado() throws Throwable {
        Pessoa pessoa = (Pessoa) pessoaDAO.getById(100);
        assertEquals(null, pessoa);
    }
    
    @When("^eu tentar logar dados que não existem no sistema$")
    public void eu_tentar_logar_dados_que_não_existem_no_sistema() throws Throwable {
        boolean loginValido = pessoaDAO.selectLogin("helenfranca93@hotmail.com", "llela123");
        assertEquals(false, loginValido);
    }

    @Then("^eu devo ver o texto \"([^\"]*)\"$")
    public void eu_devo_ver_o_texto(String arg1) throws Throwable {
        assertEquals("Usuário não cadastrado", arg1);
    }
    
    @Then("^eu devo ser redirecionado para a tela de cadastro$")
    public void eu_devo_ser_redirecionado_para_a_tela_de_cadastro() throws Throwable {
        System.out.println("Tela de Cadastro");
    }

    
    
}
