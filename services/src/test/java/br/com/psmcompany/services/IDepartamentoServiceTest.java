package br.com.psmcompany.services;

import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class IDepartamentoServiceTest {
    @Autowired
    IDepartamentoService departamentoService;

    @Autowired
    IFuncionarioService funcionarioService;

    @Test
    public void save() {
        Funcionario chefe = Funcionario
                .FuncionarioBuilder
                .aFuncionario()
                .withName("John Barbacue")
                .build();

        Departamento departamento = departamentoService.save(
                Departamento.
                        DepartamentoBuilder
                        .aDepartamento()
                        .withName("Catalogo")
                        .withChefe(chefe)
                        .build()
        );
        assertThat(departamento.getId(), is(notNullValue()));
        assertThat(departamento.getName(), is(equalToIgnoringCase("Catalogo")));
        assertThat(departamento.getChefe().getName(), is(equalToIgnoringCase("john barbacue")));
    }
}