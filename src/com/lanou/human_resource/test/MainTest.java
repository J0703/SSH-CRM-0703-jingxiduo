package com.lanou.human_resource.test;

import com.lanou.human_resource.action.DepartmentAction;
import com.lanou.human_resource.domain.Department;
import com.lanou.human_resource.domain.Post;
import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.DepartmentService;
import com.lanou.human_resource.service.StaffService;
import com.lanou.human_resource.util.HibernateUtil;
import org.apache.struts2.components.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

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
        //注意是service层
        DepartmentService service = (DepartmentService) context.getBean("departmentService");
        StaffService service1 = (StaffService) context.getBean("staffService");
        Department department1 = new Department("咨询部");
        Department department2 = new Department("职规部");
        Department department3 = new Department("教研部");
        Department department4 = new Department("就业部");
        Department department5 = new Department("人力资源部");

        Post post = new Post("教学总监",department3);
        Post post1 = new Post("Java主管",department3);
        Post post2 = new Post("Java讲师",department3);

        department3.getPosts().add(post);
        department3.getPosts().add(post1);
        department3.getPosts().add(post2);

        service.save(department3);

        Post post3 = new Post("职规总监",department2);
        Post post4 = new Post("职规顾问",department2);
        Post post5 = new Post("职业规划师",department2);

        department2.getPosts().add(post3);
        department2.getPosts().add(post4);
        department2.getPosts().add(post5);

        service.save(department2);

        Post post6 = new Post("就业总监",department4);
        Post post7 = new Post("就业顾问",department4);

        department4.getPosts().add(post6);
        department4.getPosts().add(post7);

        service.save(department4);

        Post post8 = new Post("人力资源总监",department5);
        Post post9 = new Post("人力资源顾问",department5);

        department5.getPosts().add(post8);
        department5.getPosts().add(post9);

        service.save(department5);

        Post post10 = new Post("咨询总监",department1);
        Post post11 = new Post("咨询顾问",department1);

        department1.getPosts().add(post10);
        department1.getPosts().add(post11);

        service.save(department1);

        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        java.util.Date date1 = calendar.getTime();
        java.util.Date date2 = calendar.getTime();

        Staff staff1 = new Staff("klose","123456","faker","男",date,post8,department5);
        Staff staff2 = new Staff("faker","654321","bang","女",date1,post11,department1);
        Staff staff3 = new Staff("wolf","000000","support","男",date2,post3,department2);
        post8.getStaffs().add(staff1);
        post9.getStaffs().add(staff2);
        post3.getStaffs().add(staff3);

        service1.save(staff1);
        service1.save(staff2);
        service1.save(staff3);


    }
}
