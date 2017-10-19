package br.edu.ifpe.recife.x9academicocorp.model;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "relatorioparental")
public class Relatorioparental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrelatorioparental")
    private Integer idrelatorioparental;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "observacoes")
    private String observacoes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comportamento")
    private float comportamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "participacao_em_sala")
    private float participacaoEmSala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "facilidade_com_disciplina")
    private float facilidadeComDisciplina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trabalho_em_equipe")
    private float trabalhoEmEquipe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lideranca")
    private float lideranca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "motivacao")
    private float motivacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criatividade")
    private float criatividade;
    @JoinColumn(name = "aluno_idaluno", referencedColumnName = "idaluno")
    @ManyToOne(optional = false)
    private Aluno alunoIdaluno;

    public Relatorioparental() {
    }

    public Relatorioparental(Integer idrelatorioparental) {
        this.idrelatorioparental = idrelatorioparental;
    }

    public Relatorioparental(Integer idrelatorioparental, String observacoes, float comportamento, float participacaoEmSala, float facilidadeComDisciplina, float trabalhoEmEquipe, float lideranca, float motivacao, float criatividade) {
        this.idrelatorioparental = idrelatorioparental;
        this.observacoes = observacoes;
        this.comportamento = comportamento;
        this.participacaoEmSala = participacaoEmSala;
        this.facilidadeComDisciplina = facilidadeComDisciplina;
        this.trabalhoEmEquipe = trabalhoEmEquipe;
        this.lideranca = lideranca;
        this.motivacao = motivacao;
        this.criatividade = criatividade;
    }

    public Integer getIdrelatorioparental() {
        return idrelatorioparental;
    }

    public void setIdrelatorioparental(Integer idrelatorioparental) {
        this.idrelatorioparental = idrelatorioparental;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public float getComportamento() {
        return comportamento;
    }

    public void setComportamento(float comportamento) {
        this.comportamento = comportamento;
    }

    public float getParticipacaoEmSala() {
        return participacaoEmSala;
    }

    public void setParticipacaoEmSala(float participacaoEmSala) {
        this.participacaoEmSala = participacaoEmSala;
    }

    public float getFacilidadeComDisciplina() {
        return facilidadeComDisciplina;
    }

    public void setFacilidadeComDisciplina(float facilidadeComDisciplina) {
        this.facilidadeComDisciplina = facilidadeComDisciplina;
    }

    public float getTrabalhoEmEquipe() {
        return trabalhoEmEquipe;
    }

    public void setTrabalhoEmEquipe(float trabalhoEmEquipe) {
        this.trabalhoEmEquipe = trabalhoEmEquipe;
    }

    public float getLideranca() {
        return lideranca;
    }

    public void setLideranca(float lideranca) {
        this.lideranca = lideranca;
    }

    public float getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(float motivacao) {
        this.motivacao = motivacao;
    }

    public float getCriatividade() {
        return criatividade;
    }

    public void setCriatividade(float criatividade) {
        this.criatividade = criatividade;
    }

    public Aluno getAlunoIdaluno() {
        return alunoIdaluno;
    }

    public void setAlunoIdaluno(Aluno alunoIdaluno) {
        this.alunoIdaluno = alunoIdaluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrelatorioparental != null ? idrelatorioparental.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relatorioparental)) {
            return false;
        }
        Relatorioparental other = (Relatorioparental) object;
        if ((this.idrelatorioparental == null && other.idrelatorioparental != null) || (this.idrelatorioparental != null && !this.idrelatorioparental.equals(other.idrelatorioparental))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpe.recife.pdsw.model.Relatorioparental[ idrelatorioparental=" + idrelatorioparental + " ]";
    }
    
}
