package br.edu.ifpb.sistema_academico.dao;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<T, PK extends Serializable>  {
  T findById(PK id);
  List<T> findAll();
  void insert(T entity);
  void update(T entity);
  void delete(T entity);
}
