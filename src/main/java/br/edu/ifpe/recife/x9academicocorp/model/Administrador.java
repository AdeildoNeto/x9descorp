package br.edu.ifpe.recife.x9academicocorp.model;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name="idadministrador", referencedColumnName = "idusuario")
public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cargo")
    private String cargo;
    @JoinColumn(name = "idadministrador", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Administrador() {
    }



    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
