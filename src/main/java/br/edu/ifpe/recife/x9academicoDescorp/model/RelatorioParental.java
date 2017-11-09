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
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name = "relatorioParental")
public class RelatorioParental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelatorioParental")
    private Long idRelatorioParental;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "lideranca")
    private double lideranca;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "trabalhoEmEquipe")
    private double trabalhoEmEquipe;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "participacaoEmSala")
    private double participacaoEmSala;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "motivacao")
    private double motivacao;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "criatividade")
    private double criatividade;

    @Size(max = 150)
    @Column(name = "observacoes")
    private String observacoes;

    public Long getIdRelatorioParental() {
        return idRelatorioParental;
    }

    public void setIdRelatorioParental(Long idRelatorioParental) {
        this.idRelatorioParental = idRelatorioParental;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getLideranca() {
        return lideranca;
    }

    public void setLideranca(double lideranca) {
        this.lideranca = lideranca;
    }

    public double getTrabalhoEmEquipe() {
        return trabalhoEmEquipe;
    }

    public void setTrabalhoEmEquipe(double trabalhoEmEquipe) {
        this.trabalhoEmEquipe = trabalhoEmEquipe;
    }

    public double getParticipacaoEmSala() {
        return participacaoEmSala;
    }

    public void setParticipacaoEmSala(double participacaoEmSala) {
        this.participacaoEmSala = participacaoEmSala;
    }

    public double getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(double motivacao) {
        this.motivacao = motivacao;
    }

    public double getCriatividade() {
        return criatividade;
    }

    public void setCriatividade(double criatividade) {
        this.criatividade = criatividade;
    }

}
