package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.PostDao;
import com.lanou.human_resource.service.PostService;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class PostServiceImpl implements PostService{
    private PostDao postDao;

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
