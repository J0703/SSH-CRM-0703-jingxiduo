package com.lanou.human_resource.dao.impl;

import com.lanou.human_resource.dao.StaffDao;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.util.PageBean;
import com.lanou.human_resource.util.PageHibernateCallback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff>
        implements StaffDao {

    @Override
    public int getTotalRecordStaff(String hql, List<Object> params) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object staffName = params.get(0);
        Object postId = params.get(1);
        Object depId = params.get(2);
        //声明lists集合
        List<Object> lists = new ArrayList<>();
        //高级查询的三大条件
        if (staffName.toString().trim().length() > 0) {
            stringBuffer.append("and staffName like ?");
            lists.add("'%" + staffName + "%'");
        }

        if (!postId.equals("-1")) {
            stringBuffer.append("and postId like ?");
            lists.add(postId);
        }
        if(!depId.equals("-1")){
            stringBuffer.append("and depId like ?");
            lists.add(depId);
        }
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(stringBuffer.toString(), lists.toArray());
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Staff> findAdvancedQuery(String hql, List<Object> params, int startIndex, int pageSize) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object staffName = params.get(0);
        Object postId = params.get(1);
        Object depId = params.get(2);
        //声明list集合
        List<Object> lists = new ArrayList<>();
        //高级查询
        if (staffName.toString().trim().length() > 0){
            stringBuffer.append("and staffName like ?");
            lists.add("%" + staffName + "%");
        }

        if (!postId.equals("-1")){
            stringBuffer.append("and postId like ?");
            lists.add(postId);
        }

        if(!depId.equals("-1")){
            stringBuffer.append("and depId like ?");
            lists.add(depId);
        }

            return this.getHibernateTemplate().execute(new PageHibernateCallback<Staff>(stringBuffer.toString(),lists.toArray(),startIndex,pageSize));
    }
}
