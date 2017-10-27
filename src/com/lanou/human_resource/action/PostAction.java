package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/26.
 */
@Controller("PostAction")
@Scope("prototype")
public class PostAction extends ActionSupport{

    private List<Post> postList;

    @Autowired
    @Qualifier("postService")
    private PostService postService;
    private String postId;
    private String postName;
    private String depId;

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    //action方法中不能传参数
    public String findPost(){

        String hql = "from Post";

        postList = postService.findAll(hql);

        return SUCCESS;
    }

    /**
     * 更新或添加
     * @return
     */
    public String update(){
        if(StringUtils.isBlank(postId)){
            Post post = new Post(postId,postName);
            postService.save(post);
        }else{
            Post post = new Post(postId,postName);
            postService.update(post);
        }
        return SUCCESS;
    }


    public void validateUpdate(){
        if(StringUtils.isBlank(postName)){
            addActionError("输入职务名称不能为空");
        }
    }

}
