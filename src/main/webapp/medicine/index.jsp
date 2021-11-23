<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
	
	String imgPath=request.getScheme()+"://"+request.getServerName()+":"+
			request.getServerPort()+"/Hospital-pic/";
	pageContext.setAttribute("imgPath", imgPath);
%>
<!DOCTYPE html>
<html>
<base href="<%=this.getServletContext().getContextPath() %>/medicine/">
<head>
    <title>药品查询</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    	$(function(){
    		$("#newNav").bind("click",function(){
    			window.location.href="${pageContext.request.contextPath}/medicine/add.jsp"
    		})
    		$("#checkall").bind("change",function(){
    			$("tbody input").prop("checked",$(this).prop("checked"))
    		})
    		$("#delAll").bind("click",function(){
    			$("tbody input:checked").each(function(){
    				alert($(this).val())
    			})
    		})
    		$("#ret").bind("click",function(){
    			$("#name").val("");
    			$("#type option:first").prop("selected", 'selected');
    		})
    	})
	</script>
</head>
<body>

<form action="${pageContext.request.contextPath }/medicine/search" method="get" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	    <tr>
	        <td width="10%" class="tableleft">药品名称：</td>
	        <td><input type="text" id="name" name="name"/></td>
			
	        <td width="10%" class="tableleft">药品类型：</td>
	        <td>
		        <select name="type" id="type">
		        <c:if test="${medicine.type == 1 }">selected='selected'</c:if>
		        	<option value="0" >==请选择==</option>
		       		<option value="1"  <c:if test="${medicine.type == 1 }">selected='selected'</c:if>>处方药</option>
			        <option value="2"  <c:if test="${medicine.type == 2 }">selected='selected'</c:if>>中药</option>
			        <option value="3"  <c:if test="${medicine.type == 3 }">selected='selected'</c:if>>西药</option>
		        </select>
	        </td>
	    </tr>
	    <tr>
			  <td colspan="4">
				<center>
					<input id="find" type="submit" class="btn btn-primary" value="查询"/>
			  		<input id="ret" type="button" class="btn btn-primary" value="清空"/> 
				</center>
	        </td>
	    </tr>
	</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" ></th>
        <th>药品编号</th>
        <th>药品名称</th>
        <th>图片</th>
        <th>药品类型</th>
        <th>简单描述</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${page.list }" var="medicine">
			<tr>
				<td><input type="checkbox" value="${medicine.mid }"></td>
				<td>${medicine.mid }</td>
				<td>${medicine.name }</td>
				<td><img width="80px" height="50px" alt="photo" src="${pageContext.request.contextPath }/Images/${medicine.picture }"></td>
				<td><c:choose>
					<c:when test="${medicine.type == 1 }">处方药</c:when>
					<c:when test="${medicine.type == 2 }">中药</c:when>
					<c:when test="${medicine.type == 3 }">西药</c:when>
				</c:choose></td>
				<td>${medicine.descs }</td>
				<td><a href="${pageContext.request.contextPath }/medicine/detail?mid=${medicine.mid}&edit=false">查看</a>|<a href="${pageContext.request.contextPath }/medicine/detail?mid=${medicine.mid}&edit=true">编辑</a></td>
			</tr>
		</c:forEach>
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  			<div class="inline pull-right page">
	  			<a href='${requestUri }?name=${medicine.name}&type=${medicine.type}' >首页</a> 
		        <a href='${requestUri }?name=${medicine.name}&type=${medicine.type}&p=${page.prePage}'>上一页</a>
		        <a href='${requestUri }?name=${medicine.name}&type=${medicine.type}&p=${page.pageNum + 1}'>下一页</a> 
		        <a href='${requestUri }?name=${medicine.name}&type=${medicine.type}&p=${page.pages}'>尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${page.total}</span>条记录
			  <span class='current'>${page.pageNum} / ${page.pages} </span>页
		  </div>
		 <div>
			<button type="button" class="btn btn-success" id="newNav">添加新药</button>	
			<button type="button" class="btn btn-success" id="delAll" >批量删除</button>		
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
