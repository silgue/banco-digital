package me.dio.banco.dominio;

public class Conta {

	private static int SEQUENCIAL = 1;

	private static final int agencia = 1;

	private int numero;
	private double saldo;
	private Cliente cliente;
	private CartaoCredito cartao;

	public void cadastrarConta(Cliente cliente) {
		this.numero = SEQUENCIAL++;
		this.saldo = 0;
		this.cliente = cliente;
		this.cartao = new CartaoCredito(200);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
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

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

}
