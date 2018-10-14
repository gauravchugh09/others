<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>display varables 2</title>
</head>
<body>
${msg}

username=${student1.userName}
password=${student1.passWord}
mobile number=${student1.mobNo}
date of birth=${student1.dob}
country=${student1.address.country}
city=${student1.address.city}
pincode=${student1.address.pinCode}
</body>
</html>