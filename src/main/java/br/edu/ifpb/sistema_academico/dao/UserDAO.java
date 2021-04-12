package br.edu.ifpb.sistema_academico.dao;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.models.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Named
@SessionScoped
public class UserDAO extends DAO<User, Integer> {
    public UserDAO() {
        super(User.class);
    }

    public User encontrarUsuario(String username, String senha) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT siac_user " +
                            "FROM User siac_user " +
                            "WHERE siac_user.username = :username AND siac_user.senha = :senha",
                    User.class);
            query.setParameter("username", username);
            query.setParameter("senha", senha);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
