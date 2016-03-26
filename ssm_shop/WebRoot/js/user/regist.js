$(function() {
	/*
	 * 1. 得到所有的错误信息，遍历之。调用一个方法是否显示错误信息。
	 */
	$(".error").each(function() {
		showError($(this));//遍历每个元素，调用方法是否显示错误信息
	});
	/*
	 * 2. 注册按钮的图片切换
	 */
	/*$("#submit").hover(
		function(){
			$("#submit").attr("src","/goods/images/regist1.jpg");
		},
		function() {
			$("#submit").attr("src","/goods/images/regist2.jpg");
		}
	);*/
	/*
	 * 3. 表单校验：自身校验
	 *   输入框得到焦点，隐藏错误信息。
	 */
	$(".text").focus(function() {
		var labelId = $(this).attr("id") + "Error";//通过输入框id找到label的id
		$("#" + labelId).text("");//设置元素文本为空
		showError($("#" + labelId));//调用方法，完成隐藏
	});
	/*
	 * 4. 输入框失去焦点，进行校验
	 */
	$(".text").blur(function() {
		var inputId = $(this).attr("id");//得到输入框id
		//调用输入框的校验方法
		var funName = "validate" + inputId.substring(0,1).toUpperCase() + inputId.substring(1) + "()";
		eval(funName);
	});
	/*
	 * 5. 表单提交校验所有输入框
	 */
	$("#registerForm").submit(function() {
		var bool = true;
		//校验所有输入框
		if (!validateUsername()) {
			bool = false;
		}
		if (!validatePassword()) {
			bool = false;
		}
		if (!validateRePassword()) {
			bool = false;
		}
		if (!validateEmail()) {
			bool = false;
		}
		if (!validateVerifyCode()) {
			bool = false;
		}
		return bool;
	});
});

/*
 * 用户名校验
 */
function validateUsername() {
	var id = "username";
	var value = $("#" + id).val();//得到元素
	/*
	 * 1. 判断是否为空
	 */ 
	if (!value) {// 为空则设置错误信息
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("用户名不能为空！");// 设置文本错误信息
		showError($("#" + labelId));// 调用方法，完成显示
		return false;//返回false表示不能提交表单
	}
	/*
	 * 2. 判断输入字段长度是否在3-20之间
	 */
	
	if (value.length < 3 || value.length > 20) {
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("用户名长度必须在3-20之间！");// 设置文本错误信息
		showError($("#" + labelId));//调用方法完成显示
		return false;
	}
	 // 3. 校验是否已被注册
	var url = "ajaxFindByName.action";
	var args = {"username": value};
	
	$.post(url, args, function(data){
		//返回类型：true表示用户已被注册，false表示未被注册
		if (data == "1") {
			var labelId = id + "Error";//通过输入框的id得到label的id
			$("#" + labelId).text("该用户已被注册！");//设置错误信息
			showError($("#" + labelId));//调用方法完成错误信息显示
			return false;
		}
	});
	
	return true;
}

/*
 * 密码校验
 */
function validatePassword() {
	var id = "password";
	var value = $("#" + id).val();//得到元素
	/*
	 * 1. 判断是否为空
	 */ 
	if (!value) {// 为空则设置错误信息
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("密码不能为空！");// 设置文本错误信息
		showError($("#" + labelId));// 调用方法，完成显示
		return false;//返回false表示不能提交表单
	}
	/*
	 * 2. 判断输入字段长度是否在3-20之间
	 */
	
	if (value.length < 3 || value.length > 20) {
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("密码长度必须在3-20之间！");// 设置文本错误信息
		showError($("#" + labelId));//调用方法完成显示
		return false;
	}
	return true;
}
/*
 * 确认密码校验
 */
