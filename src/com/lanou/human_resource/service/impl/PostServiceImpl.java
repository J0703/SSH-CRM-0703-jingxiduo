package com.lanou.human_resource.service.impl;

import com.lanou.human_resource.dao.PostDao;
import com.lanou.human_resource.dao.impl.PostDaoImpl;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.PostService;
import com.lanou.human_resource.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/25.
 */
@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Override
    public List<Post> findAll() {
        String hql = "from Post";
        return postDao.findAll(hql);
    }

    @Override
    public List<Post> find(Map<String, Object> params) {
        String hql = "from Post where depId=:depId";
        return postDao.find(hql, params);
    }

    @Override
    public Post findById(String postId) {
        return postDao.findById(postId, Post.class);
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }

    @Override
    public PageBean<Post> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Post";
        String hql1 = "from Post where 1=1";
        int totalRecord = postDao.getTotalRecord(hql);
        PageBean<Post> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Post> data = postDao.findWhole(hql1, pageBean.getStartIndex(), pageSize);
        pageBean.setData(data);
        return pageBean;
    }
}
