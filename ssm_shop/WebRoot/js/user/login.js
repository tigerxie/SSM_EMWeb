$(function() {
	/*
	 * 1. 让登录按钮在得到和失去焦点时切换图片
	 */
	/*$("#submit").hover(
		function() {
			$("#submit").attr("src", "/goods/images/login2.jpg");
		},
		function() {
			$("#submit").attr("src", "/goods/images/login1.jpg");
		}
	);*/
	
	/*
	 * 2. 给注册按钮添加submit()事件，完成表单校验
	 */
	$("#loginForm").submit(function() {
		var bool = true;
		//校验所有输入框
		if (!validateUsername()) {
			bool = false;
		}
		if (!validatePassword()) {
			bool = false;
		}
		if (!validateVerifyCode()) {
			bool = false;
		}
		return bool;
	});
	
	
	/*$("#submit").submit(function(){
//		$("#msg").text("");
		alter("aa");
		var bool = true;
		$(".text").each(function() {
			var inputName = $(this).attr("name");
			if(!invokeValidateFunction(inputName)) {
				bool = false;
				
			}
		});
		return bool;
	});*/
	
	/*
	 * 3. 输入框得到焦点时隐藏错误信息
	 */
	$(".text").focus(function() {
		var inputName = $(this).attr("name");
		$("#" + inputName + "Error").css("display", "none");
	});
	
	/*
	 * 4. 输入框失去焦点时进行校验
	 */
	$(".text").blur(function() {
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	})
});

/*
 * 输入input名称，调用对应的validate方法。
 * 例如input名称为：loginname，那么调用validateLoginname()方法。
 */
function invokeValidateFunction(inputName) {
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	var functionName = "validate" + inputName;
	return eval(functionName + "()");	
}

/*
 * 校验登录名
 */
function validateUsername() {
	var bool = true;
	$("#usernameError").css("display", "none");
	var value = $("#username").val();
	if(!value) {// 非空校验
		$("#usernameError").css("display", "");
		$("#usernameError").text("用户名不能为空!");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#usernameError").css("display", "");
		$("#usernameError").text("长度为3~20位!");
		bool = false;
	}
	return bool;
}

/*
 * 校验密码
 */
function validatePassword() {
	var bool = true;
	$("#passwordError").css("display", "none");
	var value = $("#password").val();
	if(!value) {// 非空校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("密码不能为空!");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("长度为3~20位!");
		bool = false;
	}
	return bool;
}

/*
 * 校验验证码
 */
function validateVerifyCode() {
	var bool = true;
	$("#verifyCodeError").css("display", "none");
	var value = $("#verifyCode").val();
	if(!value) {//非空校验
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("验证码不能为空！");
		bool = false;
	} else if(value.length != 4) {//长度不为4就是错误的
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("错误的验证码！");
		bool = false;
	} else {//异步校验验证码是否正确
		var url = "ajaxValidateVerifyCode.action";
		var args = {"verifyCode": value};
		// ajax 异步请求
		$.post(url, args, function(data){
			//返回类型：true表示用户已被注册，false表示未被注册
			if (data == "false") {
				$("#verifyCodeError").css("display", "");
				$("#verifyCodeError").text("验证码错误！");
				bool = false;
			}
		});
	}
	
	/* else {//验证码是否正确
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {method: "ajaxValidateVerifyCode", verifyCode: value},
			url: "/goods/UserServlet",
			success: function(flag) {
				if(flag) {
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("错误的验证码！");
					bool = false;					
				}
			}
		});
	}*/
	return bool;
}
/*
 * 换一张验证码图片
 * 1. 得到验证码元素
 * 2. 设置src属性
 */
function _hyz() {
	$("#captchaImage").attr("src","/ssm_shop/checkImg.action?time="+ new Date().getTime());
}
