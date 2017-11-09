/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDescorp.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@NamedQueries(
        {
            @NamedQuery(
                    name = "Responsavel.listar",
                    query = "Select u FROM Responsavel u"
            )
        }
)


@Entity
@Table(name = "responsavel")
@DiscriminatorValue(value = "R")
@PrimaryKeyJoinColumn(name = "idResponsavel", referencedColumnName = "idUsuario")

public class Responsavel extends Usuario implements Serializable {

    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

}
