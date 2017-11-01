package com.lanou.human_resource.action;

import com.lanou.human_resource.dao.DepartmentDao;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

    private List<Department> departments;
    @Qualifier("departmentService")
    @Autowired
    private DepartmentService departmentService;
    private Department department;
    private Department departmentDriven;
    private int pageNum;
    private int pageSize = 2;

    public String findByPage(){
        if(pageNum == 0){
            pageNum = 1;
        }
        PageBean<Department>pageBean = departmentService.findByPage(pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }

    /**
     * 查询部门
     * @return
     */

    /*action中不出现sql语句*/
    public String findDepartment(){
        departments = departmentService.findAll();
        return SUCCESS;
    }

    /**
     * 更新或添加
     * @return
     */
    public String update(){
        if(StringUtils.isBlank(departmentDriven.getDepId())){
            Department department1 = new Department(departmentDriven.getDepId(),departmentDriven.getDepName());
            departmentService.save(department1);
        }else{
            Department department1 = new Department(departmentDriven.getDepId(),departmentDriven.getDepName());
            departmentService.update(department1);
        }
        return SUCCESS;
    }

    public void validateUpdate(){
        if(StringUtils.isBlank(departmentDriven.getDepName())){
            addActionError("输入部门名称不能为空");
        }
    }

    public String findSingle(){
        department = departmentService.findById(departmentDriven.getDepId());

        return SUCCESS;
    }


    @Override
    public Department getModel() {
        departmentDriven = new Department();
        return departmentDriven;
    }


    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }


    public Department getDepartmentDriven() {
        return departmentDriven;
    }

    public void setDepartmentDriven(Department departmentDriven) {
        this.departmentDriven = departmentDriven;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
