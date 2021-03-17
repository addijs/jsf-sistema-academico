package br.edu.ifpb.sistema_academico.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DAO<T, PK extends Serializable> implements DAOInterface<T, PK>, Serializable {

  @Inject
  protected EntityManager entityManager;

  protected Class<T> entityClass;

  public DAO() {}

  public DAO(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public T findById(PK id) {
    return this.entityManager.find(entityClass, id);
  }

  public List<T> findAll() {
    String jpqlQuery = String.format("select x from %s x", entityClass.getSimpleName());

    TypedQuery<T> query = this.entityManager.createQuery(jpqlQuery, entityClass);
    return query.getResultList();
  }

  public void insert(T entity) {
    this.entityManager.persist(entity);
  }

  public void update(T entity) {
    this.entityManager.merge(entity);
  }

  public void delete(T entity) {
    this.entityManager.remove(
        this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity)
    );
  }
}
