package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.DepartmentDao;
import com.lanou.human_resource.dao.impl.DepartmentDaoImpl;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.service.DepartmentService;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentDao departmentDao;//持有的dao层对象

    public DepartmentServiceImpl() {
        departmentDao =  new DepartmentDaoImpl();
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAll(String hql) {
        return departmentDao.findAll(hql);
    }

    @Override
    public Department findById(String depId) {
        return departmentDao.findById(depId,Department.class);
    }


}
