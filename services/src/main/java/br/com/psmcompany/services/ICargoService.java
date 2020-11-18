package br.com.psmcompany.services;

import br.com.psmcompany.models.Cargo;

import java.util.List;
import java.util.Optional;

public interface ICargoService {
    Cargo save(Cargo cargo);

    void deleteById(Integer id);

    Optional<Cargo> findById(Integer id);

    Optional<Cargo> findByName(String name);

    List<Cargo> findAll();
}
