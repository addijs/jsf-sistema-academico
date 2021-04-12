package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.models.SituacaoEnum;
import br.edu.ifpb.sistema_academico.services.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Named
@ViewScoped
public class RelatorioEstudantesBean implements Serializable {

  @Inject
  EstudanteService estudanteService;

  private List<Estudante> estudantes;

  @PostConstruct
  public void init() {
    this.estudantes = estudanteService.todosEstudantes();
  }

  public void realizarRelatorio() {
    estudantes.forEach(estudante -> {
      BigDecimal media;
      media = calcularMedia(estudante);
      estudante.setMedia(media);

      SituacaoEnum situacao;
      situacao = calcularSituacao(estudante);
      estudante.setSituacao(situacao);
    });
  }

  private BigDecimal calcularMedia(Estudante estudante) {
    BigDecimal nota1 = estudante.getNota1();
    BigDecimal nota2 = estudante.getNota2();
    BigDecimal nota3 = estudante.getNota3();
    BigDecimal notaFinal = estudante.getNotaFinal();
    BigDecimal media = null;

    if (nota1 != null &&
        nota2 != null &&
        nota3 != null) {

      media = (nota1.add(nota2).add(nota3)).divide(new BigDecimal(3), MathContext.DECIMAL32);

      if (notaFinal != null) {
        media = (
            media.multiply(new BigDecimal(60)).add
            (notaFinal.multiply(new BigDecimal(40))).divide
            (new BigDecimal(100), MathContext.DECIMAL32)
        );
      }
    }

    return media;
  }

  private SituacaoEnum calcularSituacao(Estudante estudante) {
    BigDecimal media = this.calcularMedia(estudante);
    BigDecimal notaFinal = estudante.getNotaFinal();
    Integer faltas = estudante.getFaltas();

    if (faltas != null) {
      if (faltas >= 25) return SituacaoEnum.RF;

      if (media != null) {
        if (media.compareTo(new BigDecimal(0)) > 0) {
          if (notaFinal != null) {

            if (
                media.compareTo(new BigDecimal(50)) == 0 ||
                media.compareTo(new BigDecimal(50)) == 1
            ) return SituacaoEnum.AP;
            else return SituacaoEnum.RP;

          } else {

            if (media.compareTo(new BigDecimal(40)) == -1) return SituacaoEnum.RP;
            if (media.compareTo(new BigDecimal(70)) == -1) return SituacaoEnum.FN;
            if (
                media.compareTo(new BigDecimal(70)) == 0 ||
                media.compareTo(new BigDecimal(70)) == 1
            ) return SituacaoEnum.AP;
          }
        }
      }

    }


    return SituacaoEnum.MT;
  }

  public List<Estudante> getEstudantes() {
    return estudantes;
  }

  public void setEstudantes(List<Estudante> estudantes) {
    this.estudantes = estudantes;
  }
}
