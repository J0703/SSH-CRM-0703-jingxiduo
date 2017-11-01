package com.lanou.human_resource.dao.impl;

import com.lanou.human_resource.dao.BaseDao;
import com.lanou.human_resource.util.PageHibernateCallback;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Transactional
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    /**
     * 通过主键id查找对象
     *
     * @param id
     * @param tClass
     * @return
     */
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public T findById(Serializable id, Class<T> tClass) {
        Session session = currentSession();
        //根据主键id查询对象
        T t = (T) session.get(tClass, id);
        //返回查询对象
        return t;
    }

    @Override
    public List<T> findAll(String hql) {
        Session session = currentSession();

        //执行查询语句
        Query query = session.createQuery(hql);

        List<T> tList = query.list();

        return tList;
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = currentSession();

        Query query = session.createQuery(hql);

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }

        List<T> tList = query.list();

        return tList;
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        Session session = currentSession();
        Query query = session.createQuery(hql);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<T> tList = find(hql, params);
        if (tList.size() > 0) {
            return tList.get(0);
        }
        return null;
    }

    @Override
    public void save(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
    }

    @Override
    public void delete(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
    }

    @Override
    public List<T> findWhole(String hql, int startIndex, int pageSize) {
        return this.getHibernateTemplate().execute(new PageHibernateCallback<T>(hql, startIndex, pageSize));
    }

    @Override
    public int getTotalRecord(String hql) {
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(hql);
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }


}

