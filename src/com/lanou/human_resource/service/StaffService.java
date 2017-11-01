package com.lanou.human_resource.service;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface StaffService {
    List<Staff> findAll();

    Staff findById(String staffId);

    void save(Staff staff);

    void update(Staff staff);

    Staff login(Map<String,Object>params);

    Staff findSingle(Map<String,Object> params);

    PageBean<Staff> findByPage(int pageNum,int pageSize);

    PageBean<Staff> findAdvancedQuery(List<Object>params,int pageNum,int pageSize);
}
