package com.locadora.ui;

import com.locadora.modelo.*;
import com.locadora.servico.ClienteService;
import com.locadora.servico.FuncionarioService;
import com.locadora.servico.LocacaoService;
import com.locadora.servico.VeiculoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Interface textual (menu no terminal) do sistema de Locação de Veículos.
 * Responsável apenas por interação com o usuário; toda a regra de
 * negócio fica delegada às classes de serviço.
 */
public class MenuPrincipal {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Scanner scanner = new Scanner(System.in);
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final VeiculoService veiculoService;
    private final LocacaoService locacaoService;

    public MenuPrincipal(ClienteService clienteService, FuncionarioService funcionarioService,
                          VeiculoService veiculoService, LocacaoService locacaoService) {
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.veiculoService = veiculoService;
        this.locacaoService = locacaoService;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuVeiculos();
                case 3 -> menuFuncionarios();
                case 4 -> menuLocacoes();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.\n");
            }
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("=========================================");
        System.out.println("        LOCADORA DE VEÍCULOS");
        System.out.println("=========================================");
        System.out.println("1 - Clientes");
        System.out.println("2 - Veículos");
        System.out.println("3 - Funcionários");
        System.out.println("4 - Locações");
        System.out.println("0 - Sair");
    }

    // ---------------------- CLIENTES ----------------------

    private void menuClientes() {
        int opcao;
        do {
            System.out.println("\n--- Clientes ---");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> listarClientes();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String telefone = lerTexto("Telefone: ");
        String cnh = lerTexto("CNH: ");
        Cliente cliente = clienteService.cadastrar(nome, email, telefone, cnh);
        System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("[" + c.getId() + "] " + c.exibirPerfil());
        }
    }

    // ---------------------- VEÍCULOS ----------------------

    private void menuVeiculos() {
        int opcao;
        do {
            System.out.println("\n--- Veículos ---");
            System.out.println("1 - Cadastrar carro");
            System.out.println("2 - Cadastrar moto");
            System.out.println("3 - Cadastrar van");
            System.out.println("4 - Listar todos os veículos");
            System.out.println("5 - Listar veículos disponíveis");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> cadastrarCarro();
                    case 2 -> cadastrarMoto();
                    case 3 -> cadastrarVan();
                    case 4 -> listarVeiculos(veiculoService.listarTodos());
                    case 5 -> listarVeiculos(veiculoService.listarDisponiveis());
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarCarro() {
        String placa = lerTexto("Placa: ");
        String modelo = lerTexto("Modelo: ");
        String marca = lerTexto("Marca: ");
        int ano = lerInteiro("Ano: ");
        double preco = lerDouble("Preço da diária base: ");
        int portas = lerInteiro("Número de portas: ");
        boolean arCondicionado = lerTexto("Possui ar-condicionado? (s/n): ").equalsIgnoreCase("s");
        Carro carro = veiculoService.cadastrarCarro(placa, modelo, marca, ano, preco, portas, arCondicionado);
        System.out.println("Carro cadastrado com sucesso! ID: " + carro.getId());
    }

    private void cadastrarMoto() {
        String placa = lerTexto("Placa: ");
        String modelo = lerTexto("Modelo: ");
        String marca = lerTexto("Marca: ");
        int ano = lerInteiro("Ano: ");
        double preco = lerDouble("Preço da diária base: ");
        int cilindrada = lerInteiro("Cilindrada (cc): ");
        Moto moto = veiculoService.cadastrarMoto(placa, modelo, marca, ano, preco, cilindrada);
        System.out.println("Moto cadastrada com sucesso! ID: " + moto.getId());
    }

    private void cadastrarVan() {
        String placa = lerTexto("Placa: ");
        String modelo = lerTexto("Modelo: ");
        String marca = lerTexto("Marca: ");
        int ano = lerInteiro("Ano: ");
        double preco = lerDouble("Preço da diária base: ");
        int capacidade = lerInteiro("Capacidade de passageiros: ");
        Van van = veiculoService.cadastrarVan(placa, modelo, marca, ano, preco, capacidade);
        System.out.println("Van cadastrada com sucesso! ID: " + van.getId());
    }

    private void listarVeiculos(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
            return;
        }
        for (Veiculo v : veiculos) {
            System.out.println("[" + v.getId() + "] " + v.exibirFicha());
        }
    }

    // ---------------------- FUNCIONÁRIOS ----------------------

    private void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n--- Funcionários ---");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> cadastrarFuncionario();
                    case 2 -> listarFuncionarios();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarFuncionario() {
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String telefone = lerTexto("Telefone: ");
        String cargo = lerTexto("Cargo: ");
        String matricula = lerTexto("Matrícula: ");
        Funcionario funcionario = funcionarioService.cadastrar(nome, email, telefone, cargo, matricula);
        System.out.println("Funcionário cadastrado com sucesso! ID: " + funcionario.getId());
    }

    private void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println("[" + f.getId() + "] " + f.exibirPerfil());
        }
    }

    // ---------------------- LOCAÇÕES ----------------------

    private void menuLocacoes() {
        int opcao;
        do {
            System.out.println("\n--- Locações ---");
            System.out.println("1 - Criar nova locação");
            System.out.println("2 - Listar todas as locações");
            System.out.println("3 - Iniciar locação (retirada do veículo)");
            System.out.println("4 - Finalizar locação (devolução do veículo)");
            System.out.println("5 - Cancelar locação");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> criarLocacao();
                    case 2 -> listarLocacoes();
                    case 3 -> iniciarLocacao();
                    case 4 -> finalizarLocacao();
                    case 5 -> cancelarLocacao();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void criarLocacao() {
        String clienteId = lerTexto("ID do cliente: ");
        Cliente cliente = clienteService.buscarPorId(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        String veiculoId = lerTexto("ID do veículo: ");
        Veiculo veiculo = veiculoService.buscarPorId(veiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));

        String funcionarioId = lerTexto("ID do funcionário responsável: ");
        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        LocalDate dataInicio = lerData("Data de início (dd/MM/yyyy): ");
        LocalDate dataFimPrevista = lerData("Data prevista de devolução (dd/MM/yyyy): ");

        Pagamento pagamento = escolherFormaPagamento();

        Locacao locacao = locacaoService.criarLocacao(cliente, veiculo, funcionario, dataInicio, dataFimPrevista, pagamento);
        System.out.println("Locação criada! ID: " + locacao.getId());

        String continuar = lerTexto("Deseja adicionar algum item extra (seguro, GPS, etc.)? (s/n): ");
        while (continuar.equalsIgnoreCase("s")) {
            String nomeAdicional = lerTexto("Nome do adicional: ");
            double valorDiario = lerDouble("Valor diário do adicional: ");
            String adicionalId = java.util.UUID.randomUUID().toString().substring(0, 8);
            locacaoService.adicionarAdicional(locacao, new Adicional(adicionalId, nomeAdicional, valorDiario));
            continuar = lerTexto("Adicionar outro item? (s/n): ");
        }

        System.out.println("\nResumo da locação:");
        System.out.println(locacao);
    }

    private Pagamento escolherFormaPagamento() {
        System.out.println("Forma de pagamento:");
        System.out.println("1 - Cartão");
        System.out.println("2 - Pix");
        System.out.println("3 - Dinheiro");
        int opcao = lerInteiro("Escolha: ");
        return switch (opcao) {
            case 1 -> new PagamentoCartao(lerTexto("Número do cartão: "));
            case 2 -> new PagamentoPix(lerTexto("Chave Pix: "));
            case 3 -> new PagamentoDinheiro(lerDouble("Valor a ser entregue em dinheiro: "));
            default -> throw new IllegalArgumentException("Forma de pagamento inválida.");
        };
    }

    private void listarLocacoes() {
        List<Locacao> locacoes = locacaoService.listarTodos();
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação registrada.");
            return;
        }
        for (Locacao l : locacoes) {
            System.out.println(l);
        }
    }

    private void iniciarLocacao() {
        String locacaoId = lerTexto("ID da locação: ");
        Locacao locacao = buscarLocacaoOuFalhar(locacaoId);
        locacaoService.iniciarLocacao(locacao);
        System.out.println("Locação iniciada. Status: " + locacao.getStatus().getDescricao());
    }

    private void finalizarLocacao() {
        String locacaoId = lerTexto("ID da locação: ");
        Locacao locacao = buscarLocacaoOuFalhar(locacaoId);
        LocalDate dataFimReal = lerData("Data real de devolução (dd/MM/yyyy): ");
        double valorTotal = locacaoService.finalizarLocacao(locacao, dataFimReal);
        System.out.println("Locação finalizada. Valor total a pagar (incluindo possível multa por atraso): R$ "
                + String.format("%.2f", valorTotal));
        System.out.println(locacao.confirmarPagamento(valorTotal));
    }

    private void cancelarLocacao() {
        String locacaoId = lerTexto("ID da locação: ");
        Locacao locacao = buscarLocacaoOuFalhar(locacaoId);
        locacaoService.cancelarLocacao(locacao);
        System.out.println("Locação cancelada.");
    }

    private Locacao buscarLocacaoOuFalhar(String id) {
        Optional<Locacao> locacao = locacaoService.buscarPorId(id);
        return locacao.orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));
    }

    // ---------------------- UTILITÁRIOS DE LEITURA ----------------------

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }

    private LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return LocalDate.parse(entrada, FORMATO_DATA);
            } catch (Exception e) {
                System.out.println("Digite uma data válida no formato dd/MM/yyyy.");
            }
        }
    }
}
