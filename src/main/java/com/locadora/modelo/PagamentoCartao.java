package com.locadora.modelo;

/**
 * Implementação de pagamento via cartão de crédito/débito.
 */
public class PagamentoCartao implements Pagamento {

    private final String numeroCartaoMascarado;

    public PagamentoCartao(String numeroCartao) {
        int tamanho = numeroCartao.length();
        this.numeroCartaoMascarado = "**** **** **** " + numeroCartao.substring(Math.max(0, tamanho - 4));
    }

    @Override
    public String processar(double valor) {
        return "Pagamento de R$ " + String.format("%.2f", valor) + " aprovado no cartão " + numeroCartaoMascarado;
    }

    @Override
    public String getDescricao() {
        return "Cartão de Crédito/Débito";
    }
}
