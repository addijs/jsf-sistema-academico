package br.edu.ifpb.sistema_academico.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="siac_user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String username;

  @Column
  private String senha;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", senha='" + senha + '\'' +
            '}';
  }

  public void setId(Long id) {
    this.id = id;
  }
}
