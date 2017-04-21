<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="jf_register">
		<h2>
			您好，欢迎光临
			<fmt:message key="b2cShowName" />
			！<a class="blue" href="${ctx}/LoginRegist_turnToLogin">请登录</a>
		</h2>
	</div>
	<div class="jf_register_banner">注册成功</div>
	<div class="jf_password">
		<ul>
			<li>感谢您选择<fmt:message key="b2cShowName" />，
			</li>
			<li>您的账号<span> <font color=red><strong>${param.bussinessId}</strong></font></span>已经注册成功。
			</li>
			<li>系统将会在<strong id="endtime"></strong>秒后跳转到登录页！
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="blue"
				href="${ctx}/LoginRegist_turnToLogin">直接登录</a></li>
		</ul>
	</div>
	<div class="jf_loginfooter">
		<fmt:message key="copyright" />
		&nbsp;版权所有&nbsp;| <a href="Login_loginHelpTips">联系我们</a>
		<fmt:message key="copyrightIcp" />
	</div>

	<script type="text/javascript">  
var i = 10;  
function remainTime(){  
    if(i==0){  
        location.href='${ctx}/hdfrontend31/index.htm?uid=1';
			}
			document.getElementById('endtime').innerHTML = i--;
			setTimeout("remainTime()", 1000);
		}
		remainTime();
	</script>

</body>
</html>