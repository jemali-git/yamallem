<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
<body>
	<form action="/home/logout" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<sec:authorize access="isAuthenticated()">
		<a href="javascript:formSubmit()">Déconnexion</a>
		<p> connected</p>
	</sec:authorize>

	<sec:authorize access="isAnonymous()">
		<a href="/home/login">Connexion</a>
	</sec:authorize>
</body>
</html>