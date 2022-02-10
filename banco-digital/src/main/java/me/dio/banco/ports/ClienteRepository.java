package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.util.ClienteException;

public interface ClienteRepository {

	Cliente cadastrarCliente(Banco banco, String cpf) throws ClienteException;

	Cliente buscaClientePeloCPF(Banco banco, String cpf) throws ClienteException;

}
