package br.com.psmcompany.data;

import br.com.psmcompany.TestMain;
import br.com.psmcompany.models.Cargo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {TestMain.class, Cargo.class, InterfaceCargo.class})
class InterfaceDepartamentoTest {

    @Autowired
    InterfaceCargo interfaceCargo;

    @Test
    public void testInterfaceCargo() {
        Cargo cargo = Cargo
                .CargoBuilder
                .aCargo()
                .withName("Cargo1")
                .build();
        interfaceCargo.save(cargo);
        Optional<Cargo> byId = interfaceCargo.findById(1);
        assertThat(byId.get().getId(), is(equalTo(1)));
        Iterable<Cargo> cargos = interfaceCargo.findAll();
        for (Cargo cargo1 : cargos) {
            assertThat(cargo1.getId(), is(equalTo(1)));
        }
        interfaceCargo.delete(byId.get());
        Iterable<Cargo> cargos2 = interfaceCargo.findAll();
        for (Cargo cargo1 : cargos2) {
            fail();
        }
    }
}