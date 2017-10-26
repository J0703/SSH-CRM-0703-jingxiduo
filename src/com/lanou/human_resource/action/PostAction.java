package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.impl.DepartmentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/26.
 */
public class PostAction extends ActionSupport{

    private Set<Post> postList;

    public Set<Post> getPostList() {
        return postList;
    }

    public void setPostList(Set<Post> postList) {
        this.postList = postList;
    }

    public String findPost(String depId){
        DepartmentService departmentService = new DepartmentServiceImpl();

        Department selectDepart = departmentService.findById(depId);

        postList = selectDepart.getPosts();

        return SUCCESS;
    }
}
