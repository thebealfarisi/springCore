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
		<title>Upload Single File</title>
	</head>
	<body>
		<div align="center">
			<h3>Upload Single File</h3>
			<form:form action="${pageContext.request.contextPath}/uploadSingle" method="POST" enctype="multipart/form-data">
			File to upload: <input type="file" name="file"><br>
			Name: <input type="text" name="name"><br>
			<input type="submit" value="UPLOAD">
			</form:form>
		</div>
	</body>
</html>
