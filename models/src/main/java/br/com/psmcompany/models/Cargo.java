package br.com.psmcompany.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Cargo")
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cargo_id")
    @NotNull
    private Integer id;

    @Column(name = "cargo_name", columnDefinition = "varchar(20)", nullable = false, length = 20)
    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    public Cargo() {

    }

    public Cargo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public static final class CargoBuilder {
        private Integer id;
        private String name;

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

        public Cargo build() {
            return new Cargo(id, name);
        }
    }
}
