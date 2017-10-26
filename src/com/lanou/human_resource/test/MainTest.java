package com.lanou.human_resource.test;

import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class MainTest {
    private Session session;
    private Transaction transaction;

    @Before
    public void init(){
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destroy(){
        transaction.commit();
        HibernateUtil.closeSession();
    }

    @Test
    public void save(){

        Department department = new Department("教研部");
        Post post = new Post("教研总监");
        Post post1 = new Post("Java主管");
        Post post2 = new Post("Java讲师");

        department.getPosts().add(post);
        department.getPosts().add(post1);
        department.getPosts().add(post2);
        session.save(department);

        Department department1 = new Department("人力资源部");
        Post post3 = new Post("人力资源总监");
        Post post4 = new Post("人力资源主管");
        Post post5 = new Post("人力资源顾问");

        department1.getPosts().add(post3);
        department1.getPosts().add(post4);
        department1.getPosts().add(post5);
        session.save(department1);

        Staff staff = new Staff("Faker");
        staff.setPost(post);
        staff.setDepartment(department);
        session.save(staff);

        Staff staff1 = new Staff("Uzi");
        staff1.setPost(post3);
        staff1.setDepartment(department1);
        session.save(staff1);

        transaction.commit();
    }
}
