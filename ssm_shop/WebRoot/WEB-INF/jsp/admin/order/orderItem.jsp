<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
	<c:forEach items="${oiList }" var="orderItem">
		<tr>
			<td><img width="40" height="45" src="${ pageContext.request.contextPath }/${orderItem.product.image }"></td>
			<td>${orderItem.product.pname }</td>
			<td>${orderItem.count }</td>
			<td>${orderItem.subtotal }</td>
		</tr>
	</c:forEach>
</table>