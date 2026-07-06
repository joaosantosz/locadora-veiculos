package com.locadora.servico;

import com.locadora.modelo.*;
import com.locadora.persistencia.LocacaoRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Regras de negócio relacionadas à criação e ao ciclo de vida das Locações.
 * É aqui que ocorre a orquestração entre Cliente, Veiculo, Funcionario e Pagamento.
 */
public class LocacaoService {

    private final LocacaoRepositorio locacaoRepositorio;

    public LocacaoService(LocacaoRepositorio locacaoRepositorio) {
        this.locacaoRepositorio = locacaoRepositorio;
    }

    public Locacao criarLocacao(Cliente cliente, Veiculo veiculo, Funcionario funcionario,
                                 LocalDate dataInicio, LocalDate dataFimPrevista, Pagamento formaPagamento) {
        if (!veiculo.isDisponivel()) {
            throw new IllegalStateException("Veículo não está disponível para locação.");
        }
        if (!dataFimPrevista.isAfter(dataInicio)) {
            throw new IllegalArgumentException("Data de devolução prevista deve ser após a data de início.");
        }
        String id = UUID.randomUUID().toString().substring(0, 8);
        Locacao locacao = new Locacao(id, cliente, veiculo, funcionario, dataInicio, dataFimPrevista, formaPagamento);
        veiculo.setDisponivel(false);
        locacaoRepositorio.salvar(locacao);
        cliente.adicionarLocacaoAoHistorico(locacao);
        return locacao;
    }

    public void adicionarAdicional(Locacao locacao, Adicional adicional) {
        locacao.adicionarAdicional(adicional);
    }

    public void iniciarLocacao(Locacao locacao) {
        if (locacao.getStatus() != StatusLocacao.RESERVADA) {
            throw new IllegalStateException("Somente locações reservadas podem ser iniciadas.");
        }
        locacao.iniciar();
    }

    public double finalizarLocacao(Locacao locacao, LocalDate dataFimReal) {
        if (locacao.getStatus() == StatusLocacao.FINALIZADA || locacao.getStatus() == StatusLocacao.CANCELADA) {
            throw new IllegalStateException("Esta locação já foi encerrada.");
        }
        return locacao.finalizar(dataFimReal);
    }

    public void cancelarLocacao(Locacao locacao) {
        if (locacao.getStatus() == StatusLocacao.FINALIZADA) {
            throw new IllegalStateException("Não é possível cancelar uma locação já finalizada.");
        }
        locacao.cancelar();
    }

    public List<Locacao> listarTodos() {
        return locacaoRepositorio.listarTodos();
    }

    public List<Locacao> listarPorStatus(StatusLocacao status) {
        return locacaoRepositorio.listarPorStatus(status);
    }

    public Optional<Locacao> buscarPorId(String id) {
        return locacaoRepositorio.buscarPorId(id);
    }
}
