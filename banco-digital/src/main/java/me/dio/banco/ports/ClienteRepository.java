package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;

public interface ClienteRepository {

	Cliente cadastrarCliente(Banco banco, String cpf);

	Cliente buscaClientePeloCPF(Banco banco, String cpf);

}
