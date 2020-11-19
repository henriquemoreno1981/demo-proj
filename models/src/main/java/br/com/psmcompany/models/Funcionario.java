package br.com.psmcompany.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Funcionario")
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "funcionario_id")
    private Integer id;

    @NotNull
    @Column(name = "funcionario_name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "funcionario_birthday")
    private Date birthday;

    @Size(max=50)
    @Column(name = "funcionario_document", columnDefinition = "nvarchar(50)")
    private String document;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id")
    private Cargo cargo;

    @OneToMany(
            cascade = { CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Departamento> historicoDepartamentos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "departamento_id")
    private Departamento departamento;

    public Funcionario() {
    }

    public Funcionario(Integer id, String name, Date birthday, String document, Cargo cargo, List<Departamento> historicoDepartamentos, Departamento departamento) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.document = document;
        this.cargo = cargo;
        this.historicoDepartamentos = historicoDepartamentos;
        this.departamento = departamento;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Departamento> getHistoricoDepartamentos() {
        return historicoDepartamentos;
    }

    public void setHistoricoDepartamentos(List<Departamento> historicoDepartamentos) {
        this.historicoDepartamentos = historicoDepartamentos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public static final class FuncionarioBuilder {
        private Integer id;
        private String name;
        private Date birthday;
        private String document;
        private Cargo cargo;
        private List<Departamento> historicoDepartamentos = new ArrayList<>();
        private Departamento departamento;

        private FuncionarioBuilder() {
        }

        public static FuncionarioBuilder aFuncionario() {
            return new FuncionarioBuilder();
        }

        public FuncionarioBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public FuncionarioBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public FuncionarioBuilder withBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public FuncionarioBuilder withDocument(String document) {
            this.document = document;
            return this;
        }

        public FuncionarioBuilder withCargo(Cargo cargo) {
            this.cargo = cargo;
            return this;
        }

        public FuncionarioBuilder withHistoricoDepartamentos(List<Departamento> historicoDepartamentos) {
            this.historicoDepartamentos = historicoDepartamentos;
            return this;
        }

        public FuncionarioBuilder withDepartamento(Departamento departamento) {
            this.departamento = departamento;
            return this;
        }

        public Funcionario build() {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(id);
            funcionario.setName(name);
            funcionario.setBirthday(birthday);
            funcionario.setDocument(document);
            funcionario.setCargo(cargo);
            funcionario.setHistoricoDepartamentos(historicoDepartamentos);
            funcionario.setDepartamento(departamento);
            return funcionario;
        }
    }
}
