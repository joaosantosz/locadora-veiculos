package com.locadora.persistencia;

import com.locadora.modelo.Cliente;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Persistência simulada de Clientes em memória, usando Map (chave = id).
 */
public class ClienteRepositorio implements Repositorio<Cliente> {

    private final Map<String, Cliente> dados = new LinkedHashMap<>();

    @Override
    public Cliente salvar(Cliente entidade) {
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public void remover(String id) {
        dados.remove(id);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return dados.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Cliente> buscarPorCnh(String cnh) {
        return dados.values().stream()
                .filter(c -> c.getCnh().equalsIgnoreCase(cnh))
                .findFirst();
    }
}
