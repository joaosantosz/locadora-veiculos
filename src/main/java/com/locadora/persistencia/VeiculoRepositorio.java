package com.locadora.persistencia;

import com.locadora.modelo.Veiculo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Persistência simulada de Veículos em memória.
 * Armazena qualquer subtipo de Veiculo (Carro, Moto, Van) - POLIMORFISMO.
 */
public class VeiculoRepositorio implements Repositorio<Veiculo> {

    private final Map<String, Veiculo> dados = new LinkedHashMap<>();

    @Override
    public Veiculo salvar(Veiculo entidade) {
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<Veiculo> buscarPorId(String id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public void remover(String id) {
        dados.remove(id);
    }

    public List<Veiculo> listarDisponiveis() {
        return dados.values().stream()
                .filter(Veiculo::isDisponivel)
                .toList();
    }
}
