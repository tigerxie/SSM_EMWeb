<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%@include file="menu.jsp" %>

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单显示</li>
				</ul>
			</div>
				<c:forEach items="${pageBean.beanList }" var="order">
					<tr>
						<th colspan="5">订单编号:${order.oid }&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
							color="red">${order.total }
						</font>
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
							<c:if test="${order.state == 1 }">
								<a href="${ pageContext.request.contextPath }/order/findByOid.action?oid=${order.oid }">付款</a>
							</c:if>
							<c:if test="${order.state == 2 }">
								已付款
							</c:if>
							<c:if test="${order.state == 3 }">
								<a href="${ pageContext.request.contextPath }/order/updateState.action?oid=${order.oid }">确认收货</a>
							</c:if>
							<c:if test="${order.state == 4 }">
								交易成功
							</c:if>
						</font>
						</th>
					</tr>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<c:forEach items="${order.orderitemCustoms }" var="orderItem">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath }/${orderItem.product.image }"/>
							</td>
							<td>
								<a target="_blank">${orderItem.product.pname }</a>
							</td>
							<td>
								${orderItem.product.shopPrice }
							</td>
							<td class="quantity" width="60">
								<input type="text" name="count" value="${orderItem.count }" maxlength="4" onpaste="return false;"/>
								<div>
									<span class="increase">&nbsp;</span>
									<span class="decrease">&nbsp;</span>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥${orderItem.subtotal }</span>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:forEach>
		</div>
		<div class="pagination">
			<c:if test="${pageBean.page > 1}">
				<a href="${pageContext.request.contextPath }/order/myOrder.action?page=1" class="firstPage">&nbsp;</a>
				<a href="${pageContext.request.contextPath }/order/myOrder.action?page=${pageBean.page - 1 }" class="previousPage">&nbsp;</a>
			</c:if>
			
			<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
				<c:choose>
					<c:when test="${pageBean.page == i }">
						<a href="${pageContext.request.contextPath }/order/myOrder.action?page=${i }" class="currentPage">${i }</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/order/myOrder.action?page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${pageBean.page < pageBean.totalPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/order/myOrder.action?page=${pageBean.page + 1 }">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath }/order/myOrder.action?page=${pageBean.totalPage }">&nbsp;</a>
			</c:if>
			
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
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
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>
