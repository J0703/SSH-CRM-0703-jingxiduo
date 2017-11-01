package com.lanou.human_resource.util;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/31.
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

    private String hql; //hql语句
    private Object[] params; //参数
    private int startIndex; //开始索引
    private int pageSize;  //每页显示的条目数

    public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
        this.hql = hql;
        this.params = params;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public PageHibernateCallback(Object[] params, int startIndex, int pageSize) {
        this.params = params;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public PageHibernateCallback(String hql, int startIndex, int pageSize) {
        this.hql = hql;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    @Override
    public List<T> doInHibernate(Session session) throws HibernateException {
        //1.通过hql语句获取query对象
        Query queryObject = session.createQuery(hql);
        //2.条件的设置
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                queryObject.setParameter(i, params[i]);
            }
        }
        //3.分页
        queryObject.setFirstResult(startIndex);
        queryObject.setMaxResults(pageSize);
        //4.查询所有
        return queryObject.list();
    }


}
