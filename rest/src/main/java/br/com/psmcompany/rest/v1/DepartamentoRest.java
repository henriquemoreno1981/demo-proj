package br.com.psmcompany.rest.v1;

import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import br.com.psmcompany.services.IDepartamentoService;
import br.com.psmcompany.services.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartamentoRest {

    private final IDepartamentoService departamentoService;
    private final IFuncionarioService funcionarioService;

    public DepartamentoRest(@Autowired IDepartamentoService departamentoService, IFuncionarioService funcionarioService) {
        this.departamentoService = departamentoService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping(path = "/v1/departamento/")
    public Iterable<Departamento> getDepartamentos() {
        return this.departamentoService.findAll();
    }

    @GetMapping(path = "/v1/departamento/{id_departamento}")
    public Departamento getDepartamentos(@PathVariable("id_departamento") Integer idDepartamento) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new RuntimeException(String.format("%d é inválido.", idDepartamento));
        }
        return departamento.get();
    }

    @GetMapping(path = "/v1/departamento/{id_departamento}/funcionario")
    public List<Funcionario> getPessoasDepartamento(@PathVariable("id_departamento") Integer idDepartamento) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new RuntimeException(String.format("%d é inválido.", idDepartamento));
        }
        return departamento.get().getFuncionarios();
    }

    @GetMapping(path = "/v1/departamento/{id_departamento}/funcionario/{id_funcionario}")
    public Funcionario getPessoasDepartamento(@PathVariable("id_departamento") Integer idDepartamento, @PathVariable("id_funcionario") Integer idFuncionario) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new RuntimeException(String.format("%d é inválido.", idDepartamento));
        }
        return this.departamentoService.findByDepartamentoAndFuncionario(idDepartamento, idFuncionario);
    }

}
