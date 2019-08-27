<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="req"><%=request.getAttribute("javax.servlet.forward.request_uri")%></c:set>
<!-- Variable Declaration -->
<%@page import="com.mycore.thebe.entity.User" %>

<%
User user = (User) session.getAttribute("sessionUser");
String mainMenu = user == null ? "" : user.getRoleId().getMainMenu();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Company Name</title>
	
	<!-- INCLUDE STYLER -->
	<%@include file="component/resources.jsp" %>

</head>

<body>
    <div id="wrapper">
    	<!-- --------------HEADER-------------- -->
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                	Logo and Company Name
                </a>
            </div>
           	<!-- Top Menu-->
           	<div>
           		<jsp:include page="<%=mainMenu %>" />
           	</div>
           	<!-- End Top Menu -->
        </nav>
        <!-- End Navigation -->
		<!-- --------------END HEADER-------------- -->
	
		<!-- Main Body -->
        <!-- NANTI DIPISAH -->
        <!-- /#page-wrapper -->
    	<!-- bawah disambung ke main page -->
