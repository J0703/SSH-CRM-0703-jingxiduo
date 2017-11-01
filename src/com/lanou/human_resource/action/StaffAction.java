package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.PostService;
import com.lanou.human_resource.service.StaffService;
import com.lanou.human_resource.service.impl.StaffServiceImpl;
import com.lanou.human_resource.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jbtms940317 on 17/10/26.
 */
//使用驱动模型，将action中参数清理
public class StaffAction extends ActionSupport implements ModelDriven<Staff>{

    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    @Autowired
    @Qualifier("postService")
    private PostService postService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    private List<Staff> staffList;
    private List<Post> postList;
    private List<Department> departments;
    private String staffId;
    private String  postId;
    private String depId;
    private Staff staff;
    private Staff staffDriven;
    private int pageNum;
    private int pageSize = 2;

    public String findStaff(){
        staffList = staffService.findAll();
        return SUCCESS;
    }

    /**
     * 分页查询
     */
    public String findByPage(){
        if(pageNum == 0){
            pageNum = 1;
        }
        PageBean<Staff>pageBean = staffService.findByPage(pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }

    /**
     * 高级查询
     * @return
     */
    public String find(){
        List<Object> params = new ArrayList<>();

        params.add(staffDriven.getStaffName());
        params.add(postId);
        params.add(depId);

        if(pageNum == 0){
            pageNum = 1;
        }
        PageBean<Staff>pageBean =staffService.findAdvancedQuery(params,pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }
    /**
     * 更新或添加
     * @return
     */
    public String update(){
        Date date = staffDriven.getOnDutyDate();
        Post post = postService.findById(postId);
        if(StringUtils.isBlank(staffDriven.getStaffId())) {
            Staff staff1 = new Staff(staffDriven.getLoginName(), staffDriven.getLoginPwd(),
                    staffDriven.getStaffName(), staffDriven.getGender(), date);
            staff1.setPost(post);
            staff1.setDepartment(post.getDepartment());
            staffService.save(staff1);
        }else{
            Staff staff1 = new Staff(staffDriven.getStaffId(), staffDriven.getLoginName(), staffDriven.getLoginPwd(),
                    staffDriven.getStaffName(), staffDriven.getGender(), date);
            staff1.setPost(post);
            staff1.setDepartment(post.getDepartment());
            staffService.update(staff1);
        }
        return SUCCESS;
    }

    public void validateUpdate(){
        if(StringUtils.isBlank(staffDriven.getLoginName())){
            addActionError("登录名不能为空");
        }
        if(StringUtils.isBlank(staffDriven.getLoginPwd())){
            addActionError("密码不能为空");
        }
        if(StringUtils.isBlank(staffDriven.getStaffName())){
            addActionError("员工名不能为空");
        }
        if(StringUtils.isBlank(staffDriven.getGender())){
            addActionError("性别不能为空");
        }

    }

    public String findSingleStaff(){
        departments = departmentService.findAll();
        postList =postService.findAll();
        staff = staffService.findById(staffDriven.getStaffId());
        return SUCCESS;
    }

    @Override
    public Staff getModel() {
        staffDriven = new Staff();
        return staffDriven;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public Staff getStaffDriven() {
        return staffDriven;
    }

    public void setStaffDriven(Staff staffDriven) {
        this.staffDriven = staffDriven;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
