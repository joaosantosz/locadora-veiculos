package com.locadora.aplicacao;

import com.locadora.modelo.Carro;
import com.locadora.modelo.Cliente;
import com.locadora.modelo.Funcionario;
import com.locadora.modelo.Moto;
import com.locadora.persistencia.ClienteRepositorio;
import com.locadora.persistencia.FuncionarioRepositorio;
import com.locadora.persistencia.LocacaoRepositorio;
import com.locadora.persistencia.VeiculoRepositorio;
import com.locadora.servico.ClienteService;
import com.locadora.servico.FuncionarioService;
import com.locadora.servico.LocacaoService;
import com.locadora.servico.VeiculoService;
import com.locadora.ui.MenuPrincipal;

/**
 * Classe principal do sistema de Locação de Veículos.
 * Monta as dependências (repositórios -> serviços -> UI), popula dados de
 * exemplo e inicia o menu interativo.
 */
public class Main {

    public static void main(String[] args) {
        // Repositórios (persistência simulada em memória)
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
        VeiculoRepositorio veiculoRepositorio = new VeiculoRepositorio();
        LocacaoRepositorio locacaoRepositorio = new LocacaoRepositorio();

        // Serviços (regras de negócio)
        ClienteService clienteService = new ClienteService(clienteRepositorio);
        FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepositorio);
        VeiculoService veiculoService = new VeiculoService(veiculoRepositorio);
        LocacaoService locacaoService = new LocacaoService(locacaoRepositorio);

        popularDadosDeExemplo(clienteService, funcionarioService, veiculoService);

        // Interface textual
        MenuPrincipal menu = new MenuPrincipal(clienteService, funcionarioService, veiculoService, locacaoService);
        menu.iniciar();
    }

    /**
     * Cadastra alguns dados iniciais para facilitar testes e a demonstração
     * na apresentação, sem precisar digitar tudo manualmente.
     */
    private static void popularDadosDeExemplo(ClienteService clienteService,
                                               FuncionarioService funcionarioService,
                                               VeiculoService veiculoService) {
        Cliente cliente = clienteService.cadastrar(
                "Maria Silva", "maria@email.com", "(68) 99999-0001", "12345678900");

        Funcionario funcionario = funcionarioService.cadastrar(
                "Carlos Souza", "carlos@locadora.com", "(68) 99999-0002", "Atendente", "F001");

        Carro carro = veiculoService.cadastrarCarro(
                "ABC-1234", "Onix", "Chevrolet", 2023, 150.00, 4, true);

        Moto moto = veiculoService.cadastrarMoto(
                "XYZ-5678", "CG 160", "Honda", 2022, 80.00, 160);

        System.out.println("Dados de exemplo carregados:");
        System.out.println("- Cliente ID: " + cliente.getId() + " (" + cliente.getNome() + ")");
        System.out.println("- Funcionário ID: " + funcionario.getId() + " (" + funcionario.getNome() + ")");
        System.out.println("- Carro ID: " + carro.getId() + " (" + carro.getModelo() + ")");
        System.out.println("- Moto ID: " + moto.getId() + " (" + moto.getModelo() + ")");
        System.out.println();
    }
}
