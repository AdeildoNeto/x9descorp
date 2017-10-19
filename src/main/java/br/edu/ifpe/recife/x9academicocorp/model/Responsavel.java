package br.edu.ifpe.recife.x9academicocorp.model;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aldo_neto
 */
@Entity
@Table(name = "responsavel")
@PrimaryKeyJoinColumn(name = "idresponsavel", referencedColumnName = "idusuario")
public class Responsavel extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "parentesco")
    private String parentesco;
    @OneToMany(mappedBy = "responsavelIdresponsavel")
    private List<Aluno> alunoList;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Responsavel() {
    }

   /* public Responsavel(Integer idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public Responsavel(Integer idresponsavel, String parentesco) {
        this.idresponsavel = idresponsavel;
        this.parentesco = parentesco;
    }

    public Integer getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Integer idresponsavel) {
        this.idresponsavel = idresponsavel;
    }*/

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsavel != null ? idresponsavel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.idresponsavel == null && other.idresponsavel != null) || (this.idresponsavel != null && !this.idresponsavel.equals(other.idresponsavel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpe.recife.pdsw.model.Responsavel[ idresponsavel=" + idresponsavel + " ]";
    }
    */
}
