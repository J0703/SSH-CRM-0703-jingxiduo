package com.lanou.human_resource.dao;

import com.lanou.human_resource.domain.Staff;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface StaffDao extends BaseDao<Staff>{
    int getTotalRecordStaff(String hql,List<Object> params);

    List<Staff> findAdvancedQuery(String hql,List<Object> params, int startIndex, int pageSize);
}
