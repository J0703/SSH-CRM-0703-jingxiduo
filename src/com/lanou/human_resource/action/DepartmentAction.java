package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.impl.DepartmentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class DepartmentAction extends ActionSupport {

    private List<Department> departments;
    private DepartmentService departmentService;
    private Department department;
    private String depName;
    private String depId;

    /**
     * 查询部门
     * @return
     */
    public String findDepartment(){
        DepartmentService departmentService = new DepartmentServiceImpl();
        String hql ="from Department";
        departments = departmentService.findAll(hql);
        return SUCCESS;
    }

    /**
     * 通过id查询
     * @return
     */
    public String findById(){

        return SUCCESS;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
