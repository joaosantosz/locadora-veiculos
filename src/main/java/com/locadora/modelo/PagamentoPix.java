package com.locadora.modelo;

/**
 * Implementação de pagamento via Pix.
 */
public class PagamentoPix implements Pagamento {

    private final String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public String processar(double valor) {
        return "Pagamento de R$ " + String.format("%.2f", valor) + " confirmado via Pix (chave: " + chavePix + ")";
    }

    @Override
    public String getDescricao() {
        return "Pix";
    }
}
