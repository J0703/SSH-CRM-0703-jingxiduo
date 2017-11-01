package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.PostService;
import com.lanou.human_resource.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/26.
 */
@Controller("PostAction")
@Scope("prototype")
public class PostAction extends ActionSupport implements ModelDriven<Post> {

    private List<Post> postList;

    @Autowired
    @Qualifier("postService")
    private PostService postService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    private String depId;
    private Post postDriven;
    private Post post;
    private List<Department> departments;
    private int pageNum;
    private int pageSize = 2;


    public String findByPage(){
        if(pageNum == 0){
            pageNum = 1;
        }
        PageBean<Post> pageBean = postService.findByPage(pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }

    //action方法中不能传参数
    public String findPost() {
        departments = departmentService.findAll();
        postList = postService.findAll();
        return SUCCESS;
    }

    public String showPost(){
        Map<String,Object>params = new HashMap();
        params.put("depId",depId);
        postList = postService.find(params);
        return SUCCESS;
    }

    /**
     * 更新或添加
     *
     * @return
     */
    public String update() {
        //在进入添加页面之前，查询出部门集合，点击保存，执行update方法。
        //其中的depId为表单中的name
        Department department = departmentService.findById(depId);
        if (StringUtils.isBlank(postDriven.getPostId())) {
            Post post1 = new Post();
            post1.setPostName(postDriven.getPostName());
            post1.setDepartment(department);
            postService.save(post1);
        } else {
            Post post1 = new Post(postDriven.getPostId(), postDriven.getPostName());
            post1.setDepartment(department);
            postService.update(post1);
        }
        return SUCCESS;
    }


    public void validateUpdate() {
        if (StringUtils.isBlank(postDriven.getPostName())) {
            addActionError("输入职务名称不能为空");
        }
    }

    public String findSingle(){
        post = postService.findById(postDriven.getPostId());
        departments = departmentService.findAll();
        return SUCCESS;
    }

    @Override
    public Post getModel() {
        postDriven = new Post();
        return postDriven;
    }


    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {this.departmentService = departmentService;}

    public Post getPostDriven() {
        return postDriven;
    }

    public void setPostDriven(Post postDriven) {
        this.postDriven = postDriven;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Post getPost() {return post;}

    public void setPost(Post post) {this.post = post;}

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
