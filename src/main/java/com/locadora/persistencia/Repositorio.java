package com.locadora.persistencia;

import java.util.List;
import java.util.Optional;

/**
 * USO DE INTERFACE + GENERICS: contrato genérico de persistência,
 * implementado por cada repositório específico (simulação de CRUD em memória).
 *
 * @param <T> tipo da entidade armazenada
 */
public interface Repositorio<T> {

    T salvar(T entidade);

    Optional<T> buscarPorId(String id);

    List<T> listarTodos();

    void remover(String id);
}
