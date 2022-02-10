package me.dio.banco.dominio;

import me.dio.banco.util.ClienteException;

public class Conta {

	private static int SEQUENCIAL = 1;

	private static final int agencia = 1;

	private int numero;
	private double saldo;
	private Cliente cliente;
	

	public void cadastrarConta(Cliente cliente) {
		this.numero = SEQUENCIAL++;
		this.saldo = 0;
		this.cliente = cliente;
	}
	
	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	private void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getAgencia() {
		return agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void sacar(Conta conta, Double valor) throws ClienteException {
		if(isTemSaldo(conta, valor)) {
			double saldo = conta.getSaldo();
			conta.setSaldo(saldo - valor);
		}else throw new ClienteException("A conta não possui saldo suficiente");
	}
	
	public void depositar(Conta conta, Double valor) throws ClienteException {
		double saldoAtual = conta.getSaldo();
		conta.setSaldo(saldoAtual + valor);
	}
	
	private boolean isTemSaldo(Conta conta, Double valor) {
		if(conta.getSaldo() >= valor) return true;
		else return false;
	}

}
