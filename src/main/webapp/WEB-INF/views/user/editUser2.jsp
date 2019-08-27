<%@include file="../mainHeader.jsp" %>
<div id="main-wrapper">
	<div class="row">
		<div class="col-lg-12">
			
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Add/Edit User</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form:form action="${pageContext.request.contextPath}/saveUser" method="POST" modelAttribute="userData" enctype="multipart/form-data">
						<div class="col-lg-6">
							<div class="form-group">
								<form:hidden path="userId"/>
								
                                <label>First Name:</label> 
                                <form:errors class="text-danger" path="firstName"/>
                                <form:input class="form-control" path="firstName" />
                                
                                <label>Last Name:</label>
                                <form:input class="form-control" path="lastName" />
                                
                                <label>Username:</label>
                                <form:errors class="text-danger" path="username"/>
                                <form:input class="form-control" path="username" />
                                
                                <label>Password:</label>
                                <form:errors class="text-danger" path="password"/>
                                <form:input class="form-control" path="password" />
                                
                                <label>Confirm Password:</label>
                                <form:input class="form-control" path="confPassword" />
                                
                                <label>Phone Number:</label>
                                <form:input class="form-control" path="phoneNumber" />
                                
                                <label>Address:</label>
                                <form:input class="form-control" path="address" />
                                
                                <label>Role:</label>
                                <form:input class="form-control" id="autoCompleteRole" path="roleId.roleId" value="${roleId.roleName }" />
                                
                                <label>Upload File:</label>
                                <input type="file" name="file"/>
                                
                                <label>Additional Info:</label>
                                <form:textarea class="form-control" rows="3" path="description"/>
                            </div>
						</div>
						<div class="col-lg-12">
							<input type="submit" value="Save">
							&nbsp;
							<input type="button" value="Cancel" onclick="window.location.replace('${pageContext.request.contextPath}/user')">
						</div>
					</form:form>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
<%@include file="../mainFooter.jsp" %>


<script language="Javascript">

$(document).ready(function() {
	$('#autoCompleteRole').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/autoCompleteRoleX',
		paramName: "role",
		delimiter: ",",
		transformResult: function(response) {
			return {
				suggestions: $.map($.parseJSON(response), function(item){
					return {
						value: item.roleName, 
						data: item.roleId
					};
				})
			};
		}
	});
});

</script>