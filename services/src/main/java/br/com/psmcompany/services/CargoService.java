package br.com.psmcompany.services;

import br.com.psmcompany.data.InterfaceCargo;
import br.com.psmcompany.models.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private InterfaceCargo interfaceCargo;

    public Cargo save(Cargo cargo) {
        return interfaceCargo.save(cargo);
    }

    public void deleteById(Integer id) {
        interfaceCargo.deleteById(id);
    }

    public Optional<Cargo> findById(Integer id) {
        return interfaceCargo.findById(id);
    }

    public Optional<Cargo> findByName(String name) {
        return interfaceCargo.findByName(name);
    }

    public List<Cargo> findAll() {
        List<Cargo> result = new ArrayList<>();
        Iterable<Cargo> findAll = interfaceCargo.findAll();
        for (Cargo cargo : findAll) {
            result.add(cargo);
        }
        return result;
    }
}
