<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<jsp:include page="../main/menu.jsp" />

<div class="w3-content" style="max-width:2000px;margin-top:46px">
</div>
<div align="center">
	<div><h1>도서 목록</h1></div>
	<form id = "frm" name = "frm" action="" method = "post">
		<table border = "1">
			<tr>
				<th width="100">도서 코드</th>
				<th width="100">도서명</th>
				<th width="100">기본 수량</th>
				<th width="100">현재 수량</th>
			</tr>
			<c:forEach var = "vo" items="${list }">
			<tr>
				<td align="center">${vo.bCode }</td>
				<td align="center">${vo.bName }</td>
				<td align="center">${vo.quanti }</td>
				<td align="center">${vo.bCount }</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	<div>
		<button type="button"></button>
	</div>
</div>

</body>
</html>