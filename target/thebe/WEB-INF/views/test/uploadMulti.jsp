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
		<title>Add/Edit Contact</title>
	</head>
	<body>
		<div align="center">
			<h3>Add/Edit User</h3>
			<form:form action="${pageContext.request.contextPath}/saveUser" method="POST" modelAttribute="userData">
			<table>
				<form:hidden path="userId"/>
				<tr>
					<td>First Name:</td> 
					<td><form:input path="firstName"/></td> 
					<td><form:errors path="firstName"/></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName"/></td> 
				</tr>
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
					<td>Confirm Password:</td>
					<td><form:input type="password" path="confPassword"/></td> 
				</tr>
				<tr>
					<td>Phone Number:</td>
					<td><form:input path="phoneNumber"/></td> 
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address"/></td> 
				</tr>
				<tr>
					<td>Role:</td>
					<td>
						<form:select path="roleId.roleId">
							<c:forEach items="${roleData }" var="role">
								<form:option value="${role.roleId }">${role.roleName }</form:option>
							</c:forEach>
						</form:select>
					</td> 
				</tr>
				<tr>
					<td>Additional Info:</td>
					<td><form:textarea path="description"/></td> 
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Save"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" value="Cancel" onclick="window.location.replace('${pageContext.request.contextPath}/user')"></td>
				</tr>
			</table>
			</form:form>
		</div>
	</body>
</html>
