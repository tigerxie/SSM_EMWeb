<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传智网上商城</title>
<link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%@include file="menu.jsp" %>
	
<div class="container productList">
	<%@include file="categoryLeft.jsp" %>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath }/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
							<c:forEach items="${pageBean.beanList }" var="p">
									<li>
										<a href="${pageContext.request.contextPath }/product/findByPid.action?pid=${p.pid }">
										<img src="${pageContext.request.contextPath }/${p.image }" width="170" height="170"  style="display: inline-block;">
										   
										<span style='color:green'>
										 ${p.pname }
										</span>
										 
										<span class="price">
											商城价： ￥${p.shopPrice }/份
										</span>
										</a>
									</li>
							</c:forEach>	
						</ul>
				</div>
	<div class="pagination">
		<!-- 判断分页类型 -->
		<c:if test="${!empty cid }">
			<c:if test="${pageBean.page > 1}">
				<a href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=1" class="firstPage">&nbsp;</a>
				<a href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=${pageBean.page - 1 }" class="previousPage">&nbsp;</a>
			</c:if>
			
			<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
				<c:choose>
					<c:when test="${pageBean.page == i }">
						<a href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=${i }" class="currentPage">${i }</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${pageBean.page < pageBean.totalPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=${pageBean.page + 1 }">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath }/product/findByCidPage.action?cid=${cid }&page=${pageBean.totalPage }">&nbsp;</a>
			</c:if>
		</c:if>
		
		<!-- 判断类型是否为二级分类 -->
		<c:if test="${!empty csid }">
			<c:if test="${pageBean.page > 1}">
				<a href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=1" class="firstPage">&nbsp;</a>
				<a href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=${pageBean.page - 1 }" class="previousPage">&nbsp;</a>
			</c:if>
			
			<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
				<c:choose>
					<c:when test="${pageBean.page == i }">
						<a href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=${i }" class="currentPage">${i }</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${pageBean.page < pageBean.totalPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=${pageBean.page + 1 }">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath }/product/findByCsidPage.action?csid=${csid }&page=${pageBean.totalPage }">&nbsp;</a>
			</c:if>
		</c:if>
	</div>
	
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath }/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body></html>