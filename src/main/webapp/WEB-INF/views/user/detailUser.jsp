<%@include file="../mainHeader.jsp"%>
<div id="main-wrapper">
	<div class="row">
		<div class="col-lg-12"></div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Detail User</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%">
						<tbody>
							<tr>
								<td width="15%">First Name</td>
								<td width="35%">:&nbsp;<c:out value="${userData.firstName }" /></td>
								<td width="15%">Address</td>
								<td width="35%">:&nbsp;<c:out value="${userData.address }" /></td>
							</tr>
							<tr>
								<td width="15%">Last Name</td>
								<td width="35%">:&nbsp;<c:out value="${userData.lastName }" /></td>
								<td width="15%">Role</td>
								<td width="35%">:&nbsp;<c:out value="${userData.roleId.roleName }" /></td>
							</tr>
							<tr>
								<td width="15%">Username</td>
								<td width="35%">:&nbsp;<c:out value="${userData.username }" /></td>
								<td width="15%">Additional Info</td>
								<td width="35%">:&nbsp;<c:out value="${userData.description }" /></td>
							</tr>
							<tr>
								<td width="15%">Phone Number</td>
								<td width="35%">:&nbsp;<c:out value="${userData.phoneNumber }" /></td>
								<td width="15%">&nbsp;</td>
								<td width="35%">&nbsp;</td>
							</tr>
						</tbody>
					</table>
					<div>
						<input type="button" value="Back" onclick="window.location.replace('${pageContext.request.contextPath}/user')">
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
<%@include file="../mainFooter.jsp"%>