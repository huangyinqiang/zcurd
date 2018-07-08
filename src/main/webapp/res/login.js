function randImage() {
	$("#image").attr("src", basePath + "/login/image?" + Math.random());
}
$(function() {
	// 获取焦点样式
	$("#ff :input").focus(function() {
		$(this).parent().addClass("actived");
	}).blur(function() {
		$(this).parent().removeClass("actived");
	});
});

// 回车登陆
document.onkeydown = function() {
	if (event.keyCode == 13) {
		login();
		return false;
	}
}
function login() {
	var param = {
		"code" : $(":input[name='code']").val(),
		"user_name" : $(":input[name='user_name']").val(),
		"password" : $(":input[name='password']").val()
	};
	if (param['user_name'] == "") {
		alert("用户名不能为空");
		return;
	}
	if (param['password'] == "") {
		alert("密码不能为空");
		return;
	}
	if (param['code'] == "") {
		alert("验证码不能为空");
		return;
	}
	$.post(basePath + "/login/login", param, function(data) {
		if (data.result == "success") {
			$('#ff').submit();
			location.href = "main";
		} else {
			$("#password").val("");
			$("#code").val("");
			randImage();
			alert(data.msg);
		}
	});
}