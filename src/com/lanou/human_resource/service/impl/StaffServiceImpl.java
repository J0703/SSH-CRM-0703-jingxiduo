package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.StaffDao;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.StaffService;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class StaffServiceImpl implements StaffService{
    private StaffDao staffDao;

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }


    @Override
    public List<Staff> findAll(String hql) {
        return staffDao.findAll(hql);
    }
}
