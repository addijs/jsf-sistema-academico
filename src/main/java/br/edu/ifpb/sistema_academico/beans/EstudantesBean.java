package br.edu.ifpb.sistema_academico.beans;

import br.edu.ifpb.sistema_academico.dao.AlunoDAO;
import br.edu.ifpb.sistema_academico.models.Aluno;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Named
@ViewScoped
public class EstudantesBean implements Serializable {
  private List<Aluno> alunos;
  private AlunoDAO alunoDAO;
  private Aluno aluno = new Aluno();

  @PostConstruct
  public void init() {
    this.alunoDAO = new AlunoDAO();
    this.alunos = alunoDAO.findAll();
  }

  public String cadastrarAluno() {
    alunoDAO.insert(this.aluno);
    this.alunos = alunoDAO.findAll();

    this.aluno = new Aluno();
    return null;
  }

  public String excluirAluno(Aluno aluno) {
    alunoDAO.delete(aluno);
    this.alunos = alunoDAO.findAll();
    return null;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public List<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
  }
}
