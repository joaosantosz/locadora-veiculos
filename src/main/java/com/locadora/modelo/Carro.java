package com.locadora.modelo;

/**
 * Representa um carro da frota. HERANÇA: estende Veiculo.
 */
public class Carro extends Veiculo {

    private int numeroPortas;
    private boolean arCondicionado;

    public Carro(String id, String placa, String modelo, String marca, int ano,
                 double precoDiariaBase, int numeroPortas, boolean arCondicionado) {
        super(id, placa, modelo, marca, ano, precoDiariaBase);
        this.numeroPortas = numeroPortas;
        this.arCondicionado = arCondicionado;
    }

    @Override
    public double calcularValorDiaria() {
        // Carros com ar-condicionado têm um acréscimo de 10% na diária.
        double valor = getPrecoDiariaBase();
        if (arCondicionado) {
            valor *= 1.10;
        }
        return valor;
    }

    @Override
    public String getCategoria() {
        return "Carro";
    }

    @Override
    public String exibirFicha() {
        return toString() + " | Portas: " + numeroPortas + " | Ar-condicionado: " + (arCondicionado ? "Sim" : "Não");
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }
}
