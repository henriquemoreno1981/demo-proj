package br.com.psmcompany.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "funcionario")
@Table(name = "funcionario", schema = "dbo")
public class Funcionario {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    @Column(name = "funcionario_id")
    private Integer id;
    @Column(name = "funcionario_name", columnDefinition = "nvarchar", length = 50)
    private String name;
    @Column(name = "funcionario_birthday")
    private Date birthday;
    @Column(name = "funcionario_document", columnDefinition = "nvarchar", length = 50)
    private String document;
    @ManyToMany(mappedBy = "funcionarios")
    private List<Integer> departamentos;

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

    public List<Integer> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Integer> departamentos) {
        this.departamentos = departamentos;
    }

    public static final class FuncionarioBuilder {
        private Integer id;
        private String name;
        private Date birthday;
        private String document;
        private List<Integer> departamentos;

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

        public FuncionarioBuilder withDepartamentos(List<Integer> departamentos) {
            this.departamentos = departamentos;
            return this;
        }

        public Funcionario build() {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(id);
            funcionario.setName(name);
            funcionario.setBirthday(birthday);
            funcionario.setDocument(document);
            funcionario.setDepartamentos(departamentos);
            return funcionario;
        }
    }
}
