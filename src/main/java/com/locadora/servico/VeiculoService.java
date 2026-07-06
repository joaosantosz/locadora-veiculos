package com.locadora.servico;

import com.locadora.modelo.Carro;
import com.locadora.modelo.Moto;
import com.locadora.modelo.Van;
import com.locadora.modelo.Veiculo;
import com.locadora.persistencia.VeiculoRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Regras de negócio relacionadas ao cadastro e consulta da frota de veículos.
 * Cada método de cadastro cria o subtipo correto (Carro, Moto ou Van),
 * demonstrando o uso de POLIMORFISMO já na criação dos objetos.
 */
public class VeiculoService {

    private final VeiculoRepositorio veiculoRepositorio;

    public VeiculoService(VeiculoRepositorio veiculoRepositorio) {
        this.veiculoRepositorio = veiculoRepositorio;
    }

    public Carro cadastrarCarro(String placa, String modelo, String marca, int ano,
                                 double precoDiariaBase, int numeroPortas, boolean arCondicionado) {
        validarDadosBasicos(placa, modelo, marca, precoDiariaBase);
        String id = UUID.randomUUID().toString().substring(0, 8);
        Carro carro = new Carro(id, placa, modelo, marca, ano, precoDiariaBase, numeroPortas, arCondicionado);
        veiculoRepositorio.salvar(carro);
        return carro;
    }

    public Moto cadastrarMoto(String placa, String modelo, String marca, int ano,
                               double precoDiariaBase, int cilindrada) {
        validarDadosBasicos(placa, modelo, marca, precoDiariaBase);
        String id = UUID.randomUUID().toString().substring(0, 8);
        Moto moto = new Moto(id, placa, modelo, marca, ano, precoDiariaBase, cilindrada);
        veiculoRepositorio.salvar(moto);
        return moto;
    }

    public Van cadastrarVan(String placa, String modelo, String marca, int ano,
                             double precoDiariaBase, int capacidadePassageiros) {
        validarDadosBasicos(placa, modelo, marca, precoDiariaBase);
        String id = UUID.randomUUID().toString().substring(0, 8);
        Van van = new Van(id, placa, modelo, marca, ano, precoDiariaBase, capacidadePassageiros);
        veiculoRepositorio.salvar(van);
        return van;
    }

    private void validarDadosBasicos(String placa, String modelo, String marca, double precoDiariaBase) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa do veículo não pode ser vazia.");
        }
        if (modelo == null || modelo.isBlank() || marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Modelo e marca do veículo são obrigatórios.");
        }
        if (precoDiariaBase <= 0) {
            throw new IllegalArgumentException("Preço da diária base deve ser maior que zero.");
        }
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepositorio.listarTodos();
    }

    public List<Veiculo> listarDisponiveis() {
        return veiculoRepositorio.listarDisponiveis();
    }

    public Optional<Veiculo> buscarPorId(String id) {
        return veiculoRepositorio.buscarPorId(id);
    }
}
