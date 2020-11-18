package br.com.psmcompany.models;

import javax.persistence.*;

@Entity(name = "cargo")
@Table(name = "cargo", schema = "dbo")
public class Cargo {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    @Column(name = "cargo_id")
    private Integer id;

    @Column(name = "cargo_name", columnDefinition = "varchar", length = 20)
    private String name;

    @OneToMany(targetEntity = Cargo.class)
    private Cargo cargos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cargo getCargos() {
        return cargos;
    }

    public void setCargos(Cargo cargos) {
        this.cargos = cargos;
    }


    public static final class CargoBuilder {
        private Integer id;
        private String name;
        private Cargo cargos;

        private CargoBuilder() {
        }

        public static CargoBuilder aCargo() {
            return new CargoBuilder();
        }

        public CargoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public CargoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CargoBuilder withCargos(Cargo cargos) {
            this.cargos = cargos;
            return this;
        }

        public Cargo build() {
            Cargo cargo = new Cargo();
            cargo.setId(id);
            cargo.setName(name);
            cargo.setCargos(cargos);
            return cargo;
        }
    }
}
