package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.DepartmentDao;
import com.lanou.human_resource.dao.impl.DepartmentDaoImpl;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;//持有的dao层对象

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAll()
    {
        String hql ="from Department";
        return departmentDao.findAll(hql);
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

    @Override
    public Department findById(String depId) {
        return departmentDao.findById(depId,Department.class);
    }

    @Override
    public PageBean<Department> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Department";
        String hql1 = "from Department where 1=1";
        int totalRecord = departmentDao.getTotalRecord(hql);
        PageBean<Department> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Department> data = departmentDao.findWhole(hql1, pageBean.getStartIndex(), pageSize);
        pageBean.setData(data);
        return pageBean;
    }


}
