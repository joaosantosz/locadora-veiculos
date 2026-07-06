package com.locadora.modelo;

/**
 * USO DE INTERFACE: define o contrato que toda forma de pagamento deve seguir.
 * As implementações (Cartao, Pix, Dinheiro) demonstram POLIMORFISMO:
 * cada uma processa o pagamento de um jeito diferente.
 */
public interface Pagamento {

    /**
     * Processa o pagamento de um determinado valor.
     * @param valor valor total a ser pago
     * @return mensagem de confirmação do processamento
     */
    String processar(double valor);

    /**
     * @return descrição curta da forma de pagamento (ex: "Cartão de Crédito")
     */
    String getDescricao();
}
