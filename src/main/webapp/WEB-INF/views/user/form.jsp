<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><html><head>    <title>Register Form</title></head><body><form:form modelAttribute="registration" method="post" action="/user/form" enctype="utf8">    <form:label path="email">Adres email</form:label>    <form:input path="email"/><br>    <form:errors path="email" cssStyle="color: red"/><br>    <form:label path="login">Login</form:label>    <form:input path="login"/><br>    <form:errors path="login" cssStyle="color: red"/><br>    <form:label path="pass1">Hasło</form:label>    <form:password path="pass1"/><br><br>    <form:label path="pass2">Powtórz hasło</form:label>    <form:password path="pass2"/><br>    <form:errors path="pass2" cssStyle="color: red"/><br>    <button type="submit">ZAREJESTRUJ</button>    <button><a href="${pageContext.request.contextPath}/">Powrót</a></button></form:form></body></html>