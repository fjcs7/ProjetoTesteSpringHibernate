package net.codejava.spring.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
public class GenericDAO<T, PK extends Serializable> implements Dao<T, PK>{
 
    private final SessionFactory factory;
    private final Class<T> classe;
 
    public GenericDAO(final SessionFactory factory, final Class<T> classe) {
        this.factory = factory;
        this.classe = classe;
    }
 
    public void adiciona(final T t) {
        getSession().save(t);
    }
 
    private Session getSession() {
        return factory.getCurrentSession();
    }
 
    public void remove(final T t) {
        getSession().delete(t);
   }
 
    @SuppressWarnings("unchecked")
    public T busca(final PK id) {
        return (T) getSession().load(classe, id);
    }
 
    @SuppressWarnings("unchecked")
    public List<T> lista() {
        return getSession().createCriteria(classe).list();
    }
 
    public void atualiza(final T t) {
        getSession().merge(t);
    }
}