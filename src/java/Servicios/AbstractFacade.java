/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

/**
 *
 * @author Damian
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean contains(T entity) {
        return getEntityManager().contains(entity);
    }

    public void clearAll() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
        flush();

    }

    public T edit(T entity) {
        T res=getEntityManager().merge(entity);
        flush();
        return res;
    }

    public void flush() {
        getEntityManager().flush();
        //clear();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        flush();
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public void clear() {
        getEntityManager().clear();

    }

    public List<T> findAll() {
        clearAll();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
