package com.locadora.modelo;

/**
 * Implementação de pagamento em dinheiro, com cálculo de troco.
 */
public class PagamentoDinheiro implements Pagamento {

    private final double valorEntregue;

    public PagamentoDinheiro(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    @Override
    public String processar(double valor) {
        double troco = valorEntregue - valor;
        if (troco < 0) {
            return "Valor entregue insuficiente para pagar R$ " + String.format("%.2f", valor);
        }
        return "Pagamento de R$ " + String.format("%.2f", valor) + " em dinheiro. Troco: R$ " + String.format("%.2f", troco);
    }

    @Override
    public String getDescricao() {
        return "Dinheiro";
    }
}
