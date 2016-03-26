<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo3.gif" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/loginPage.action">登录</a>|
						</li>
						<li id="headerRegister" class="headerRegister" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/registPage.action">注册</a>|
						</li>
					</c:when>
					<c:otherwise>
						<li id="headerUsername" class="headerUsername" style="display: list-item;">
							${sessionScope.user.username }|
						</li>
						<li id="headerRegister" class="headerRegister" style="display: list-item;">
							<a href="${pageContext.request.contextPath }/order/myOrder.action?page=1">我的订单</a>|
						</li>
						<li id="headerLogout" class="headerLogout" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/quit.action">退出</a>|
						</li>
					</c:otherwise>
				</c:choose>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${ pageContext.request.contextPath }/cart/myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath }/index.action">首页</a>
						|
					</li>
					<c:forEach items="${sessionScope.categoryList }" var="category">
						<li>
							<a href="${ pageContext.request.contextPath }/product/findByCidPage.action?cid=${category.cid }&page=1">
							${category.cname }</a>
							|
						</li>
					</c:forEach>
		</ul>
	</div>


</div>