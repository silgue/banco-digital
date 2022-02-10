package me.dio.banco.repository;

import me.dio.banco.dominio.Conta;
import me.dio.banco.ports.ContaRepository;

public class ContaRepositoryImpl implements ContaRepository{

	public void extrato(Conta conta) {
		
		System.out.println("Nome: " + conta.getCliente().getNome());
		System.out.println("CPF: " + conta.getCliente().getCpf() + "\n");
		System.out.println("Agência: " + conta.getAgencia());
		System.out.println("Número da conta: " + conta.getNumero());
		System.out.println("Saldo da Conta: " + conta.getSaldo());
		
	}

}
