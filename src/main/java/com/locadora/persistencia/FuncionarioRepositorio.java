package com.locadora.persistencia;

import com.locadora.modelo.Funcionario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Persistência simulada de Funcionários em memória.
 */
public class FuncionarioRepositorio implements Repositorio<Funcionario> {

    private final Map<String, Funcionario> dados = new LinkedHashMap<>();

    @Override
    public Funcionario salvar(Funcionario entidade) {
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<Funcionario> buscarPorId(String id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Funcionario> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public void remover(String id) {
        dados.remove(id);
    }
}
