package me.dio.banco.contacorrente;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.repository.ContaCorrenteRepositoryImpl;
import me.dio.banco.util.ClienteException;

public class ContaCorrenteTest {

	@Test
	public void testCriacaoContaCorrente() throws ClienteException {

		Banco banco = new Banco();
		
		cadastrarCliente(banco, "Alysson Rodrigues", "01014368405", 40, 11000.00);

		String cpf = "01014368405";

		ContaCorrente conta = cadastrarContaCorrente(banco, cpf);

		int agencia = 1;
		int numero = 1;
		String nome = "Alysson Rodrigues";
		double limiteCartaoCredito = 200;
		double saldo = 0;
		boolean teste = false;

		if (conta.getAgencia() == agencia)
			teste = true;
		else
			teste = false;
		if (conta.getNumero() == numero)
			teste = true;
		else
			teste = false;
		if (conta.getCliente().getNome() == nome)
			teste = true;
		else
			teste = false;
		if (conta.getCartao().getLimite() == limiteCartaoCredito)
			teste = true;
		else
			teste = false;
		if (conta.getSaldo() == saldo)
			teste = true;
		else
			teste = false;

		assertTrue(teste);
	}

	@Test(expected = ClienteException.class)
	public void testCriacaoContaCorrenteJaExistente() throws ClienteException {

		Banco banco = new Banco();

		cadastrarCliente(banco, "Alysson Rodrigues", "01014368405", 40, 11000.00);

		cadastrarContaCorrente(banco, "01014368405");

		cadastrarContaCorrente(banco, "01014368405");

		assertThrows("Cliente já possui conta corrente", null, null);

	}

	private ContaCorrente cadastrarContaCorrente(Banco banco, String cpf) throws ClienteException {
		
		ContaCorrenteRepositoryImpl conta = new ContaCorrenteRepositoryImpl();
		return conta.cadastrarConta(banco, cpf);
	}

	private void cadastrarCliente(Banco banco, String nome, String cpf, Integer idade, Double remuneracaoMensal)
			throws ClienteException {
		
		Cliente cliente = new Cliente(nome, cpf, idade, remuneracaoMensal);
		banco.getClientes().add(cliente);
	}

}
