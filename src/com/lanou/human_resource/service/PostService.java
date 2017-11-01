package com.lanou.human_resource.service;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface PostService {
    List<Post> findAll();

    List<Post> find(Map<String,Object>params);

    Post findById(String postId);

    void save(Post post);

    void update(Post post);

    PageBean<Post> findByPage(int pageNum,int pageSize);
}
