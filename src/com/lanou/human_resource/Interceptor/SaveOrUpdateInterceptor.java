package com.lanou.human_resource.Interceptor;

import com.lanou.human_resource.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by jbtms940317 on 17/10/27.
 */
public class SaveOrUpdateInterceptor extends MethodFilterInterceptor {
    /**
     * 拦截，名字为klose的员工为管理员
     * @param actionInvocation
     * @return
     * @throws Exception
     */

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");

        if(!staff.getDepartment().getDepName().equals("人力资源部")){
            return "reject";
        }
        //登录放行
        return actionInvocation.invoke();
    }
}
