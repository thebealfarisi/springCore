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
		<title></title>
	</head>
	<body>
		<div align="center">
			<h3>Activity Log List</h3>
<%-- 			<h1><a href="${pageContext.request.contextPath}/editUser">Add User</a></h1> --%>
			<a href="${pageContext.request.contextPath}/logout">Log Out</a>
			<table>
				<tr>
					<td>No.</td>
					<td>Action Process</td>
					<td>Action Url</td>
					<td>Action Time</td>
					<td>Action User</td>
					<td>Ip Address</td>
<!-- 					<td>edit</td> -->
				</tr>
				<c:forEach items="${activityLogList }" var="activityLog" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${activityLog.actionProcess }</td>
						<td>${activityLog.actionUrl }</td>
						<td>${activityLog.actionTime }</td>
						<td>${activityLog.actionUser }</td>
						<td>${activityLog.ipAddress }</td>
<%-- 						<td><a href="${pageContext.request.contextPath}/editUser?userId=${user.userId}">edit</a></td> --%>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>