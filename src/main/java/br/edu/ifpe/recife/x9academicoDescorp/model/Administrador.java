/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDescorp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author aldo_neto
 */
@Entity

@Table(name = "administrador")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "idAdministrador", referencedColumnName = "idUsuario")

public class Administrador extends Usuario implements Serializable {

    @NotBlank
    @Size(max = 30)
    @Column(name = "cargo")
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
