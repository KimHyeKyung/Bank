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
<h3>[ 전체계좌 조회 ]</h3>
<table border="1">
<tr><th>계좌번호</th><th>이름</th><th>잔액</th><th>계좌구분</th><th>등급</th></tr>
<c:forEach var="acc" items="${accs}">
<tr>
	<td>${acc.id }</td>
	<td>${acc.name }</td>
	<td>${acc.balance }</td>
	<td>${acc.sect }</td>
	<c:choose>
		<c:when test="${acc.sect eq 'special' }">
			<td>${acc.grade }</td>
		</c:when>
		<c:otherwise>
			<td></td>
		</c:otherwise>
	</c:choose>
</tr>
</c:forEach>

</table>
</body>
</html>