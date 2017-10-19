package br.edu.ifpe.recife.x9academicocorp.model;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aldo_neto
 */
@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idturma")
    private Integer idturma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "serie")
    private String serie;
    @Column(name = "numerosala")
    private Integer numerosala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtdAluno")
    private int qtdAluno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turmaIdturma")
    private List<Aluno> alunoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idturma")
    private List<Professor> professorList;

    public Turma() {
    }

    public Turma(Integer idturma) {
        this.idturma = idturma;
    }

    public Turma(Integer idturma, String nomecompleto, int qtdAluno) {
        this.idturma = idturma;
        this.serie = serie;
        this.qtdAluno = qtdAluno;
    }

    public Integer getIdturma() {
        return idturma;
    }

    public void setIdturma(Integer idturma) {
        this.idturma = idturma;
    }

   

    public Integer getNumerosala() {
        return numerosala;
    }

    public void setNumerosala(Integer numerosala) {
        this.numerosala = numerosala;
    }

    public int getQtdAluno() {
        return qtdAluno;
    }

    public void setQtdAluno(int qtdAluno) {
        this.qtdAluno = qtdAluno;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturma != null ? idturma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idturma == null && other.idturma != null) || (this.idturma != null && !this.idturma.equals(other.idturma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpe.recife.pdsw.model.Turma[ idturma=" + idturma + " ]";
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }
    
}
