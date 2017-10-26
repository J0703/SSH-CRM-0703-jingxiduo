package com.lanou.human_resource.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jbtms940317 on 17/10/24.
 */
public class Department {
    private String depId;//部门编号
    private String depName;//部门名称
    private Set<Post> posts = new HashSet<Post>();

    public Department() {
    }

    public Department(String depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    public Department(String depId, String depName, Set<Post> posts) {
        this.depId = depId;
        this.depName = depName;
        this.posts = posts;
    }

    public Department(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                '}';

    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
