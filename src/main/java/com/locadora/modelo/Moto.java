package com.locadora.modelo;

/**
 * Representa uma moto da frota. HERANÇA: estende Veiculo.
 */
public class Moto extends Veiculo {

    private int cilindrada;

    public Moto(String id, String placa, String modelo, String marca, int ano,
                double precoDiariaBase, int cilindrada) {
        super(id, placa, modelo, marca, ano, precoDiariaBase);
        this.cilindrada = cilindrada;
    }

    @Override
    public double calcularValorDiaria() {
        // Motos têm um desconto de 30% em relação ao preço base (categoria mais econômica).
        return getPrecoDiariaBase() * 0.70;
    }

    @Override
    public String getCategoria() {
        return "Moto";
    }

    @Override
    public String exibirFicha() {
        return toString() + " | Cilindrada: " + cilindrada + "cc";
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
