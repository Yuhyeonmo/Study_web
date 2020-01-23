<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="user.UserDAO"%>
<%@ page import ="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>    

<jsp:useBean id="user" class="user.UserDTO" scope = "page"></jsp:useBean>
<jsp:setProperty property="userID" name="user"/>
<jsp:setProperty property="userPassword" name="user"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp 게시판 웹사이트</title>
</head>
<body>
<%UserDAO userDao = new UserDAO();
int result = userDao.login(user.getUserID(), user.getUserPassword());
PrintWriter script = response.getWriter();
if(result == 1){
	
	script.println("<script>");
	script.println("<location.href = 'main.jsp'");
	script.print("</script>");	
}
else if(result == 0)
{
	script.println("<script>");
	script.println("alret('비밀번호가 틀립니다.!!')");
	script.println("history.back()");
	script.print("</script>");	
}

else {
	
	script.println("<script>");
	script.println("alert('DB오류가 발생했습니다.')");
	script.println("history.back()");
	script.println("</script>");
}
%>

</body>
</html>
