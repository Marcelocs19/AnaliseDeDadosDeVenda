package br.com.desafio.agibank.validacao;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.modelo.Cliente;
import br.com.desafio.agibank.modelo.Item;
import br.com.desafio.agibank.modelo.Venda;
import br.com.desafio.agibank.modelo.Vendedor;

public class ValidacaoTeste {
		
	private Vendedor vendedor1;
	private Vendedor vendedor2;
	private Vendedor vendedor3;
	private Vendedor vendedor4;
	
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Cliente cliente4;
	
	private Venda venda1;
	private Venda venda2;
	private Venda venda3;
	
	private Item item1;
	private Item item2;
	private Item item3;
	private Item item4;
	
	@Before
	public void setup() {
		vendedor1 = new Vendedor();
		vendedor1.setCpf("15919950005");
		vendedor1.setNome("Pedro");
		vendedor1.setSalario(400.00);
		
		vendedor2 = new Vendedor();
		vendedor2.setCpf("11111111111");
		vendedor2.setNome("João");
		vendedor2.setSalario(200.00);
		
		vendedor3 = new Vendedor();
		vendedor3.setCpf("15919950005");
		vendedor3.setNome("");
		vendedor3.setSalario(100.00);
		
		vendedor4 = new Vendedor();
		vendedor4.setCpf("15919950005");
		vendedor4.setNome("Maria");
		vendedor4.setSalario(null);
		
		cliente1 = new Cliente();
		cliente1.setBusinessArea("Rural");
		cliente1.setNome("Marcelo");
		cliente1.setCnpj("71946154000119");
		
		cliente2 = new Cliente();
		cliente2.setBusinessArea("");
		cliente2.setNome("Paulo");
		cliente2.setCnpj("96191380000168");
		
		cliente3 = new Cliente();
		cliente3.setBusinessArea("Rural");
		cliente3.setNome("");
		cliente3.setCnpj("39019388000108");
		
		cliente4 = new Cliente();
		cliente4.setBusinessArea("Rural");
		cliente4.setNome("Paula");
		cliente4.setCnpj("");
		
		venda1 = new Venda();
		venda1.setId(1);
		venda1.setNome("Julio");
		
		venda2 = new Venda();
		venda2.setId(null);
		venda2.setNome("Juliano");
		
		venda3 = new Venda();
		venda3.setId(2);
		venda3.setNome("");
		
		item1 = new Item();
		item1.setId(1);
		item1.setPreco(200.00);
		item1.setQuantidade(10);
		
		item2 = new Item();
		item2.setId(null);
		item2.setPreco(200.00);
		item2.setQuantidade(10);
		
		item3 = new Item();
		item3.setId(3);
		item3.setPreco(null);
		item3.setQuantidade(10);
		
		item4 = new Item();
		item4.setId(4);
		item4.setPreco(1200.00);
		item4.setQuantidade(null);

	}

	@Test
	public void validaCamposVendedor() {
		assertEquals(true, Validacao.validaCamposVendedor(vendedor1));
	}
	
	@Test
	public void erroValidaCPFVendedor() {
		String msg = "";
		try {
			Validacao.validaCamposVendedor(vendedor2);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_CPF.getDescricao());
	}
	
	@Test
	public void erroValidaNomeVendedor() {
		String msg = "";
		try {
			Validacao.validaCamposVendedor(vendedor3);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_NOME.getDescricao());
	}
	
	@Test
	public void erroValidaSalarioVendedor() {
		String msg = "";
		try {
			Validacao.validaCamposVendedor(vendedor4);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_SALARIO.getDescricao());
	}
	
	@Test
	public void validaCamposCliente() {
		assertEquals(true, Validacao.validaCamposCliente(cliente1));
	}
	
	@Test
	public void erroValidaBusinessCliente() {
		String msg = "";
		try {
			Validacao.validaCamposCliente(cliente2);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_BUSINESS_AREA.getDescricao());
	}
	
	@Test
	public void erroValidaNomeCliente() {
		String msg = "";
		try {
			Validacao.validaCamposCliente(cliente3);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_NOME.getDescricao());
	}
	
	@Test
	public void erroValidaCNPJCliente() {
		String msg = "";
		try {
			Validacao.validaCamposCliente(cliente4);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_CNPJ.getDescricao());
	}
	
	@Test
	public void validaCamposVenda() {
		assertEquals(true, Validacao.validaCamposVenda(venda1));
	}

	@Test
	public void erroValidaIDVenda() {
		String msg = "";
		try {
			Validacao.validaCamposVenda(venda2);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_ID_VENDA.getDescricao());
	}
	
	@Test
	public void erroValidaNomeVenda() {
		String msg = "";
		try {
			Validacao.validaCamposVenda(venda3);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_NOME.getDescricao());
	}
	
	@Test
	public void validaCamposItem() {
		assertEquals(true, Validacao.validaCamposItem(item1));
	}

	@Test
	public void erroValidaIDItem() {
		String msg = "";
		try {
			Validacao.validaCamposItem(item2);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_ID_ITEM.getDescricao());
	}	

	@Test
	public void erroValidaPrecoItem() {
		String msg = "";
		try {
			Validacao.validaCamposItem(item3);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_PRECO.getDescricao());
	}

	@Test
	public void erroValidaQuantidadeItem() {
		String msg = "";
		try {
			Validacao.validaCamposItem(item4);			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals(msg, Erros.MSG_ERRO_QUANTIDADE.getDescricao());
	}
	
}
