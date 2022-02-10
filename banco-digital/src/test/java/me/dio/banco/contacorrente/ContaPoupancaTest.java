package me.dio.banco.contacorrente;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.repository.ContaPoupancaRepositoryImpl;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.OperacoesClientes;

public class ContaPoupancaTest {

	@Test
	public void testCriacaoContaPoupanca() throws ClienteException {

		Banco banco = new Banco();

		OperacoesClientes operacaoCliente = new OperacoesClientes();

		operacaoCliente.cadastrarCliente(banco, "Alysson Rodrigues", "01014368405", 40, 11000.00);

		String cpf = "01014368405";

		ContaPoupanca conta = cadastrarContaPoupanca(banco, cpf);

		int agencia = 1;
		int numero = 1;
		String nome = "Alysson Rodrigues";
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
		if (conta.getSaldo() == saldo)
			teste = true;
		else
			teste = false;

		assertTrue(teste);
	}

	@Test(expected = ClienteException.class)
	public void testCriacaoContaPoupancaJaExistente() throws ClienteException {

		Banco banco = new Banco();

		OperacoesClientes operacaoCliente = new OperacoesClientes();

		operacaoCliente.cadastrarCliente(banco, "Alysson Rodrigues", "01014368405", 40, 11000.00);

		cadastrarContaPoupanca(banco, "01014368405");

		cadastrarContaPoupanca(banco, "01014368405");

		assertThrows("Cliente já possui conta poupança.", null, null);

	}

	private ContaPoupanca cadastrarContaPoupanca(Banco banco, String cpf) throws ClienteException {

		ContaPoupancaRepositoryImpl conta = new ContaPoupancaRepositoryImpl();
		return conta.cadastrarConta(banco, cpf);
	}

}
