package com.lanou.human_resource.service;

import com.lanou.human_resource.dao.BaseDao;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface DepartmentService{


    List<Department> findAll();

    void save(Department department);

    void update(Department department);

    void delete(Department department);

    Department findById(String depId);

    PageBean<Department> findByPage(int pageNum,int pageSize);
}
