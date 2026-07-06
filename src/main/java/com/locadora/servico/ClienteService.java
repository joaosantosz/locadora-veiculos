package com.locadora.servico;

import com.locadora.modelo.Cliente;
import com.locadora.persistencia.ClienteRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Regras de negócio relacionadas ao cadastro e consulta de Clientes.
 */
public class ClienteService {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteService(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public Cliente cadastrar(String nome, String email, String telefone, String cnh) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        if (cnh == null || cnh.isBlank()) {
            throw new IllegalArgumentException("CNH do cliente é obrigatória.");
        }
        if (clienteRepositorio.buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este email.");
        }
        if (clienteRepositorio.buscarPorCnh(cnh).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esta CNH.");
        }
        String id = UUID.randomUUID().toString().substring(0, 8);
        Cliente cliente = new Cliente(id, nome, email, telefone, cnh);
        return clienteRepositorio.salvar(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepositorio.listarTodos();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepositorio.buscarPorId(id);
    }
}
