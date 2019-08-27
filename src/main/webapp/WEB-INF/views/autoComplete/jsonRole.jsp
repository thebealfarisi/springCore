<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


[
<c:forEach items="${roleList }" var="role" varStatus="status">
 {
 "id": "<c:out value="${role.roleId }" />",
 "name": "<c:out value="${role.roleName }"/>"
 }
 <c:if test="${status.count lt fn:length(roleList) }">
 	<c:out value=","/>
 </c:if>
</c:forEach>
]
