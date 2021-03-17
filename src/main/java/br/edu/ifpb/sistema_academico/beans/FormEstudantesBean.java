package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.services.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class FormEstudantesBean implements Serializable {

  @Inject
  private EstudanteService estudanteService;

  @Inject
  Estudante estudante;

  @PostConstruct
  private void init() {
    Estudante estudanteFlash = (Estudante) this.getFlash("estudante");
    if (estudanteFlash != null) {
      this.estudante = estudanteFlash;
    }
  }

  public String cadastrarOuAlterarAluno() {
    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage mensagem;
    this.getFlash().setKeepMessages(true);

    if (this.estudante.getId() == null) {
      estudanteService.cadastrar(estudante);
      mensagem = new FacesMessage(
          FacesMessage.SEVERITY_INFO,
          "Estudante criado com sucesso!",
          null);
      context.addMessage(null, mensagem);

    } else {
      estudanteService.alterar(estudante);

      mensagem = new FacesMessage(
          FacesMessage.SEVERITY_INFO,
          "Estudante atualizado com sucesso!",
          null);
      context.addMessage(null, mensagem);
    }

    return "estudantes?faces-redirect=true";
  }

  private Object getFlash(String nome) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    return flash.get(nome);
  }

  private Flash getFlash() {
    return FacesContext.getCurrentInstance().getExternalContext().getFlash();
  }

  public Estudante getEstudante() {
    return estudante;
  }

  public void setEstudante(Estudante estudante) {
    this.estudante = estudante;
  }
}
