package br.com.psmcompany.services;

import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;

import java.util.Optional;

public interface IDepartamentoService {
    Departamento save(Departamento departamento);

    Iterable<Departamento> findAll();

    Optional<Departamento> findById(Integer idDepartamento);

    Funcionario findByDepartamentoAndFuncionario(Integer idDepartamento, Integer idFuncionario);
}
