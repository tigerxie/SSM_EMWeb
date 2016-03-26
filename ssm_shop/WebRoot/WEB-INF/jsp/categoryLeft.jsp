<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span6">
	<div class="hotProductCategory">
		<c:forEach items="${sessionScope.categoryList }" var="c">
			<dl>
				<dt>
					<a href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${c.cid }&page=1">${c.cname }</a>
				</dt>
					<c:forEach items="${c.categorysecondList }" var="cs">
						<dd>
							<a href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${cs.csid }&page=1">${cs.csname }</a>
						</dd>
					</c:forEach>
			</dl>
		</c:forEach>
	</div>
</div>
