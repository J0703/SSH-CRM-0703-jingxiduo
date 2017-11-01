<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>

    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script src="/js/jquery-3.2.1.js"></script>
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="39%" align="left">[员工管理]</td>

        <td width="57%" align="right">
            <%--高级查询 --%>
            <a href="javascript:void(0)" onclick="document.getElementById('conditionFormId').submit()"><img
                    src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif"/></a>
            <%--员工注入 --%>
            <a href="${pageContext.request.contextPath}/pages/staff/addStaff.jsp">
                <img src="${pageContext.request.contextPath}/images/button/tianjia.gif"/>
            </a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/Staff/findAdvancedQuery.action" method="post">
    <table width="88%" border="0" style="margin: 20px;">
        <tr>
            <td width="80px">部门：</td>
            <td width="200px">

                <select name="depId" id="departmentId" onchange="changePost(this)">
                    <option value="-1">--请选择部门--</option>
                    <s:iterator value="departments" var="depart">
                        <option value="${depart.depId}"
                                <c:if test="${depart.depId eq post.department.depId}">selected="selected"</c:if>>
                            ---${depart.depName}---
                        </option>
                    </s:iterator>
                </select>

            </td>
            <td width="80px">职务：</td>
            <td width="200px">

                <select name="postId" id="postId">
                    <option value="-1">--请选择职务--</option>
                    <s:iterator value="postList" var="post">
                        <option value="${post.postId}"
                                <c:if test="${post.postId eq staff.post.postId}">selected="selected"</c:if>>
                            ---${post.postName}---
                        </option>
                    </s:iterator>
                </select>

            </td>
            <td width="80px">姓名：</td>
            <td width="200px"><input type="text" name="staffName" size="12"/></td>
            <td></td>
        </tr>
    </table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/Staff/findByPageStaff.action" method="post">
<table width="100%" border="1">
    <tr class="henglan" style="font-weight:bold;">
        <td width="10%" align="center">登录名</td>
        <td width="10%" align="center">员工姓名</td>
        <td width="6%" align="center">性别</td>
        <td width="12%" align="center">入职时间</td>
        <td width="15%" align="center">所属部门</td>
        <td width="10%" align="center">职务</td>
        <td width="10%" align="center">编辑</td>
    </tr>
    <s:iterator value="#pageBean.data" var="staff">
        <tr class="tabtd1">
            <td align="center">${staff.loginName}</td>
            <td align="center">${staff.staffName}</td>
            <td align="center">${staff.gender}</td>
            <td align="center">${staff.onDutyDate}</td>
            <td align="center">${staff.post.department.depName}</td>
            <td align="center">${staff.post.postName}</td>
            <td width="7%" align="center">

                <a href="${pageContext.request.contextPath}/Staff/findSingleStaff.action?staffId=${staff.staffId}">
                    <img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>
        </tr>
    </s:iterator>
</table>
    <input type="hidden" id="pageNum" name="pageNum" value="<s:property value="pageBean.pageNum"/>">
</form>


<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
            <span>第<s:property value="#pageBean.pageNum"/>/<s:property value="#pageBean.totalPage"/>页</span>
            <span>
            <s:if test="#pageBean.pageNum gt 1">
                <a href="javascript:void(0)" onclick="showPage(1)">[首页]</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.pageNum - 1"/>)">[上一页]</a>&nbsp;&nbsp;
            </s:if>
            <s:if test="#pageBean.pageNum lt #pageBean.totalPage">
                <a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.pageNum + 1"/>)">[下一页]</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.totalPage"/>)">[尾页]</a>
            </s:if>
        </span>
        </td>
    </tr>
</table>

<script type="text/javascript">
    //页面加载
    $(function () {
        $.post("${pageContext.request.contextPath}/Depart/showDepart.action", null,
                function (data) {
                    var _html = "<option value='-1'>---请选择---</option>";
                    $.each(data, function (index, value) {
                        _html += '<option value="' + value.depId + '">' + value.depName + '</option>'
                    });
                    $("#departmentId").html(_html);
                }, "json");
        $("#departmentId").change(function () {
            $.post("${pageContext.request.contextPath}/Post/showPost.action",
                    {
                        depId: $("#departmentId").val()
                    },
                    function (data) {
                        var _html = "<option value='-1'>---请选择---</option>";
                        $.each(data, function (index, value) {
                            _html += '<option value="' + value.postId + '">' + value.postName + '</option>'
                        });
                        $("#postId").html(_html);
                    }, "json");
        })
    })
</script>


<script type="text/javascript">
    function showPage(num) {
        document.getElementById("pageNum").value = num;
        document.forms[1].submit();
    }
</script>
</body>
</html>
