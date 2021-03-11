package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.services.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EstudantesBean implements Serializable {

  @Inject
  private EstudanteService service;

  private Estudante estudante;
  private List<Estudante> estudantes;

  @PostConstruct
  public void init() {
    this.estudantes = service.todosEstudantes();
    this.estudante = new Estudante();
  }

  public String cadastrarAluno() {
    service.cadastrar(this.estudante);
    this.estudantes = service.todosEstudantes();

    this.estudante = new Estudante();
    return null;
  }

  public String excluirAluno(Estudante estudante) {
    service.excluir(estudante);
    this.estudantes = service.todosEstudantes();
    return null;
  }

  public Estudante getEstudante() {
    return estudante;
  }

  public void setEstudante(Estudante estudante) {
    this.estudante = estudante;
  }

  public List<Estudante> getEstudantes() {
    return estudantes;
  }

  public void setEstudantes(List<Estudante> estudantes) {
    this.estudantes = estudantes;
  }
}
