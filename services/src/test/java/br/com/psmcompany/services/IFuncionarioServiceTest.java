package br.com.psmcompany.services;

import br.com.psmcompany.models.Cargo;
import br.com.psmcompany.models.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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

        Funcionario funcionario = Funcionario.FuncionarioBuilder.aFuncionario()
                .withName("João")
                .withBirthday(sdf.parse("05/05/1990"))
                .withCargo(Cargo
                        .CargoBuilder
                        .aCargo()
                        .withName("Super-herói")
                        .build())
                .withDocument("999999999999")
                .build();

        Funcionario funcionarioSalvo = funcionarioService.save(funcionario);
        assertThat(funcionarioSalvo.getBirthday(), is(equalTo(sdf.parse("05/05/1990"))));
    }

}