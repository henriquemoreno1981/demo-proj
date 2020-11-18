package br.com.psmcompany.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "departamento")
@Table(name = "deportamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "departamento_id")
    private Integer id;
    @Column(name = "departamento_name", columnDefinition = "varchar(50)")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departamento_chefe_id", referencedColumnName = "funcionario_id")
    private Funcionario chefe;
    @ManyToMany
    @JoinTable(
            name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id")
    )
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Departamento() {

    }

    public Departamento(Integer id, String name, Funcionario chefe, List<Funcionario> funcionarios) {
        this.id = id;
        this.name = name;
        this.chefe = chefe;
        this.funcionarios = funcionarios;
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

    public Funcionario getChefe() {
        return chefe;
    }

    public void setChefe(Funcionario chefe) {
        this.chefe = chefe;
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
        private Funcionario chefe;
        private List<Funcionario> funcionarios = new ArrayList<>();

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

        public DepartamentoBuilder withChefe(Funcionario chefe) {
            this.chefe = chefe;
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
            departamento.setChefe(chefe);
            departamento.setFuncionarios(funcionarios);
            return departamento;
        }
    }
}
