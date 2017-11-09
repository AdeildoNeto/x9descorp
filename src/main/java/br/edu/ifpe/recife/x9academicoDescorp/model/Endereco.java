/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDescorp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author aldo_neto
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @Column(name = "idEndereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @OneToOne(mappedBy = "endereco", optional = false)
    private Usuario usuario;

    @NotBlank
    @Size(max = 150)
    @Column(name = "rua")
    private String rua;

    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name = "numeroEndereco")
    private int numeroEndereco;

    @NotBlank
    @Size(max = 50)
    @Column(name = "cidade")
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "uf")
    private String uf;

    @NotNull
    @Pattern(regexp = "[0-90]{2}.[0-9]{3}-[0-9]{3}", message = "{br.edu.ifpe.recife.x9academicoDescorp.model.Endereco.cep}")
    @Column(name = "cep", length = 12)
    private String cep;

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
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
}
