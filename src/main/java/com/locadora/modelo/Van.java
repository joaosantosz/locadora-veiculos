package com.locadora.modelo;

/**
 * Representa uma van/veículo de grande porte da frota. HERANÇA: estende Veiculo.
 */
public class Van extends Veiculo {

    private int capacidadePassageiros;

    public Van(String id, String placa, String modelo, String marca, int ano,
               double precoDiariaBase, int capacidadePassageiros) {
        super(id, placa, modelo, marca, ano, precoDiariaBase);
        this.capacidadePassageiros = capacidadePassageiros;
    }

    @Override
    public double calcularValorDiaria() {
        // Vans têm acréscimo de 50% em relação ao preço base, por serem de maior porte.
        return getPrecoDiariaBase() * 1.50;
    }

    @Override
    public String getCategoria() {
        return "Van";
    }

    @Override
    public String exibirFicha() {
        return toString() + " | Capacidade: " + capacidadePassageiros + " passageiros";
    }

    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }
}
