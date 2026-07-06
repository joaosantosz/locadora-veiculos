package com.locadora.modelo;

/**
 * Representa os possíveis estados de uma locação ao longo do seu ciclo de vida.
 */
public enum StatusLocacao {
    RESERVADA("Reservada"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusLocacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
