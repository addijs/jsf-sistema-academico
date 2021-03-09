package br.edu.ifpb.sistema_academico.dao;

import br.edu.ifpb.sistema_academico.models.Aluno;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class AlunoDAO implements Serializable {
  // TODO Refatorar quando implementar Hibernate
  // DAO apenas simulando o banco de dados

  private Integer serial = 2;

  // Simulando banco
  private final Set<Aluno> alunos = new HashSet<>(
      Arrays.asList(
          new Aluno(1, "Adilson", "05/03/1999"),
          new Aluno(2, "Juniores", "03/05/2002")
      )
  );

  public boolean delete(Aluno aluno) {
    return alunos.remove(aluno);
  }

  public Integer insert(Aluno aluno) {
    this.serial++;
    aluno.setId(this.serial);
    alunos.add(aluno);
    return this.serial;
  }

  public List<Aluno> findAll() {
    return new ArrayList<>(alunos);
  }

}
