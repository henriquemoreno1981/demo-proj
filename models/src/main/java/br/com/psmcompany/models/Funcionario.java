package br.com.psmcompany.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "funcionario")
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "funcionario_id")
    private Integer id;

    @Column(name = "funcionario_name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "funcionario_birthday")
    private Date birthday;

    @Column(name = "funcionario_document", columnDefinition = "nvarchar(50)")
    private String document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id")
    private Cargo cargo;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Departamento> historicoDepartamentos;

    @OneToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "departamento_id")
    private Departamento departamentos;

}
