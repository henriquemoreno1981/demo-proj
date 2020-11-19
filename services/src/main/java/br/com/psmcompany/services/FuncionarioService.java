package br.com.psmcompany.services;

import br.com.psmcompany.data.InterfaceFuncionario;
import br.com.psmcompany.models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class FuncionarioService implements IFuncionarioService {

    private final InterfaceFuncionario interfaceFuncionario;

    public FuncionarioService(@Autowired InterfaceFuncionario interfaceFuncionario) {
        this.interfaceFuncionario = interfaceFuncionario;
    }

    @Override
    public Funcionario save(@NotNull Funcionario funcionario) {
        return interfaceFuncionario.save(funcionario);
    }

    @Override
    public Iterable<Funcionario> saveAll(@NotNull Iterable<Funcionario> funcionarios) {
        return interfaceFuncionario.saveAll(funcionarios);
    }
}
