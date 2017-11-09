package br.edu.ifpe.recife.x9academicoDescorp.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@NamedQueries(
        {
            @NamedQuery(
                    name = "Usuario.PorId",
                    query = "Select u FROM Usuario u WHERE u.idUsuario =?1"
            )
        }
)

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{br.edu.ifpe.recife.x9academicoDescorp.model.Usuario.nome}")
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "([^\\s])\\w+", message = "{br.edu.ifpe.recife.x9academicoDescorp.model.Usuario.login}")
    @Column(name = "login")
    private String login;

    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})",
            message = "{br.edu.ifpe.recife.x9academicoDescorp.model.Usuario.senha}")
    @Column(name = "senha")
    private String senha;

    @NotBlank
    @CPF
    @Column(name = "CPF")
    private String cpf;

    @Size(min = 10, max = 12)
    @Column(name = "telefone", length = 14)
    private String telefone;

    @NotBlank
    @Email
    @Column(name = "email", length = 30)
    private String email;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idEndereco", referencedColumnName = "idEndereco")
    private Endereco endereco;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
