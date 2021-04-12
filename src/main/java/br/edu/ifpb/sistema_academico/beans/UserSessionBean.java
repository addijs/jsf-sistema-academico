package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.models.User;
import br.edu.ifpb.sistema_academico.services.LoginService;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserSessionBean implements Serializable {
  private User admin = new User();

  @Inject
  private LoginService loginService;

  public String efetuarLogin() {
    System.out.println(admin.toString());
    User foundUser = loginService.verificarCredenciais(
            admin.getUsername(),
            admin.getSenha()
    );
    System.out.println(foundUser);
    if (foundUser == null) {
      return "";
    }
    // Criar lógica
    return "main?faces-redirect=true";
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
