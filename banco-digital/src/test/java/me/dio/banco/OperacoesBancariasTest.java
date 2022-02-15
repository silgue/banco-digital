package me.dio.banco;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.repository.ContaCorrenteRepositoryImpl;
import me.dio.banco.repository.ContaPoupancaRepositoryImpl;
import me.dio.banco.repository.OperacoesBancariasRepositoryImpl;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.OperacoesClientes;
import me.dio.banco.util.OperacoesContaUtil;
import me.dio.banco.util.TipoOperacaoConta;

public class OperacoesBancariasTest {

	OperacoesBancariasRepositoryImpl operacoes = new OperacoesBancariasRepositoryImpl();

	OperacoesContaUtil contaUtil = new OperacoesContaUtil();

	OperacoesClientes clienteUtil = new OperacoesClientes();

	Banco banco = new Banco();

	ContaCorrenteRepositoryImpl contaCorrenteRepository = new ContaCorrenteRepositoryImpl();

	ContaPoupancaRepositoryImpl contaPoupancaRepository = new ContaPoupancaRepositoryImpl();

	boolean teste = true;

	int numeroInicioConta = 0;

	@Test
	public void depositarTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		ContaCorrente contaCorrente1 = contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

		if (contaCorrente1.getSaldo() != 400.00)
			teste = false;

		ContaPoupanca contaPoupanca2 = contaPoupancaRepository.buscarConta(banco, ++numeroInicioConta);

		if (contaPoupanca2.getSaldo() != 400.00)
			teste = false;

		ContaCorrente contaCorrente3 = contaCorrenteRepository.buscarConta(banco, ++numeroInicioConta);

		if (contaCorrente3.getSaldo() != 400.00)
			teste = false;

		ContaPoupanca contaPoupanca4 = contaPoupancaRepository.buscarConta(banco, ++numeroInicioConta);

		if (contaPoupanca4.getSaldo() != 400.00)
			teste = false;

		assertTrue(teste);
	}
	
	@Test(expected = ClienteException.class)
	public void depositarContaInexistenteTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		operacoes.depositar(TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE, banco, numeroInicioConta-1, 500.00);

		contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

	}

	@Test
	public void sacarTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		operacoes.sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroInicioConta, 150.00);

		ContaCorrente contaCorrente = contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

		if (contaCorrente.getSaldo() != 250.00)
			teste = false;

		assertTrue(teste);

	}

	@Test(expected = ClienteException.class)
	public void sacarSemSaldoSuficienteTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		operacoes.sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroInicioConta, 500.00);

		contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

	}
	
	@Test(expected = ClienteException.class)
	public void sacarContaInexistenteTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		operacoes.sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroInicioConta-1, 500.00);

		contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

	}

	@Test
	public void TransferenciaEntreContasTest() throws ClienteException {

		gerarMassaDeposito();

		pegarUltimoNumeroConta(banco);

		operacoes.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_POUPANCA, banco, numeroInicioConta,
				++numeroInicioConta, 100.00);

		ContaPoupanca contaPoupancaDestino1 = contaPoupancaRepository.buscarConta(banco, numeroInicioConta);
		ContaCorrente contaCorrenteOrigem1 = contaCorrenteRepository.buscarConta(banco, --numeroInicioConta);

		if (contaPoupancaDestino1.getSaldo() != 500.00 || contaCorrenteOrigem1.getSaldo() != 300.00)
			teste = false;

		operacoes.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_CORRENTE, banco, numeroInicioConta,
				numeroInicioConta+2, 100.00);

		ContaCorrente contaCorrenteDestino2 = contaCorrenteRepository.buscarConta(banco, numeroInicioConta+2);
		ContaCorrente contaCorrenteOrigem2 = contaCorrenteRepository.buscarConta(banco, numeroInicioConta);

		if (contaCorrenteDestino2.getSaldo() != 500.00 || contaCorrenteOrigem2.getSaldo() != 200.00)
			teste = false;

		assertTrue(teste);
	}

	private void gerarMassaDeposito() throws ClienteException {

		contaUtil.gerarMassaDadosConta(banco);
		for (ContaCorrente conta : banco.getContasCorrente()) {
			if (conta.getSaldo() == 0) {
				operacoes.depositar(TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE, banco, conta.getNumero(), 400.00);
			}
		}
		for (ContaPoupanca conta : banco.getContasPoupanca()) {
			if (conta.getSaldo() == 0) {
				operacoes.depositar(TipoOperacaoConta.DEPOSITAR_CONTA_POUPANCA, banco, conta.getNumero(), 400.00);
			}
		}
	}

	private void pegarUltimoNumeroConta(Banco banco) {
		for (ContaPoupanca conta : banco.getContasPoupanca()) {
			numeroInicioConta = conta.getNumero();
		}
		numeroInicioConta = numeroInicioConta - 3;
	}
}
