package br.com.psmcompany.services;

import br.com.psmcompany.models.Funcionario;

public interface IFuncionarioService {
    Funcionario save(Funcionario funcionario);

    Iterable<Funcionario> saveAll(Iterable<Funcionario> funcionarios);
}
