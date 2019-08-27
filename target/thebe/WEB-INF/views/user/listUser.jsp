<!-- Paging -->
<% 
int i = 0;
int indexData = ((Integer) request.getAttribute("index")).intValue();
int maxData = ((Integer) request.getAttribute("maxValue")).intValue();
%>

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
				<div class="panel-heading">List Users</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form name="navForm" action="${ref }" method="POST">
						<div>
							<input type="hidden" name="index" value="<c:out value="${index }" />" />
							<input type="hidden" name="nav" />
							<table width="100%">
								<tbody>
									<tr>
										<td colspan="2">
											Search Keyword:&nbsp;
											<input type="text" name="searchVal" value="<c:out value="${searchVal }" />" />
										</td>
										<td colspan="2">
											Search Category:&nbsp;
											<input type="text" name="searchBy" value="<c:out value="${searchBy }" />" />
										</td>
										<td>
											<input type="submit" value="Search" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<span class="pull-right text-muted small">
								<c:choose>
									<c:when test="${index == 0}">
										<i class="fa fa-angle-double-left"></i>
										Start
									</c:when>
									<c:otherwise>
										<a href="javascript:goStart()">
											<i class="fa fa-angle-double-left"></i>
											Start
										</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${index == 0}">
										<i class="fa fa-angle-left"></i>
										Prev
									</c:when>
									<c:otherwise>
										<a href="javascript:goPrev()">
											<i class="fa fa-angle-left"></i>
											Prev
										</a>
									</c:otherwise>
								</c:choose>
								&nbsp;|&nbsp;${(maxValue * index) + 1 } - 
								<c:choose>
									<c:when test="${((index + 1) * maxValue) < totalData}">${(index + 1) * maxValue }</c:when>
									<c:otherwise>${totalData }</c:otherwise>
								</c:choose>
								of ${totalData }&nbsp;|&nbsp;
								<c:choose>
									<c:when test="${maxIndex == index}">
										Next
										<i class="fa fa-angle-right"></i>
									</c:when>
									<c:otherwise><a href="javascript:goNext()">
										Next
										<i class="fa fa-angle-right"></i>
									</a></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${maxIndex == index}">
										End
										<i class="fa fa-angle-double-right"></i>
									</c:when>
									<c:otherwise><a href="javascript:goEnd()">
										End
										<i class="fa fa-angle-double-right"></i>
									</a></c:otherwise>
								</c:choose>
							</span>
						</div>
						<table width="100%"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>No.</th>
									<th>Name</th>
									<th>Username</th>
									<th>Address</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList }" var="user" varStatus="status">
									<% i++; %>
									<tr class="odd gradeX">
										<td><%=(i + ((indexData) * maxData)) %></td>
										<td>${user.firstName } ${user.lastName }</td>
										<td>${user.username }</td>
										<td>${user.address }</td>
										<td align="center">
											<a href="${pageContext.request.contextPath}/detailUser?userId=${user.userId}">
												<i class="fa fa-search"></i>
											</a>
											&nbsp;
											<a href="${pageContext.request.contextPath}/editUser?userId=${user.userId}">
												<i class="fa fa-edit"></i>
											</a>
										</td>
									</tr>
								</c:forEach>		
							</tbody>
						</table>
					</form>
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
function goNext() {
	document.navForm.searchVal.value="<c:out value="${searchVal }" />";
	document.navForm.searchBy.value="<c:out value="${searchBy }" />";
	document.navForm.nav.value="next";
	document.navForm.index.value="<c:out value="${index }" />";
	document.navForm.method="POST";
// 	document.navForm.action="<c:out value="${req }" />";
	document.navForm.submit();
}

function goPrev() {
	document.navForm.searchVal.value="<c:out value="${searchVal }" />";
	document.navForm.searchBy.value="<c:out value="${searchBy }" />";
	document.navForm.nav.value="prev";
	document.navForm.index.value="<c:out value="${index }" />";
	document.navForm.method="POST";
// 	document.navForm.action="<c:out value="${req }" />";
	document.navForm.submit();
}

function goStart() {
	document.navForm.searchVal.value="<c:out value="${searchVal }" />";
	document.navForm.searchBy.value="<c:out value="${searchBy }" />";
	document.navForm.nav.value="start";
	document.navForm.index.value="<c:out value="${index }" />";
	document.navForm.method="POST";
// 	document.navForm.action="<c:out value="${req }" />";
	document.navForm.submit();
}

function goEnd() {
	document.navForm.searchVal.value="<c:out value="${searchVal }" />";
	document.navForm.searchBy.value="<c:out value="${searchBy }" />";
	document.navForm.nav.value="end";
	document.navForm.index.value="<c:out value="${index }" />";
	document.navForm.method="POST";
// 	document.navForm.action="<c:out value="${req }" />";
	document.navForm.submit();
}

</script>