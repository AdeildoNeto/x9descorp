package br.edu.ifpe.recife.x9academicocorp.model;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 *
 * @author aldo_neto
 */
@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name="idprofessor", referencedColumnName = "idusuario")
public class Professor extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "idprofessor", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idturma", referencedColumnName = "idturma")
    @ManyToOne(optional = false)
    private Turma idturma;

    public Professor() {
    }

 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Turma getIdturma() {
        return idturma;
    }

    public void setIdturma(Turma idturma) {
        this.idturma = idturma;
    }

}
