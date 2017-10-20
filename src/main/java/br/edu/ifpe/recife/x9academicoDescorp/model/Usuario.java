/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.x9academicoDescorp.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="dtype", discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario")
    private Long idUsuario;
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="login")
    private String login;
    
    @Column(name="senha")
    private String senha;
    
    @Column(name="CPF")
    private String cpf;
    
    @Column(name="telefone", length = 14)
    private String telefone;
    
    @Column(name="email", length = 30)
    private String email;
    
    /*
    @Temporal(TemporalType.DATE)
    @Column(name="data_nascimento")
    private Date dataNascimento;
    */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idEndereco", referencedColumnName= "idEndereco")
    private Endereco endereco;

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

   
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    public String getCpf() {
        return cpf;
    }

    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dataNascimento
     */
    /*
    public Date getDataNascimento() {
        return dataNascimento;
    }
*/
    /**
     * @param dataNascimento the dataNascimento to set
     */
    
    /*
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    */

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
