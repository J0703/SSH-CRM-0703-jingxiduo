package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.PostDao;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class PostServiceImpl implements PostService{
    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;


    @Override
    public List<Post> findAll(String hql) {

        return postDao.findAll(hql);
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }
}
