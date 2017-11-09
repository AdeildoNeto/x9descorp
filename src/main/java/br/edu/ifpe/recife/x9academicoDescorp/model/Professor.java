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
                    name = "Professor.PorNome",
                    query = "Select u FROM Professor u WHERE u.nome = ?1"
            )
        }
)


@Entity
@Table(name = "professor")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "idProfessor", referencedColumnName = "idUsuario")
public class Professor extends Usuario implements Serializable {

    
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Turma> turmas;

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
