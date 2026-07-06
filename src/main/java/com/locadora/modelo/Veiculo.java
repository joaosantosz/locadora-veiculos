package com.locadora.modelo;

/**
 * Classe abstrata que representa um veículo genérico da frota.
 * Serve de base para Carro, Moto e Van (HERANÇA).
 * Cada subtipo calcula sua diária de forma diferente (POLIMORFISMO).
 */
public abstract class Veiculo {

    private String id;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double precoDiariaBase;
    private boolean disponivel;

    public Veiculo(String id, String placa, String modelo, String marca, int ano, double precoDiariaBase) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.precoDiariaBase = precoDiariaBase;
        this.disponivel = true;
    }

    /**
     * Calcula o valor da diária aplicando a regra específica de cada
     * categoria de veículo (ex: moto tem desconto, van tem acréscimo).
     */
    public abstract double calcularValorDiaria();

    /**
     * Retorna a categoria do veículo (ex: "Carro", "Moto", "Van").
     */
    public abstract String getCategoria();

    /**
     * Cada subtipo exibe uma ficha com seus atributos específicos (POLIMORFISMO).
     */
    public abstract String exibirFicha();

    public String getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPrecoDiariaBase() {
        return precoDiariaBase;
    }

    public void setPrecoDiariaBase(double precoDiariaBase) {
        this.precoDiariaBase = precoDiariaBase;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Locado";
        return getCategoria() + " " + marca + " " + modelo + " (" + ano + ") - Placa: " + placa
                + " - Diária: R$ " + String.format("%.2f", calcularValorDiaria()) + " - " + status;
    }
}
