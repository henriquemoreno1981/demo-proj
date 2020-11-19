package br.com.psmcompany.services;

import br.com.psmcompany.models.Cargo;
import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class IDepartamentoServiceTest {
    @Autowired
    IDepartamentoService departamentoService;

    @Autowired
    IFuncionarioService funcionarioService;

    @Autowired
    ICargoService cargoService;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void save() {
        Departamento departamento = departamentoService.save(
                Departamento.
                        DepartamentoBuilder
                        .aDepartamento()
                        .withName("Catalogo")
                        .withChefe(funcionarioService.save(Funcionario
                                .FuncionarioBuilder
                                .aFuncionario()
                                .withName("John Barbacue")
                                .build()))
                        .build()
        );
        assertThat(departamento.getId(), is(notNullValue()));
        assertThat(departamento.getName(), is(equalToIgnoringCase("Catalogo")));
        assertThat(departamento.getChefe().getName(), is(equalToIgnoringCase("john barbacue")));
    }

    @Test
    public void listaFuncionarios() throws ParseException {
        Departamento departamento = departamentoService.save(
                Departamento.
                        DepartamentoBuilder
                        .aDepartamento()
                        .withName("Marvel")
                        .withChefe(funcionarioService.save(Funcionario
                                .FuncionarioBuilder
                                .aFuncionario()
                                .withName("Nick Fury")
                                .withDocument("XXXXXXXXX")
                                .withBirthday(sdf.parse("01/04/1960"))
                                .build()))
                        .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario
                                        .FuncionarioBuilder
                                        .aFuncionario()
                                        .withName("Thor")
                                        .withBirthday(sdf.parse("01/01/0001"))
                                        .withCargo(
                                                cargoService.save(Cargo
                                                        .CargoBuilder
                                                        .aCargo()
                                                        .withName("Deus")
                                                        .build())
                                        )
                                        .build(),
                                Funcionario
                                        .FuncionarioBuilder
                                        .aFuncionario()
                                        .withName("Hulk")
                                        .withCargo(
                                                cargoService.save(Cargo
                                                        .CargoBuilder
                                                        .aCargo()
                                                        .withName("Super-Herói")
                                                        .build())
                                        )
                                        .build())))
                        .build()
        );
        assertThat(departamento.getId(), is(notNullValue()));
        assertThat(departamento.getName(), is(equalToIgnoringCase("Marvel")));
        assertThat(departamento.getChefe().getName(), is(equalToIgnoringCase("Nick Fury")));
        assertThat(departamento.getFuncionarios().size(), is(equalTo(2)));

    }

    @Test
    public void findById() {
        Departamento departamentoSalvo = departamentoService.save(Departamento
                .DepartamentoBuilder
                .aDepartamento()
                .withName("Jornalismo")
                .build());
        Optional<Departamento> departamento = departamentoService.findById(departamentoSalvo.getId());
        assertThat(departamento.get().getId(), is(equalTo(departamentoSalvo.getId())));
    }

    @Test
    public void findByDepartamentoAndFuncionario() {

        List<Funcionario> funcionarios = Arrays.asList(new Funcionario[]{
                Funcionario.FuncionarioBuilder.aFuncionario()
                        .withName("João de Barro")
                        .withCargo(cargoService.save(
                                Cargo.CargoBuilder.aCargo().withName("Editor").build()
                        )).build(),
                Funcionario.FuncionarioBuilder.aFuncionario()
                        .withName("Bemtevi")
                        .withCargo(cargoService.save(
                                Cargo.CargoBuilder.aCargo().withName("Jornalista").build()
                        )).build()
        });
        Departamento departamentoSalvo = departamentoService.save(Departamento
                .DepartamentoBuilder
                .aDepartamento()
                .withName("Jornalismo")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(funcionarios))
                .build());

        Funcionario funcionario = departamentoService.findByDepartamentoAndFuncionario(departamentoSalvo.getId(), funcionarios.get(0).getId());
        assertThat(funcionario.getId(), is(equalTo(funcionarios.get(0).getId())));
        assertThat(funcionario.getName(), is(equalToIgnoringCase(funcionarios.get(0).getName())));
    }
}