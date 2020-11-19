package br.com.psmcompany.rest.v1;

import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import br.com.psmcompany.rest.exception.MyRestException;
import br.com.psmcompany.services.IDepartamentoService;
import br.com.psmcompany.services.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departamento")
public class DepartamentoRest {

    private final IDepartamentoService departamentoService;

    public DepartamentoRest(@Autowired IDepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Departamento> getDepartamentos() {
        return this.departamentoService.findAll();
    }

    @GetMapping(path = "/{id_departamento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Departamento getDepartamentos(@PathVariable("id_departamento") Integer idDepartamento) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new MyRestException(String.format("%d é inválido.", idDepartamento));
        }
        return departamento.get();
    }

    @GetMapping(path = "/{id_departamento}/funcionario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Funcionario>> getPessoasDepartamento(@PathVariable("id_departamento") Integer idDepartamento) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new MyRestException(String.format("%d é inválido.", idDepartamento));
        }
        return ResponseEntity.ok(departamento.get().getFuncionarios());
    }

    @GetMapping(path = "/{id_departamento}/funcionario/{id_funcionario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario getPessoasDepartamento(@PathVariable("id_departamento") Integer idDepartamento, @PathVariable("id_funcionario") Integer idFuncionario) {
        Optional<Departamento> departamento = this.departamentoService.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new MyRestException(String.format("%d é inválido.", idDepartamento));
        }
        return this.departamentoService.findByDepartamentoAndFuncionario(idDepartamento, idFuncionario);
    }

}
