<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<body>
<head>
<title>Servlet task</title>
<link rel="stylesheet" href="CSS/style.css">
</head>

<form action="another-servlet.html" method="post">
	<fieldset>
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Surname:</td>
				<td><input type="text" name="surname"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="mail"></td>
			</tr>
		</table>
		<input type="submit" name="addEmp" value="add employee">



	</fieldset>
</form>

<div class="errMsg">
	<c:out value="${errMsg}" />

	<div class="empTable">
		<c:choose>
			<c:when test="${empty applicationScope.employees}">
				<!-- Inform user that there's no employee yet -->
				<c:out value="Employee list is empty"></c:out>
			</c:when>


			<c:otherwise>
				<table>

					<td><c:out value="idEmployee"></c:out></td>
					<td><c:out value="Name"></c:out></td>
					<td><c:out value="Surname"></c:out></td>
					<td><c:out value="E-mail"></c:out></td>

					<c:forEach var="employee" items="${applicationScope.employees}">
						<tr>
							<td><c:out value="${employee.idEmployee}"></c:out></td>
							<td><c:out value="${employee.name}"></c:out></td>
							<td><c:out value="${employee.surname}"></c:out></td>
							<td><c:out value="${employee.email}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>




		<!--<h2>Hello World!</h2>
<c:out value="Hello again"></c:out>-->

	</div>
</div>
</body>
</html>
