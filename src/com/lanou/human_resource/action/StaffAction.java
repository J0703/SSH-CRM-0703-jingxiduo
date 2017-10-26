package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.StaffService;
import com.lanou.human_resource.service.impl.StaffServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/26.
 */
public class StaffAction extends ActionSupport{

    private List<Staff> staffList;

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public String findStaff(){
        StaffService staffService = new StaffServiceImpl();
        String hql ="from Staff";
        staffList = staffService.findAll(hql);
        return SUCCESS;
    }
}