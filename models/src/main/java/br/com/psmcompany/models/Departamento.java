package br.com.psmcompany.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departamento")
@Table(name = "deportamento", schema = "dbo")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "departamento_id")
    private Integer id;

    @Column(name = "departamento_name", columnDefinition = "varchar", length = 50)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id")
    )
    private List<Funcionario> funcionarios;

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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }


    public static final class DepartamentoBuilder {
        private Integer id;
        private String name;
        private List<Funcionario> funcionarios;

        private DepartamentoBuilder() {
        }

        public static DepartamentoBuilder aDepartamento() {
            return new DepartamentoBuilder();
        }

        public DepartamentoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public DepartamentoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepartamentoBuilder withFuncionarios(List<Funcionario> funcionarios) {
            this.funcionarios = funcionarios;
            return this;
        }

        public Departamento build() {
            Departamento departamento = new Departamento();
            departamento.setId(id);
            departamento.setName(name);
            departamento.setFuncionarios(funcionarios);
            return departamento;
        }
    }
}
