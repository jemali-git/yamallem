<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:if test="${param.error ne null}">
		<div style="color: red;">Identifiants invalides.</div>
	</c:if>
	<form action="/home/login" method="post">

		<label>Email</label> 
		<input  name="userEmail"  />
		 
		<label>Mot de passe</label> 
		<input name="userPassword"  />
		
		<button type="submit">Connexion</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

</body>
</html>