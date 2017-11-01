package com.lanou.human_resource.action;

import com.lanou.human_resource.domain.Staff;
import com.lanou.human_resource.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jbtms940317 on 17/10/30.
 */
@Controller("loginAction")
public class loginAction extends ActionSupport implements ModelDriven<Staff>{
    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    private String loginName;//登录名
    private String loginPwd;//登录密码
    private String newLoginPwd;//新密码
    private String reLoginPwd;//确认密码

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getReLoginPwd() {
        return reLoginPwd;
    }

    public void setReLoginPwd(String reLoginPwd) {
        this.reLoginPwd = reLoginPwd;
    }

    //登录验证
    public String login() {
        //先初始化一个Map集合
        Map<String, Object> params = new HashMap<>();
        params.put("loginName", loginName);
        if (staffService.findSingle(params) == null) {
            addActionError("用户不存在");
            return INPUT;
        }
        params.put("loginPwd", loginPwd);
        Staff staff = staffService.login(params);
        //将staff对象存入ServletContext域中，用于更改密码时使用
        ServletActionContext.getRequest().getServletContext().setAttribute("adminStaff", staff);
        if (staff == null) {
            addActionError("密码输入错误");
            return INPUT;
        }
        return SUCCESS;
    }

    //登录表单校验
    public void validateLogin() {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPwd)) {
            addActionError("用户名或密码不能为空");
        }
    }

    //修改密码
    public String updatePwd() {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        staff.setLoginPwd(newLoginPwd);
        staffService.update(staff);
        addFieldError("msg", "密码修改成功,请重新登录");
        return SUCCESS;
    }

    //修改密码表单校验
    public void validateUpdatePwd() {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        if (StringUtils.isBlank(newLoginPwd) || StringUtils.isBlank(loginPwd) || StringUtils.isBlank(reLoginPwd)) {
            addActionError("输入密码不能为空");
        } else {
            if (newLoginPwd.length() < 6) {
                addActionError("密码长度不得小于6位");
            } else {
                if (!staff.getLoginPwd().equals(loginPwd)) {
                    addActionError("与原始密码不一致,请重新输入");
                } else {
                    if (newLoginPwd.equals(loginPwd)) {
                        addActionError("新密码不得与原始密码相同");
                    } else {
                        if (!newLoginPwd.equals(reLoginPwd)) {
                            addActionError("两次输入密码不一致");
                        }
                    }
                }
            }
        }
    }

    @Override
    public Staff getModel() {
        return null;
    }
}
