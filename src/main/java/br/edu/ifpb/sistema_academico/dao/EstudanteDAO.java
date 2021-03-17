package br.edu.ifpb.sistema_academico.dao;

import br.edu.ifpb.sistema_academico.models.Estudante;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EstudanteDAO extends DAO<Estudante, Integer> {
  public EstudanteDAO() {
    super(Estudante.class);
  }
}
