package br.com.psmcompany.data;

import br.com.psmcompany.models.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("cargo")
public interface InterfaceCargo extends CrudRepository<Cargo, Integer> {
}
