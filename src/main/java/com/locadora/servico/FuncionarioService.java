package com.locadora.servico;

import com.locadora.modelo.Funcionario;
import com.locadora.persistencia.FuncionarioRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Regras de negócio relacionadas ao cadastro de Funcionários.
 */
public class FuncionarioService {

    private final FuncionarioRepositorio funcionarioRepositorio;

    public FuncionarioService(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
    }

    public Funcionario cadastrar(String nome, String email, String telefone, String cargo, String matricula) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do funcionário não pode ser vazio.");
        }
        String id = UUID.randomUUID().toString().substring(0, 8);
        Funcionario funcionario = new Funcionario(id, nome, email, telefone, cargo, matricula);
        return funcionarioRepositorio.salvar(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepositorio.listarTodos();
    }

    public Optional<Funcionario> buscarPorId(String id) {
        return funcionarioRepositorio.buscarPorId(id);
    }
}
