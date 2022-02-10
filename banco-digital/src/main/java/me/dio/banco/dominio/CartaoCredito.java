package me.dio.banco.dominio;

public class CartaoCredito {
	private double limite;

	public CartaoCredito(int limite) {
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

}
