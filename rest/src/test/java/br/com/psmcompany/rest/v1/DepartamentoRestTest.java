package br.com.psmcompany.rest.v1;

import br.com.psmcompany.TestMain;
import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import br.com.psmcompany.rest.util.HeaderRequestInterceptor;
import br.com.psmcompany.rest.util.LoggingRequestInterceptor;
import br.com.psmcompany.services.DepartamentoService;
import br.com.psmcompany.services.FuncionarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest(
        classes = {TestMain.class, DepartamentoRest.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class DepartamentoRestTest {

    @LocalServerPort
    int randomServerPort;

    RestTemplate restTemplate = DepartamentoRestTest.makeRestTemplate();

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    DepartamentoService departamentoService;

    @Autowired
    FuncionarioService funcionarioService;

    static RestTemplate makeRestTemplate() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
        //interceptors.add(new LoggingRequestInterceptor());
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Test
    void getDepartamentos() throws JsonProcessingException {
        departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento().withName("Departamento 1").build());
        departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento().withName("Departamento 2").build());
        departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento().withName("Departamento 3").build());
        List<Departamento> departamentos = this.restTemplate.getForObject(URI.create(String.format("http://localhost:%d/api/v1/departamento/", randomServerPort)), List.class);
        assertThat(departamentos.size(), is(greaterThanOrEqualTo(3)));
    }

    @Test()
    @ExceptionHandler({HttpClientErrorException.class})
    void testGetDepartamentos() {
        Departamento departamento1 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 4")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 4")
                                .withDocument("XYZ")
                                .build()))).build());
        Departamento departamento2 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 5")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 5")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Departamento departamento3 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 6")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 6")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Departamento departamento = restTemplate.getForObject(URI.create(String.format("http://localhost:%d/api/v1/departamento/%d", randomServerPort, departamento3.getId())), Departamento.class);
        assertThat(departamento.getId(), is(equalTo(departamento3.getId())));
        assertThat(departamento.getFuncionarios().size(), is(equalTo(5)));
    }

    @Test
    void testGetPessoasDepartamentoErro() {
        try {
            Departamento entityDepartamento = restTemplate.getForObject(URI.create(String.format("http://localhost:%d/api/v1/departamento/%d", randomServerPort, 99999999)), Departamento.class);
            fail("");
        } catch (Throwable t) {
            assertTrue("OK", true);
        }
    }

    @Test
    void getPessoasDepartamento() {
        Departamento departamento1 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 4")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 4")
                                .withDocument("XYZ")
                                .build()))).build());
        Departamento departamento2 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 5")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 5")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Departamento departamento3 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 6")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 6")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Funcionario[] funcionarios = restTemplate.getForObject(String.format("http://localhost:%d/api/v1/departamento/%d/funcionario/", randomServerPort, departamento3.getId()), Funcionario[].class);
        assertThat(funcionarios[0].getName(), is(containsStringIgnoringCase("Cara 1 do Departamento 6")));
    }

    @Test
    void testGetPessoasDepartamento() {
        Departamento departamento1 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 4")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 4")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 4")
                                .withDocument("XYZ")
                                .build()))).build());
        Departamento departamento2 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 5")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 5")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 5")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Departamento departamento3 = departamentoService.save(Departamento.DepartamentoBuilder.aDepartamento()
                .withName("Departamento 6")
                .withFuncionarios((List<Funcionario>) funcionarioService.saveAll(Arrays.asList(Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 1 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 2 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 3 do Departamento 6")
                                .withDocument("XYZ")
                                .build(),
                        Funcionario.FuncionarioBuilder.aFuncionario()
                                .withName("Cara 4 do Departamento 6")
                                .withDocument("XYZ")
                                .build())))
                .build());
        Funcionario funcionario = restTemplate.getForObject(String.format("http://localhost:%d/api/v1/departamento/%d/funcionario/%d", randomServerPort, departamento3.getId(), departamento3.getFuncionarios().get(0).getId()), Funcionario.class);
        assertThat(funcionario.getName(), is(containsStringIgnoringCase("Cara 1 do Departamento 6")));
        funcionario = restTemplate.getForObject(String.format("http://localhost:%d/api/v1/departamento/%d/funcionario/%d", randomServerPort, departamento3.getId(), departamento3.getFuncionarios().get(1).getId()), Funcionario.class);
        assertThat(funcionario.getName(), is(containsStringIgnoringCase("Cara 2 do Departamento 6")));
    }
}