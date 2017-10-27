package com.lanou.human_resource.service;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public interface PostService {
    List<Post> findAll(String hql);


    void save(Post post);

    void update(Post post);
}
