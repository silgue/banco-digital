package me.dio.banco.dominio;

import me.dio.banco.util.ClienteException;

public class ContaCorrente extends Conta{
	
	private CartaoCredito cartao;
	
	public ContaCorrente(Banco banco, Cliente cliente) {
		this.cartao = new CartaoCredito(200);
		super.cadastrarConta(cliente);
	}
	
	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

}
