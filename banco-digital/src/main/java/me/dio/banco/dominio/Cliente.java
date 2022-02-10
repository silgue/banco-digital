package me.dio.banco.dominio;

public class Cliente {

	private String nome;
	private String cpf;
	private int idade;
	private double remuneracaoMensal;

	public Cliente(String nome, String cpf, int idade, double remuneracaoMensal) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.remuneracaoMensal = remuneracaoMensal;

	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public int getIdade() {
		return idade;
	}

	public double getRemuneracaoMensal() {
		return remuneracaoMensal;
	}

	public void setRemuneracaoMensal(double remuneracaoMensal) {
		this.remuneracaoMensal = remuneracaoMensal;
	}

}
