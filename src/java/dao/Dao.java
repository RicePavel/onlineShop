/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Новый профиль
 */
public abstract class Dao<T> {

  public abstract Class getSupportedClass();

  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  protected Session currentSession() {
    return sessionFactory.getCurrentSession();
  }

  public Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public void deleteObj(final T obj) {
    currentSession().delete(obj);
  }

  public Long save(T obj) {
    return (Long) currentSession().save(obj);
  }

  public void refresh(T obj) {
    currentSession().refresh(obj);
  }

  public void saveOrUpdate(T obj) {
    currentSession().saveOrUpdate(obj);
  }

  public void update(T obj) {
    //currentSession().update(obj);
    currentSession().merge(obj);
  }

  /**
   * получить все
   *
   * @param noticeType тип сообщений. Может быть null
   * @return
   */
  @Transactional
  public List<T> getAll() {
    Criteria cr = currentSession().createCriteria(getSupportedClass());
    cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return cr.list();
  }

  public void delete(T obj) {
    currentSession().delete(obj);
  }

  public T getOne(String id) {
    return (T) currentSession().get(getSupportedClass(), Long.valueOf(id));
  }

  public T find(Long id) {
    if (id != null) {
      return (T) currentSession().get(getSupportedClass(), id);
    } else {
      return null;
    }
  }
  
  public void evict(T obj) {
    currentSession().evict(obj);
  }

  protected Criteria getCriteriaDistinctRootEntity(Class cl) {
    Criteria crit = currentSession().createCriteria(cl);
    crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return crit;
  }

  protected Criterion nullOrFalse(String fieldName) {
    return Restrictions.or(Restrictions.isNull(fieldName), Restrictions.eq(fieldName, false));
  }

}
