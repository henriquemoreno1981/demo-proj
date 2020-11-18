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
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class IFuncionarioServiceTest {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    IFuncionarioService funcionarioService;

    @Autowired
    ICargoService cargoService;

    @Autowired
    IDepartamentoService departamentoService;

    @Test
    void save() throws ParseException {
        Cargo cargo = cargoService.save(Cargo
                .CargoBuilder
                .aCargo()
                .withName("Super-herói")
                .build());
        Departamento departamento = departamentoService.save(Departamento
                .DepartamentoBuilder
                .aDepartamento()
                .withName("Marvel")
                .build());
        Funcionario funcionario = Funcionario.FuncionarioBuilder.aFuncionario()
                .withName("Hulk")
                .withBirthday(sdf.parse("05/05/1990"))
                .withCargo(cargo)
                .withDepartamento(departamento)
                .withDocument("999999999999")
                .build();

        Funcionario funcionarioSalvo = funcionarioService.save(funcionario);
        assertThat(funcionarioSalvo.getBirthday(), is(equalTo(sdf.parse("05/05/1990"))));
        assertThat(funcionarioSalvo.getName(), is(equalToIgnoringCase("hulk")));
        assertThat(funcionarioSalvo.getDepartamento().getName(), is(equalToIgnoringCase("Marvel")));
        assertThat(funcionarioSalvo.getCargo().getName(), is(equalToIgnoringCase("Super-herói")));
    }

    @Test
    public void saveListaFuncionarios() throws ParseException {
        Cargo cargo = cargoService.save(Cargo
                .CargoBuilder
                .aCargo()
                .withName("Deus")
                .build());
        Cargo cargo1 = cargoService.save(Cargo
                .CargoBuilder
                .aCargo()
                .withName("Super-Herói")
                .build());
        List<Funcionario> funcionarios = Arrays.asList(Funcionario
                        .FuncionarioBuilder
                        .aFuncionario()
                        .withName("Thor")
                        .withBirthday(sdf.parse("01/01/0001"))
                        .withCargo(cargo)
                        .build(),
                Funcionario
                        .FuncionarioBuilder
                        .aFuncionario()
                        .withName("Hulk")
                        .withCargo(cargo1)
                        .build());
        Iterable<Funcionario> funcionariosSalvos = funcionarioService.saveAll(funcionarios);
        Iterator<Funcionario> iterator = funcionariosSalvos.iterator();
        Funcionario funcionarioSalvo1 = iterator.next();
        assertThat(funcionarioSalvo1.getName(), is(equalToIgnoringCase("thor")));
        assertThat(funcionarioSalvo1.getCargo().getName(), is(equalToIgnoringCase("deus")));
        Funcionario funcionarioSalvo2 = iterator.next();
        assertThat(funcionarioSalvo2.getName(), is(equalToIgnoringCase("hulk")));
        assertThat(funcionarioSalvo2.getCargo().getName(), is(equalToIgnoringCase("super-herói")));
    }
}