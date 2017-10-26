package com.lanou.human_resource.dao;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface BaseDao<T> {
    /**
     * 根据主键id查询某个对象
     */
    T findById(Serializable id,Class<T> tClass);


    List<T> findAll(String hql);
    /**
     * 根据条件查询，返回查询到的结果集合
     * @param hql
     * @param params
     * @return
     */


    List<T> find(String hql, Map<String,Object> params);

    /**
     * 根据条件查询，返回查询到的第一个对象
     * @param hql
     * @param params
     * @return
     */
    T findSingle(String hql,Map<String,Object> params);

   /*
     更新数据
    */

    T update(String hql);

    /*
     删除数据

     */
    T delete(String hql);
}
