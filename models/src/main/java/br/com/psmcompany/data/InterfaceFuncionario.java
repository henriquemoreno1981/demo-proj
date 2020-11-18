package br.com.psmcompany.data;

import br.com.psmcompany.models.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("funcionarioRepository")
public interface InterfaceFuncionario extends CrudRepository<Funcionario, Integer> {
}
