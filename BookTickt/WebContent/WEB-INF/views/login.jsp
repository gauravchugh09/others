<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form:errors path="student1.*"/>
<form method="post" action="setModelAttr">
<label>username</label><input type="text" name="userName">
<br>
<label>password</label><input type="password" name="passWord">
<br>
<label>mobile Number</label><input type="text" name="mobNo">
<br>
<label>date of birth</label><input type="text" name="dob">
<br>
<label>country</label><input type="text" name="address.country">
<br>
<label>city</label><input type="text" name="address.city">
<br>
<label>pincode</label><input type="text" name="address.pinCode">
<br>
<button type="submit">submit</button>
</form>
</body>
</html>