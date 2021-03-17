package br.edu.ifpb.sistema_academico.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {
  // Classe que fornece um EntityManager para todos os beans
  // que precisam desse recurso através da annotation @Inject

  private static final EntityManagerFactory factory =
      Persistence.createEntityManagerFactory("siac-postgresql");

  @Produces
  @RequestScoped
  public EntityManager createEntityManager() {
    return factory.createEntityManager();
  }

  public void closeEntityManager(@Disposes EntityManager entityManager) {
    entityManager.close();
  }
}
