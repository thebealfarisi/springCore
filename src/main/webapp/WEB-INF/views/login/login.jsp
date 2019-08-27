<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Login</title>
	</head>
	<body>
		<div align="center">
			<h3>Login</h3>
			<form:form action="${pageContext.request.contextPath}/doLogin" method="POST" modelAttribute="loginData">
			<table>
				<tr>
					<td>Username:</td> 
					<td><form:input path="username"/></td> 
					<td><form:errors path="username"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:input type="password" path="password"/></td> 
					<td><form:errors path="password"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><c:out value="${loginStatus }"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Login"></td>
				</tr>
			</table>
			</form:form>
		</div>
	</body>
</html>