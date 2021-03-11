package br.edu.ifpb.sistema_academico.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Estudante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @Column(name = "data_nasc")
  @Temporal(TemporalType.DATE)
  private Date dataNascimento;

  private Integer faltas;
  private BigDecimal nota1;
  private BigDecimal nota2;
  private BigDecimal nota3;

  @Column(name = "nota_final")
  private BigDecimal notaFinal;

  @Transient
  private SituacaoEnum situacao;

  public Estudante() {}

  public Estudante(String nome, Date dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Integer getFaltas() {
    return faltas;
  }

  public void setFaltas(Integer faltas) {
    this.faltas = faltas;
  }

  public BigDecimal getNota1() {
    return nota1;
  }

  public void setNota1(BigDecimal nota1) {
    this.nota1 = nota1;
  }

  public BigDecimal getNota2() {
    return nota2;
  }

  public void setNota2(BigDecimal nota2) {
    this.nota2 = nota2;
  }

  public BigDecimal getNota3() {
    return nota3;
  }

  public void setNota3(BigDecimal nota3) {
    this.nota3 = nota3;
  }

  public BigDecimal getNotaFinal() {
    return notaFinal;
  }

  public void setNotaFinal(BigDecimal notaFinal) {
    this.notaFinal = notaFinal;
  }

  public SituacaoEnum getSituacao() {
    return situacao;
  }

  public void setSituacao(SituacaoEnum situacao) {
    this.situacao = situacao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Estudante estudante = (Estudante) o;
    return getId().equals(estudante.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
