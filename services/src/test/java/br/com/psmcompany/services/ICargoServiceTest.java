package br.com.psmcompany.services;

import br.com.psmcompany.TestMain;
import br.com.psmcompany.data.InterfaceCargo;
import br.com.psmcompany.models.Cargo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.TransactionSystemException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {TestMain.class, CargoService.class, ICargoService.class, Cargo.class})
class ICargoServiceTest {

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private InterfaceCargo interfaceCargo;

    @Test
    void salvar() {
        Cargo cargo = Cargo.CargoBuilder.aCargo().withName("Contador").build();

        Cargo salvar = cargoService.save(cargo);
        assertThat(salvar.getName(), is(equalTo("Contador")));
    }

    @Test
    void salvarVazio() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            cargoService.save(null);
        });
        String expectedMessage = "Entity must not be null.; nested exception is java.lang.IllegalArgumentException: Entity must not be null.";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(containsStringIgnoringCase(expectedMessage)));
    }

    @Test
    void salvarCargoVazio() {
        Exception exception = assertThrows(TransactionSystemException.class, () -> {
            cargoService.save(new Cargo());
        });
        String expectedMessage = "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(containsStringIgnoringCase(expectedMessage)));
    }

    @Test
    void salvarCargoComId() {
        Exception exception = assertThrows(TransactionSystemException.class, () -> {
            cargoService.save(Cargo.CargoBuilder.aCargo().withId(1).build());
        });
        String expectedMessage = "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(containsStringIgnoringCase(expectedMessage)));
    }

    @Test
    void salvarCargoComNome() {
        Cargo cargo = cargoService.save(Cargo.CargoBuilder.aCargo().withName("James").build());

        assertThat(cargo.getName(), is(containsStringIgnoringCase("James")));
    }

    @Test
    void getById() {
        Cargo cargo1 = cargoService.save(Cargo.CargoBuilder.aCargo().withName("Escritor").build());

        Optional<Cargo> cargo = cargoService.findById(cargo1.getId());
        assertThat(cargo.get().getName(), is(equalTo("Escritor")));
    }

    @Test
    void deleteById() {
        Cargo cargo1 = cargoService.save(Cargo.CargoBuilder.aCargo().withName("Jogador").build());

        cargoService.deleteById(cargo1.getId());
    }

    @Test
    void findByName() {
        cargoService.save(Cargo.CargoBuilder.aCargo().withName("Piloto").build());

        Optional<Cargo> cargo = cargoService.findByName("Piloto");
        assertThat(cargo.get().getName(), is(equalTo("Piloto")));
    }

    @Test
    void getAll() {
        interfaceCargo.deleteAll();
        cargoService.save(Cargo.CargoBuilder.aCargo().withName("Desenhista").build());
        cargoService.save(Cargo.CargoBuilder.aCargo().withName("Administrador").build());
        cargoService.save(Cargo.CargoBuilder.aCargo().withName("Desenvolvedor").build());
        cargoService.save(Cargo.CargoBuilder.aCargo().withName("Designer").build());
        assertThat(cargoService.findAll().size(), is(equalTo(4)));
    }
}