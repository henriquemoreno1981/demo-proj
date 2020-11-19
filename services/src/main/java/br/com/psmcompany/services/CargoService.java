package br.com.psmcompany.services;

import br.com.psmcompany.data.InterfaceCargo;
import br.com.psmcompany.models.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService implements ICargoService {


    private final InterfaceCargo interfaceCargo;

    public CargoService(@Autowired InterfaceCargo interfaceCargo) {
        this.interfaceCargo = interfaceCargo;
    }

    @Override
    public Cargo save(@NotNull Cargo cargo) {
        return interfaceCargo.save(cargo);
    }

    @Override
    public void deleteById(@NotNull Integer id) {
        interfaceCargo.deleteById(id);
    }

    @Override
    public Optional<Cargo> findById(@NotNull Integer id) {
        return interfaceCargo.findById(id);
    }

    @Override
    public Optional<Cargo> findByName(@NotNull String name) {
        return interfaceCargo.findByName(name);
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> result = new ArrayList<>();
        Iterable<Cargo> findAll = interfaceCargo.findAll();
        for (Cargo cargo : findAll) {
            result.add(cargo);
        }
        return result;
    }
}
