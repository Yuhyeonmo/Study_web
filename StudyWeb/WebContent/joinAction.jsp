<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.UserDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="userID" name="user"/>
<jsp:setProperty property="userPassword" name="user"/>
<jsp:setProperty property="userName" name="user"/>
<jsp:setProperty property="userEmail" name="user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp 게시판 웹사이트</title>
</head>
<body>
<% if (user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null || user.getUserEmail() == null){
	
if(user.getUserID()==null){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('아이디를 입력하세요')");
	script.println("history.back()");
	script.println("/script>");
} if(user.getUserPassword()==null){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('비밀번호를 입력하세요')");
	script.println("history.back()");
	script.println("/script>");
} if(user.getUserName()==null){	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이름을 입력하세요')");
	script.println("history.back()");
	script.println("/script>");
	}
 if(user.getUserEmail()==null){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이메일을 입력하세요')");
	script.println("history.back()");
	script.println("/script>");
}
}
else {
	UserDAO userDAO = new UserDAO();
	int result = userDAO.join(user);
	
	if(result==-1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디 입니다.')");
		script.println("history.back()");
		script.println("/script>");
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href ='index.jsp'");
		script.println("/script>");
	}
}
%>

</body>
</html>