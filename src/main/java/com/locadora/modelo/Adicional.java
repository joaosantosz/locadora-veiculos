package com.locadora.modelo;

/**
 * Representa um item adicional/opcional de uma locação
 * (ex: seguro completo, GPS, cadeira infantil), cobrado por diária.
 */
public class Adicional {

    private String id;
    private String nome;
    private double valorDiario;

    public Adicional(String id, String nome, double valorDiario) {
        this.id = id;
        this.nome = nome;
        this.valorDiario = valorDiario;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", valorDiario) + "/dia";
    }
}
