package br.com.psmcompany.services;

import br.com.psmcompany.data.InterfaceDepartamento;
import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {
    private final InterfaceDepartamento interfaceDepartamento;

    public DepartamentoService(@Autowired InterfaceDepartamento interfaceDepartamento) {
        this.interfaceDepartamento = interfaceDepartamento;
    }

    @Override
    public Departamento save(Departamento departamento) {
        return this.interfaceDepartamento.save(departamento);
    }

    @Override
    public Iterable<Departamento> findAll() {
        return this.interfaceDepartamento.findAll();
    }

    @Override
    public Optional<Departamento> findById(Integer idDepartamento) {
        return this.interfaceDepartamento.findById(idDepartamento);
    }

    @Override
    public Funcionario findByDepartamentoAndFuncionario(Integer idDepartamento, Integer idFuncionario) {
        return this.interfaceDepartamento.findByIdAndFuncionario(idDepartamento, idFuncionario);
    }
}
