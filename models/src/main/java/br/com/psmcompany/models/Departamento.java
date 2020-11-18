package br.com.psmcompany.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departamento")
@Table(name = "deportamento")
public class Departamento {
    public Departamento() {

    }

    public Departamento(Integer id, String name, Funcionario chefe, List<Funcionario> funcionarios) {
        this.id = id;
        this.name = name;
        this.chefe = chefe;
        this.funcionarios = funcionarios;
    }

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
    private List<Funcionario> funcionarios;
}
