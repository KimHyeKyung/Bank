<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[ 계좌 조회 ]</h3>
<table border="1">
	<tr><td>계좌번호</td><td>${acc.id}</td></tr>
	<tr><td>이름</td><td>${acc.name}</td></tr>
	<tr><td>잔액</td><td>${acc.balance } </td></tr>
	
	<c:choose>
		<c:when test="${acc.sect eq 'special'}">
			<tr><td>계좌구분</td><td>특수계좌</td></tr>
			<tr><td>등급</td><td>${acc.grade}	</td></tr>
		</c:when>
		<c:otherwise>
			<tr><td>계좌구분</td><td>일반계좌</td></tr>		
		</c:otherwise>		
	</c:choose>
</table>
</body>
</html>