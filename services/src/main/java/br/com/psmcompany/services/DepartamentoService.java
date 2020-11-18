package br.com.psmcompany.services;

import br.com.psmcompany.data.InterfaceDepartamento;
import br.com.psmcompany.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService implements IDepartamentoService {
    private final InterfaceDepartamento interfaceDepartamento;

    public DepartamentoService(@Autowired InterfaceDepartamento interfaceDepartamento) {
        this.interfaceDepartamento = interfaceDepartamento;
    }

    @Override
    public Departamento save(Departamento departamento) {
        return interfaceDepartamento.save(departamento);
    }
}
