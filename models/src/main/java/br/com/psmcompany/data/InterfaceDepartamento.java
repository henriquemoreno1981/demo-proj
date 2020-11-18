package br.com.psmcompany.data;

import br.com.psmcompany.models.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("departamentoRepository")
public interface InterfaceDepartamento extends CrudRepository<Departamento, Integer> {
}
