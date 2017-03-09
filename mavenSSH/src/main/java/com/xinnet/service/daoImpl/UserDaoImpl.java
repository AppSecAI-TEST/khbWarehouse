package com.xinnet.service.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xinnet.dao.UserDao;
import com.xinnet.entity.User;
  
  
@Repository("UserDao")  
public class UserDaoImpl implements UserDao{  
      
    @Autowired  
    private SessionFactory sessionFactory;  
      
    private Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
  
    @Override  
    public User load(Integer id) {  
        return getCurrentSession().load(User.class, id);  
    }  
  
    @Override  
    public User get(Integer id) {  
        return getCurrentSession().get(User.class, id);  
    }  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public List<User> findAll() {  
        return getCurrentSession().createQuery("from user").getResultList();  
    }  
  
    @Override  
    public void persist(User entity) {  
        getCurrentSession().persist(entity);  
    }  
  
    @Override  
    public Integer save(User entity) {  
        return (Integer)getCurrentSession().save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(User entity) {  
        getCurrentSession().saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(Integer id) {  
        getCurrentSession().delete(this.get(id));  
    }  
  
    @Override  
    public void flush() {  
        getCurrentSession().flush();  
    }  
  
}
