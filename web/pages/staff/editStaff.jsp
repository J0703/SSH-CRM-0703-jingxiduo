<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js">
	//显示职务
	function showPost(obj) {
		//1.获得选中部门
		var depId = obj.value;
		//2.发送ajax，通过部门查询职务
		//2.1 获得引擎  xml->dom->DOM HttpRequest(节点操作)
		var xmlhttp=null;
		if(window.XMLHttpRequest){

		}else if(window.ActiveXObject){
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		//2.2 设置回调函数
		xmlhttp.onreadystatechange = function () {
			//请求完成，正常响应
			if(xmlhttp.readyState == 4 && xmlhttp.status ==200){
				//3.获得数据，并展示，手动ajax获得json数据字符串
				var textData = xmlhttp.responseText;
				//3.1 将字符串手动转换json对象
				var jsonData = eval("("+textData+")");
				//获得select对象
				var postSelectElement = document.getElementById("postSelectId");
				postSelectElement.innerHTML ="<option value=>------请-选-择------</option>";

				//3.2  遍历
				for(var i = 0;i < jsonData.length;i++){
					var postObj = jsonData[i];
					//获得职务id
					var postId = postObj.postId;
					//获得职务名称
					var postName = postObj.postName;
					//3.3将数显示到select标签
					postSelectElement.innerHTML  +=  "<option value='"+postId+"'>"+postName+"</option>";
				}
			}
		};
		//2.3创建连接
		var url ="${pageContext.request.contextPath}/postAction"+depId;
		xmlhttp.open("GET",url);
		//2.4发送请求
		xmlhttp.send(null);
	}
</script>
</head>

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="44%" align="left">[员工管理]</td>
   
    <td width="52%"align="right">
    	<!-- 提交表单 -->
       <a href="javascript:void(0)" onclick="document.forms[0].submit()">
       	<img src="${pageContext.request.contextPath}/images/button/save.gif" />
       </a>
       <!-- 执行js，进行返回 -->
       <a href="javascript:void(0)" onclick="window.history.go(-1)"><img src="${pageContext.request.contextPath}/images/button/tuihui.gif" /></a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>

<form action="/crm2/staff/staffAction_edit.action" method="post">
	
	<input type="hidden" name="staffId" value="2c9091c14c78e58b014c78e7ecd90007"/>
	
	<table width="88%" border="0" class="emp_table" style="width:80%;">
	 <tr>
	    <td>登录名：</td>
	    <td><input type="text" name="loginName" value="赵六" /> </td>
	    <td>密码：</td>
	    <td><input type="password" name="loginPwd" value="54dfc11c8e9c49bab6068f473f913be9" /> </td>
	  </tr>
	 <tr>
	    <td>姓名：</td>
	    <td><input type="text" name="staffName" value="赵六" /> </td>
	    <td>性别：</td>
	    <td>
	    	<input type="radio" name="gender" checked="checked" value="男"/>男
	    	<input type="radio" name="gender" value="女"/>女
	    </td>
	  </tr>
	 <tr>
	    <td width="10%">所属部门：</td>
	    <td width="20%">
	    	<select name="crmPost.crmDepartment.depId"  onchange="changePost(this)">
			    <option value="">----请--选--择----</option>
			    <option value="ee050687bd1a4455a153d7bbb7000001" selected="selected">教学部</option>
			    <option value="ee050687bd1a4455a153d7bbb7000002">咨询部</option>
			</select>

	    </td>
	    <td width="8%">职务：</td>
	    <td width="62%">
	    	<select name="crmPost.postId" id="postSelectId">
			    <option value="">----请--选--择----</option>
			    <option value="2c9091c14c78e58b014c78e6b34a0003">总监</option>
			    <option value="2c9091c14c78e58b014c78e6d4510004" selected="selected">讲师</option>
			</select>
	    </td>
	  </tr>
	  <tr>
	    <td width="10%">入职时间：</td>
	    <td width="20%">
	    	<input type="text" name="onDutyDate" value="2012-02-12" readonly="readonly" onfocus="c.showMoreDay=true; c.show(this);"/>
	    </td>
	    <td width="8%"></td>
	    <td width="62%"></td>
	  </tr>
	</table>
</form>

</body>
</html>
