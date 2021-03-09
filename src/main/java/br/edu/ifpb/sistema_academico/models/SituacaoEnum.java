package br.edu.ifpb.sistema_academico.models;

public enum SituacaoEnum {
  MT("Matriculado"),
  AP("Aprovado"),
  RP("Reprovado"),
  FN("Na final"),
  RF("Reprovado por falta");

  private String descricao;

  SituacaoEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
