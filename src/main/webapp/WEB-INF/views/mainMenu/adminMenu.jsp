<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<strong> 
				User 
			</strong>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu dropdown-user">
			<li>
				<a href="${pageContext.request.contextPath}/user">
					Search User
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="${pageContext.request.contextPath}/editUser">
					Add New User
				</a>
			</li>
		</ul>
	</li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			<strong> 
				Task
			</strong>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu dropdown-user">
			<li>
				<a href="#">
					TASK 01
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="#">
					TASK 02
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="#">
					TASK 03
				</a>
			</li>
		</ul> 
	</li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			<strong>
				Notification 
			</strong>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu dropdown-user">
			<li>
				<a href="#">
					Notification 01
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="#">
					Notification 02
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="#">
					Notification 03
				</a>
			</li>
		</ul> 
	</li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			<i class="fa fa-user fa-fw"></i>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu dropdown-user">
			<li>
				<a href="#">
					<i class="fa fa-user fa-fw"></i> 
					${sessionUser.firstName } ${sessionUser.lastName }
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="${pageContext.request.contextPath}/logout">
					<i class="fa fa-sign-out fa-fw"></i>
					Logout
				</a>
			</li>
		</ul> 
	</li>
</ul>