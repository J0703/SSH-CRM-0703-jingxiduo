package com.lanou.human_resource.test;

import com.lanou.human_resource.action.DepartmentAction;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jbtms940317 on 17/10/25.
 */
public class MainTest {

    private ApplicationContext context;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    @Test
    public void testDepartment(){
        DepartmentAction departmentAction = (DepartmentAction) context.getBean("departmentAction");
        System.out.println(departmentAction.findDepartment());
        System.out.println(departmentAction.update());
    }
}
