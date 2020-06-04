package pl.ice.GameMasterHelper.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(T t){
        Session session = entityManager.unwrap(SessionFactory.class).openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
        session.close();
    }

    protected Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Autowired
    public void setEntityManagerFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
