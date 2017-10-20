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

/**
 *
 * @author aldo_neto
 */


@Entity
@Table(name= "endereco")
public class Endereco implements Serializable {
 
    @Id
    @Column(name = "idEndereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    
    @OneToOne(mappedBy = "endereco", optional = false)
    private Usuario usuario;
    
    @Column(name = "rua")
    private String rua;
    
    @Column(name = "numeroEndereco")
    private int numeroEndereco;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "uf")
    private String uf;
    
    @Column(name = "cep")
    private String cep;

    /**
     * @return the idEndereco
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

   
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numeroEndereco
     */
    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    /**
     * @param numeroEndereco the numeroEndereco to set
     */
    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }
    }
