package com.locadora.persistencia;

import com.locadora.modelo.Locacao;
import com.locadora.modelo.StatusLocacao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Persistência simulada de Locações em memória.
 */
public class LocacaoRepositorio implements Repositorio<Locacao> {

    private final Map<String, Locacao> dados = new LinkedHashMap<>();

    @Override
    public Locacao salvar(Locacao entidade) {
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<Locacao> buscarPorId(String id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Locacao> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public void remover(String id) {
        dados.remove(id);
    }

    public List<Locacao> listarPorStatus(StatusLocacao status) {
        return dados.values().stream()
                .filter(l -> l.getStatus() == status)
                .toList();
    }
}
