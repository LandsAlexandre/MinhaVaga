/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.controller.GenController;
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cgd.CartaoDAOImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

/**
 *
 * @author ISM
 */
public class CadastrarCartao {
    
    CartaoDAOImpl<Cartao> cartaoDAO = new CartaoDAOImpl<>();
    GenController controller = new GenController();
    Cartao card = new Cartao();
    Cliente c;
    String pagina;
    @Given("^cartão não cadastrado$")
    public void cartão_não_cadastrado() throws Throwable {
        Cartao cartao = (Cartao) cartaoDAO.getById(cartaoDAO.getNextId("select * from cartao order by id_cartao", "id_cartao"));
        assertEquals(null, cartao);
    }

    @When("^eu cadastrar dados de cartão válidos$")
    public void eu_cadastrar_dados_de_cartão_válidos() throws Throwable {
        
        String email = "JuliaRodrigues6@hotmail.com";
        c = new Cliente();
        c.setCpf("11307925014");
        c.setEmail(email);
        c.setNome("Jubileu");
        c.setSenha("0000");
        
        card.setNumeroCartao("4024007122177636");
        card.setNomeTitular(c.getNome());
        
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 05, 28);
        
        card.setDataValidade(cal);
        card.setCvv("326");
        
        pagina = controller.cadastrarCartao(card);
        assertEquals(GenController.TELASOLICITACAO, pagina);
    }

    @Then("^devo ver a mensagem \"([^\"]*)\"$")
    public void devo_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inserido com sucesso!", arg1);
    }

    @Then("^serei redirecionado para a tela de reservas$")
    public void serei_redirecionado_para_a_tela_de_reservas() throws Throwable {
        assertEquals(GenController.TELASOLICITACAO, pagina);
    }

    
    
    @When("^eu cadastrar dados de cartão inválidos$")
    public void eu_cadastrar_dados_de_cartão_inválidos() throws Throwable {
        card.setNumeroCartao("123456789456");
        pagina = controller.cadastrarCartao(card);
        assertEquals(GenController.CARTAO_INVALIDO, pagina);
    }

    @Then("^deve me aparecer a mensagem \"([^\"]*)\"$")
    public void deve_me_aparecer_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inválido!", arg1);
    }

    @Then("^serei redirecionado para a tela de inserção de dados$")
    public void serei_redirecionado_para_a_tela_de_inserção_de_dados() throws Throwable {
        assertEquals(GenController.CARTAO_INVALIDO, pagina);
    }
    
    @Given("^eu tenha um cartao cadastrado$")
    public void eu_tenha_um_cartao_cadastrado() throws Throwable {
    	int id = cartaoDAO.getNextId("select * from cartao where nometitular='Jubileu' order by id_cartao asc", "id_cartao")-1;
    	card = cartaoDAO.getById(id);
    	assertEquals("Jubileu",card.getNomeTitular());
    }

    @When("^eu atualizar seus dados$")
    public void eu_atualizar_seus_dados() throws Throwable {
    	card.setNomeTitular("Jubileu de Ouro");
    	cartaoDAO.update(card);
    	card = cartaoDAO.getById(card.getId());
    	assertEquals("Jubileu de Ouro",card.getNomeTitular());
    }

    @Then("^eu deverei ver a mensagem \"([^\"]*)\"$")
    public void eu_deverei_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Atualização completa!",arg1);
    }

    @Given("^eu ja tenha um cartao cadastrado$")
    public void eu_ja_tenha_um_cartao_cadastrado() throws Throwable {
    	int id = cartaoDAO.getNextId("select * from cartao where nometitular='Jubileu de Ouro' order by id_cartao asc", "id_cartao")-1;
    	card = cartaoDAO.getById(id);
    	assertEquals("Jubileu de Ouro",card.getNomeTitular());
    }

    @When("^eu deletar este cartao$")
    public void eu_deletar_este_cartao() throws Throwable {
        cartaoDAO.delete(card);
    }

    @Then("^ele nao estara mais guardado$")
    public void ele_nao_estara_mais_guardado() throws Throwable {
        int id = cartaoDAO.getNextId("select * from cartao where nometitular='Jubileu de Ouro'", "id_cartao")-1;
        assertNull(cartaoDAO.getById(id));
    }

    
}
