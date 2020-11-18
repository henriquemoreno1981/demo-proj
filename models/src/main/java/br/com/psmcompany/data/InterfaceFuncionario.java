package br.com.psmcompany.data;

import br.com.psmcompany.models.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("funcionario")
public interface InterfaceFuncionario extends CrudRepository<Funcionario, Integer> {
}
