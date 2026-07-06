package com.locadora.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente que aluga veículos no sistema.
 * HERANÇA: estende Usuario.
 */
public class Cliente extends Usuario {

    private String cnh;
    private final List<Locacao> historicoLocacoes;

    public Cliente(String id, String nome, String email, String telefone, String cnh) {
        super(id, nome, email, telefone);
        this.cnh = cnh;
        this.historicoLocacoes = new ArrayList<>();
    }

    public void adicionarLocacaoAoHistorico(Locacao locacao) {
        historicoLocacoes.add(locacao);
    }

    public List<Locacao> getHistoricoLocacoes() {
        return historicoLocacoes;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Override
    public String exibirPerfil() {
        return "Cliente: " + getNome() + " | CNH: " + cnh + " | Email: " + getEmail()
                + " | Locações realizadas: " + historicoLocacoes.size();
    }
}
