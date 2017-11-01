package com.lanou.human_resource.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/24.
 */
public class Post {
    private String postId; //职务编号
    private String postName;  //职务名称


    //职务和员工的关系：一对多，一个职务可以有多个员工
    private Set<Staff> staffs = new HashSet<Staff>();
    //职务和部门的关系：多对一，多个职务同属于一个部门
    private Department department;
    public Post() {
    }

    public Post(String postName, Department department) {
        this.postName = postName;
        this.department = department;
    }

    public Post(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

    public Post(String postId, String postName, Department department) {
        this.postId = postId;
        this.postName = postName;
        this.department = department;
    }

    public Post(String postId, String postName, Set<Staff> staffs) {
        this.postId = postId;
        this.postName = postName;
        this.staffs = staffs;
    }

    public Post(String postName) {
        this.postName = postName;

    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}