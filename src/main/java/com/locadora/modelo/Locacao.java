package com.locadora.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe central do sistema: representa a locação de um Veiculo por um
 * Cliente, formalizada por um Funcionario, com uma lista de Adicionais
 * (COMPOSIÇÃO) e uma forma de Pagamento (polimorfismo via interface).
 */
public class Locacao {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final double MULTA_ATRASO_PERCENTUAL = 0.5; // 50% da diária por dia de atraso

    private String id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionarioResponsavel;
    private final List<Adicional> adicionais;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
    private LocalDate dataFimReal;
    private StatusLocacao status;
    private Pagamento formaPagamento;

    public Locacao(String id, Cliente cliente, Veiculo veiculo, Funcionario funcionarioResponsavel,
                   LocalDate dataInicio, LocalDate dataFimPrevista, Pagamento formaPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionarioResponsavel = funcionarioResponsavel;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.formaPagamento = formaPagamento;
        this.adicionais = new ArrayList<>();
        this.status = StatusLocacao.RESERVADA;
    }

    public void adicionarAdicional(Adicional adicional) {
        adicionais.add(adicional);
    }

    private long calcularDiasPrevistos() {
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFimPrevista);
        return Math.max(dias, 1);
    }

    private double calcularValorDiariaTotal() {
        double valorAdicionais = adicionais.stream().mapToDouble(Adicional::getValorDiario).sum();
        return veiculo.calcularValorDiaria() + valorAdicionais;
    }

    public double calcularValorTotal() {
        return calcularDiasPrevistos() * calcularValorDiariaTotal();
    }

    public void iniciar() {
        this.status = StatusLocacao.EM_ANDAMENTO;
    }

    /**
     * Finaliza a locação na data informada, calculando multa por atraso
     * caso a devolução ocorra após a data prevista.
     * @param dataFimReal data em que o veículo foi efetivamente devolvido
     * @return valor total a pagar, já incluindo eventual multa por atraso
     */
    public double finalizar(LocalDate dataFimReal) {
        this.dataFimReal = dataFimReal;
        this.status = StatusLocacao.FINALIZADA;
        this.veiculo.setDisponivel(true);

        double total = calcularValorTotal();
        if (dataFimReal.isAfter(dataFimPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataFimPrevista, dataFimReal);
            total += diasAtraso * veiculo.calcularValorDiaria() * MULTA_ATRASO_PERCENTUAL;
        }
        return total;
    }

    public void cancelar() {
        this.status = StatusLocacao.CANCELADA;
        this.veiculo.setDisponivel(true);
    }

    public String confirmarPagamento(double valor) {
        return formaPagamento.processar(valor);
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public List<Adicional> getAdicionais() {
        return adicionais;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public LocalDate getDataFimReal() {
        return dataFimReal;
    }

    public StatusLocacao getStatus() {
        return status;
    }

    public void setStatus(StatusLocacao status) {
        this.status = status;
    }

    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "Locação #" + id
                + " | Cliente: " + cliente.getNome()
                + " | Veículo: " + veiculo.getCategoria() + " " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")"
                + " | Período: " + dataInicio.format(FORMATO_DATA) + " a " + dataFimPrevista.format(FORMATO_DATA)
                + " | Status: " + status.getDescricao()
                + " | Pagamento: " + formaPagamento.getDescricao()
                + " | Valor previsto: R$ " + String.format("%.2f", calcularValorTotal());
    }
}
