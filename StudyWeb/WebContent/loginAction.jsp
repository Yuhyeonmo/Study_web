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


if(result == 1){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("location.href = 'main.jsp'");
	script.println("</script>");
	session.setAttribute("userID", user.getUserID()); 

}

else if(result == 0){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('비밀번호가 틀립니다.')");
	script.println("history.back()");
	script.println("</script>");
}

else if(result == -1){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('존재하지 않는 아이디 입니다.')");
	script.println("history.back()");
	script.println("</script>");
}
else {
	PrintWriter script = response.getWriter();

	script.println("<script>");
	script.println("alert('DB오류가 발생했습니다.')");
	script.println("history.back()");
	script.println("</script>");
}
%>

</body>
</html>
