package br.edu.ifpb.sistema_academico.cdi;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor {

  @Inject
  private EntityManager entityManager;

  @AroundInvoke
  public Object invoke(InvocationContext context) throws Exception {
    EntityTransaction transaction = entityManager.getTransaction();
    boolean created = false;

    try {
      if (!transaction.isActive()) {
        transaction.begin();
        created = true;
      }

      return context.proceed();
    } catch (Exception e) {
      if (transaction != null && created) {
        transaction.rollback();
      }

      throw e;
    } finally {
      if (transaction != null && transaction.isActive() && created) {
        transaction.commit();
      }
    }
  }
}
