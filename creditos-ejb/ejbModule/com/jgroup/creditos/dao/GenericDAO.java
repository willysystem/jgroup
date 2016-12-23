package com.jgroup.creditos.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Clase genérica para todos de la cual hereda todos los DAOs del proyecto. Además este 
 * inyecta <code>EntityManager</code> y lo hace disponible a todos los DAOs
 * @author Willy Hurtado
 * @param <E>
 */
@Stateless
public abstract class GenericDAO<E> {
	
	@PersistenceContext(unitName="PUnitCreditos")
	protected EntityManager em;
	
	public void persist(E e)  {
		em.persist(e);
	}
	
	public E merge(E e) {
		E merge = em.merge(e);
		return merge; 
	} 
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<E> findAll(Class<E> clazz) {
		
	    CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> q = cb.createQuery(clazz);
		q.select(q.from(clazz)) ;
		
		return em.createQuery(q).getResultList();
	}
	
	public void delete(E e, Object id){
		em.find(e.getClass(), id);
	}
	
}