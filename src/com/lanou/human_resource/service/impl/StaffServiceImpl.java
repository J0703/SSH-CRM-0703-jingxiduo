package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.StaffDao;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.StaffService;
import com.lanou.human_resource.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    @Qualifier("staffDao")
    private StaffDao staffDao;

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }


    @Override
    public List<Staff> findAll() {
        String hql = "from Staff";
        return staffDao.findAll(hql);
    }

    @Override
    public Staff findById(String staffId) {
        return staffDao.findById(staffId, Staff.class);
    }

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public Staff login(Map<String, Object> params) {
        String hql = "from Staff where loginName =:loginName and loginPwd =:loginPwd";
        return staffDao.findSingle(hql, params);
    }

    @Override
    public Staff findSingle(Map<String, Object> params) {
        String hql = "from Staff where loginName =:loginName";
        return staffDao.findSingle(hql, params);
    }

    @Override
    public PageBean<Staff> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Staff";
        String hql1 = "from Staff where 1=1";
        int totalRecord = staffDao.getTotalRecord(hql);
        PageBean<Staff> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Staff> data = staffDao.findWhole(hql1, pageBean.getStartIndex(), pageSize);
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public PageBean<Staff> findAdvancedQuery(List<Object> params, int pageNum, int pageSize) {
        String hql = "select count(*) from Staff where 1=1";
        String hql1 = "from Staff where 1=1";
        int totalRecord = staffDao.getTotalRecordStaff(hql,params);
        PageBean<Staff> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Staff> data = staffDao.findAdvancedQuery(hql1,params,pageBean.getStartIndex(),pageSize);
        pageBean.setData(data);
        return pageBean;
    }


}
