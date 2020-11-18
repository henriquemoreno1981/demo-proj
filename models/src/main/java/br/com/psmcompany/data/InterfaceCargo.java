package br.com.psmcompany.data;

import br.com.psmcompany.models.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("cargoRepository")
public interface InterfaceCargo extends CrudRepository<Cargo, Integer> {
    Optional<Cargo> findByName(String name);
}
