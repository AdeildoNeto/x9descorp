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
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idendereco")
    private Integer idendereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rua")
    private String rua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroend")
    private int numeroend;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UF")
    private String uf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cep")
    private String cep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoIdendereco")
    private List<Usuario> usuarioList;

    public Endereco() {
    }

    public Endereco(Integer idendereco) {
        this.idendereco = idendereco;
    }

    public Endereco(Integer idendereco, String rua, int numeroend, String cidade, String uf, String cep) {
        this.idendereco = idendereco;
        this.rua = rua;
        this.numeroend = numeroend;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Integer getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(Integer idendereco) {
        this.idendereco = idendereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroend() {
        return numeroend;
    }

    public void setNumeroend(int numeroend) {
        this.numeroend = numeroend;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idendereco != null ? idendereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.idendereco == null && other.idendereco != null) || (this.idendereco != null && !this.idendereco.equals(other.idendereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpe.recife.pdsw.model.Endereco[ idendereco=" + idendereco + " ]";
    }
    
}
