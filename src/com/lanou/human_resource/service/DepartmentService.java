package com.lanou.human_resource.service;

import com.lanou.human_resource.dao.BaseDao;
import com.lanou.human_resource.domain.Department;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface DepartmentService{

    List<Department> findAll(String hql);

    Department findById(String depId);

    void save(Department department);

    void update(Department department);

    void delete(Department department);
}
