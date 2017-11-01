package com.lanou.human_resource.Interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by jbtms940317 on 17/10/27.
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //判断session作用域是否有用户消息，如果有放行，如果没有则进行拦截
        Object obj = ActionContext.getContext().getSession().get("loginStaff");

        if(obj == null){
            //没有登录需要登录
            return "login";
        }
        //登录放行
        return actionInvocation.invoke();
    }
}
