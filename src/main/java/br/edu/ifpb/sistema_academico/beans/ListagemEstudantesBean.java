package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.services.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListagemEstudantesBean implements Serializable {

  @Inject
  private EstudanteService service;

  private List<Estudante> estudantes;

  @PostConstruct
  public void init() {
    this.estudantes = service.todosEstudantes();
  }

  public String excluirAluno(Estudante estudante) {
    service.excluir(estudante);
    this.estudantes = service.todosEstudantes();
    return null;
  }

  public String carregarAluno(Estudante estudante) {
    this.putFlash("estudante", estudante);
    return "form-estudante?faces-redirect=true";
  }

  private void putFlash(String nome, Object valor) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put(nome, valor);
  }

  public List<Estudante> getEstudantes() {
    return estudantes;
  }

  public void setEstudantes(List<Estudante> estudantes) {
    this.estudantes = estudantes;
  }
}
