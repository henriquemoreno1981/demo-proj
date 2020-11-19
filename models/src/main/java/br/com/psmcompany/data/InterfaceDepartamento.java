package br.com.psmcompany.data;

import br.com.psmcompany.models.Departamento;
import br.com.psmcompany.models.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departamentoRepository")
public interface InterfaceDepartamento extends CrudRepository<Departamento, Integer> {

    @Query("select f from Departamento d left join d.funcionarios f ON (f.id IN ?2) where d.id = ?1")
    Funcionario findByIdAndFuncionario(Integer idDepartamento, Integer idFuncionario);
}
