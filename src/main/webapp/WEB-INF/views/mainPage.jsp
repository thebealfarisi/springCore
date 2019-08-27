<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="req"><%=request.getAttribute("javax.servlet.forward.request_uri")%></c:set>
<!-- Variable Declaration -->
<!-- User -->
<%@page import="com.mycore.thebe.entity.User" %>

<%
User user = (User) session.getAttribute("sessionUser");
String mainMenu = user == null ? "" : user.getRoleId().getMainMenu();
%>

<html>
	<body>
		<jsp:include page="<%=mainMenu %>" />
	</body>
</html>