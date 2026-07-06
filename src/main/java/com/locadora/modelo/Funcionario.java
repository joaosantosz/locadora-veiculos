package com.locadora.modelo;

/**
 * Representa um funcionário da locadora, responsável por atender
 * clientes e formalizar locações.
 * HERANÇA: estende Usuario.
 */
public class Funcionario extends Usuario {

    private String cargo;
    private String matricula;

    public Funcionario(String id, String nome, String email, String telefone, String cargo, String matricula) {
        super(id, nome, email, telefone);
        this.cargo = cargo;
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String exibirPerfil() {
        return "Funcionário: " + getNome() + " | Cargo: " + cargo + " | Matrícula: " + matricula;
    }
}
