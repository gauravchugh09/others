<%-- register.jsp  --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
</head>
<body>
<form:form method="POST" action="addUser" modelAttribute="userObj" > <spring:message code="register.userid" text="default text"/>
<form:input path="userId" class="form-control" /> <form:errors path="userId" /> <spring:message code="register.pwd" text="default text"/> 
<form:password path="password" class="form-control" /> <form:errors path="password" /> ......... </form:form> 
<button type="submit">submit</button>
</body> 
</html>