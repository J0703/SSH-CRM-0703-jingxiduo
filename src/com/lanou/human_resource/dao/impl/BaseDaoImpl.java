package com.lanou.human_resource.dao.impl;

import com.lanou.human_resource.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        BaseDaoImpl.sessionFactory = sessionFactory;
    }

    /**
     * 通过主键id查找对象
     * @param id
     * @param tClass
     * @return
     */
    @Override
    public T findById(Serializable id, Class<T> tClass) {
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();

        T t = (T) session.get(tClass,id);

        transaction.commit();
        return null;
    }

    @Override
    public List<T> findAll(String hql) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //执行查询语句
        Query query = session.createQuery(hql);

        List<T> tList = query.list();

        transaction.commit();
        return tList;
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction =session.beginTransaction();

        Query query = session.createQuery(hql);

        if(params!=null && !params.isEmpty()){
            for(String key:params.keySet()){
                query.setParameter(key,params.get(key));
            }
        }

        List<T> tList = query.list();

        transaction.commit();
        return tList;
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        List<T> tList = find(hql,params);
        if(tList.size()>0){
            return tList.get(0);
        }
        return null;
    }



    @Override
    public T update(String hql) {
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();

        session.update(hql);
        transaction.commit();
        return null;
    }

    @Override
    public T delete(String hql) {
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();

        session.delete(hql);
        transaction.commit();
        return null;
    }
}
