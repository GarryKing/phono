<%@ page contentType="text/html; charset=UTF-8" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "
http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=gbk">
</head>

<body>
<c:forEach var="item" items="${ipMap}">
    ${item.key} > ${item.value.ip} <br/>
</c:forEach>
</body>
</html>