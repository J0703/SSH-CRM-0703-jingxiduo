package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.DepartmentDao;
import com.lanou.human_resource.dao.impl.DepartmentDaoImpl;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    @Qualifier("departmentDao")
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
    public List<Department> findAll(String hql)
    {
        return departmentDao.findAll(hql);
    }

    @Override
    public Department findById(String depId) {
        return departmentDao.findById(depId,Department.class);
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }


}
