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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aldo_neto
 */
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idaluno")
    private Integer idaluno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "data_nascimento")
    private String dataNascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "deficiencia")
    private String deficiencia;
    @JoinColumn(name = "turma_idturma", referencedColumnName = "idturma")
    @ManyToOne(optional = false)
    private Turma turmaIdturma;
    @JoinColumn(name = "responsavel_idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne
    private Responsavel responsavelIdresponsavel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alunoIdaluno")
    private List<Relatorioparental> relatorioparentalList;

    public Aluno() {
    }

    public Aluno(Integer idaluno) {
        this.idaluno = idaluno;
    }

    public Aluno(Integer idaluno, String matricula, String nome, String dataNascimento, String deficiencia) {
        this.idaluno = idaluno;
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.deficiencia = deficiencia;
    }

    public Integer getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(Integer idaluno) {
        this.idaluno = idaluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Turma getTurmaIdturma() {
        return turmaIdturma;
    }

    public void setTurmaIdturma(Turma turmaIdturma) {
        this.turmaIdturma = turmaIdturma;
    }

    public Responsavel getResponsavelIdresponsavel() {
        return responsavelIdresponsavel;
    }

    public void setResponsavelIdresponsavel(Responsavel responsavelIdresponsavel) {
        this.responsavelIdresponsavel = responsavelIdresponsavel;
    }

    public List<Relatorioparental> getRelatorioparentalList() {
        return relatorioparentalList;
    }

    public void setRelatorioparentalList(List<Relatorioparental> relatorioparentalList) {
        this.relatorioparentalList = relatorioparentalList;
    }

   
    
}
