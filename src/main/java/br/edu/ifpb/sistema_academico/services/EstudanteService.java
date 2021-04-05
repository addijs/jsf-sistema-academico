package br.edu.ifpb.sistema_academico.services;

import br.edu.ifpb.sistema_academico.cdi.Transactional;
import br.edu.ifpb.sistema_academico.dao.EstudanteDAO;
import br.edu.ifpb.sistema_academico.models.Estudante;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class EstudanteService implements Serializable {

  @Inject
  private EstudanteDAO estudanteDAO;

  @Transactional
  public void cadastrar(Estudante estudante) {
    estudanteDAO.insert(estudante);
  }

  @Transactional
  public void excluir(Estudante estudante) {
    estudanteDAO.delete(estudante);
  }

  @Transactional
  public void alterar(Estudante estudante) {
    estudanteDAO.update(estudante);
  }

  public List<Estudante> todosEstudantes() {
    return estudanteDAO.findAll();
  }
}