function validateRePassword() {
	var id = "rePassword";
	var value = $("#" + id).val();//得到元素
	/*
	 * 1. 判断是否为空
	 */ 
	if (!value) {// 为空则设置错误信息
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("确认密码不能为空！");// 设置文本错误信息
		showError($("#" + labelId));// 调用方法，完成显示
		return false;//返回false表示不能提交表单
	}
	/*
	 * 2. 判断两次密码输入是否一致
	 */
	
	if ($("#password").val() != value) {
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("两次密码输入不一致！");// 设置文本错误信息
		showError($("#" + labelId));//调用方法完成显示
		return false;
	}
	return true;
}
/*
 * Email校验
 */
function validateEmail() {
	var id = "email";
	var value = $("#" + id).val();//得到元素
	/*
	 * 1. 判断是否为空
	 */ 
	if (!value) {// 为空则设置错误信息
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("Email不能为空！");// 设置文本错误信息
		showError($("#" + labelId));// 调用方法，完成显示
		return false;//返回false表示不能提交表单
	}
	/*
	 * 2. 判断Email格式
	 */
	if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)) {//格式校验
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("Email格式不对！");// 设置文本错误信息
		showError($("#" + labelId));//调用方法完成显示
		return false;
	}
	/*
	 * 3. 校验是否已被注册
	 */
	var url = "ajaxFindByEmail.action";
	var args = {"email": value};
	// ajax 异步请求
	$.post(url, args, function(data){
		//返回类型：true表示用户已被注册，false表示未被注册
		if (data == "1") {
			var labelId = id + "Error";//通过输入框的id得到label的id
			$("#" + labelId).text("该邮箱已被注册！");//设置错误信息
			showError($("#" + labelId));//调用方法完成错误信息显示
			return false;
		}
	});
	return true;
}
/*
 * 图像验证码校验
 */
function validateVerifyCode() {
	var id = "verifyCode";
	var value = $("#" + id).val();//得到元素
	/*
	 * 1. 判断是否为空
	 */ 
	if (!value) {// 为空则设置错误信息
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("验证码不能为空！");// 设置文本错误信息
		showError($("#" + labelId));// 调用方法，完成显示
		return false;//返回false表示不能提交表单
	}
	/*
	 * 2. 判断输入字段长度是否为4
	 */
	
	if (value.length != 4) {
		var labelId = id + "Error";// 通过输入框id找到label的id
		$("#" + labelId).text("验证码长度必须为4！");// 设置文本错误信息
		showError($("#" + labelId));//调用方法完成显示
		return false;
	}
	/*
	 * 3. 异步校验是否输入正确
	 */
	var url = "ajaxValidateVerifyCode.action";
	var args = {"verifyCode": value};
	// ajax 异步请求
	$.post(url, args, function(data){
		//返回类型：true表示用户已被注册，false表示未被注册
		if (data == "false") {
			var labelId = id + "Error";//通过输入框的id得到label的id
			$("#" + labelId).text("验证码错误！");//设置错误信息
			showError($("#" + labelId));//调用方法完成错误信息显示
			return false;
		}
	});
	
	/*$.ajax({
		url: "/goods/UserServlet",
		async: false,
		ache: false,
		type: "POST",
		data: {method:"ajaxValidateVerifyCode", verifyCode: value},
		dataType: "json",
		success: function(bool) {
			if (bool) {
				var labelId = id + "Error";
				$("#" + labelId).text("验证码错误！");
				showError($("#" + labelId));
			}
		}
	});*/
	return true;
}

/*
 * 判断当前元素是否有内容，有则显示，无责隐藏
 */
function showError(ele) {
	var text = ele.text();
	if (!text) {
		ele.css("display", "none");//如果元素文本无内容,则不显示错误信息
	} else {
		ele.css("display", "");//如果元素文本有内容,则显示错误信息
	}
}
/*
 * 换一张验证码图片
 * 1. 得到验证码元素
 * 2. 设置src属性
 */
function _hyz() {
	$("#captchaImage").attr("src","/ssm_shop/checkImg.action?time="+ new Date().getTime());
}

