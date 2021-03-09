package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {
  private User admin = new User();

  public String efetuarLogin() {
    // Criar lógica
    return "main";
  }

  public String efetuarLogout() {
    this.admin = new User();
    return "login";
  }

  public User getAdmin() {
    return admin;
  }

  public void setAdmin(User admin) {
    this.admin = admin;
  }
}
