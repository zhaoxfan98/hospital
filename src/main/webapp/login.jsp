<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* request.getScheme():获取协议 http
		request.getServerName():获取服务器名  localhost
		request.getServerPort():获取端口号 8080
		request.getContextPath():获取项目根目录 hospital
		http://localhost:8080/hospital/login.jsp
	*/
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>优就业-在线医疗管理系统</title>
	<meta charset="UTF-8">
	<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
   <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/jquery-3.4.1.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background: url("Images/yy.jpg");
            background-size: 100%;
            background-repeat: no-repeat;
        }

        .form-signin {
            max-width: 400px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            background: rgba(255,255,255,0.5);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 24px;
            margin-left: 90px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
		
		
		#message{
			font-size: 14px;
			color:red;
			margin-left: 40px;
		}
		
		.input-block-level{
			width: 300px;
			margin-left: 40px;
		}
		.input-medium{
			margin-left: 40px;
		}
		.code_images{
			width: 115px;
			height: 35px;
			margin-top: -15px;
			margin-left: 10px;
		}
		.error{
			color: red;
			font-size: 12px;
		}
		
    </style>  
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="${pageContext.request.contextPath }/user/auth">
    	<input type="hidden" name="method" value="checkLogin">
        <h2 class="form-signin-heading" >在线医疗管理系统</h2>
        <span id="message" class="message">${errorMsg }</span><br>
        <input type="text" id="username" name="username" class="input-block-level" placeholder="账号">
        <input type="password" id="password" name="password" class="input-block-level" placeholder="密码" >
        <input type="text" id="verify" name="verify" class="input-medium" placeholder="验证码"> 
        <img id="code" class="code_images" src="${pageContext.request.contextPath }/code/getCode?time="+new Date() /> 
		<!--  
			验证码功能参考：
			https://www.cnblogs.com/jianlun/articles/5553452.html
		-->
        <p style="text-align: center;">
        <input id="login" type="button" value="登录" name="login" class="btn btn-large btn-primary" style="width: 150px;"/>
        <a href="${pageContext.request.contextPath }/regist.jsp">请先注册</a>
        </p>
    </form>
</div>	

</body>

<script type="text/javascript">
	$("#code").bind("click",function(){
		$(this).attr("src","${pageContext.request.contextPath }/code/getCode?time="+new Date())
	})
	
	$("#login").bind("click",function(){
		var msg = $("#message")
		var username = $("#username").val()
		if(username == null|username == ""){
			msg.text("用户名不能为空")
			return
		}
		
		var password = $("#password").val()
		if(password == null|password == ""){
			msg.text("密码不能为空")
			return
		}
		
		var verify = $("#verify").val()
		if(verify == null|verify == ""){
			msg.text("验证码不能为空")
			return
		}
		
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/code/checkCode",
			data:{"code":verify},
			success:function(msg){
				if(msg.statusCode == 200){
					$(".form-signin").submit();
				}else{
					msg.text("验证码错误")
				}
			}
		})
		
	})
</script>

</html>