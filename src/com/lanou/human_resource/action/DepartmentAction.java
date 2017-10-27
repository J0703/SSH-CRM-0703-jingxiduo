package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport {

    private List<Department> departments;
    @Qualifier("departmentService")
    @Autowired
    private DepartmentService departmentService;
    private Department department;
    private String depName;
    private String depId;

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

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }


    /**
     * 查询部门
     * @return
     */

    public String findDepartment(){
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

    /**
     * 更新或添加
     * @return
     */
    public String update(){
        if(StringUtils.isBlank(depId)){
            Department department = new Department(depId,depName);
            departmentService.save(department);
        }else{
            Department department = new Department(depId,depName);
            departmentService.update(department);
        }
        return SUCCESS;
    }

    public void validateUpdate(){
        if(StringUtils.isBlank(depName)){
            addActionError("输入部门名称不能为空");
        }
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
