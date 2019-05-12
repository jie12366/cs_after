/*函数功能：验证登录表单*/
function check_login()
{
 var name=$("#user_name").val();
 var psw=$("#password").val();
 var r_name = $("#r_email").val();
 var r_psw = $("#r_password").val();
 if(name==r_name && psw==r_psw)
 {
  alert("登录成功！");
  $("#user_name").val("");
  $("#password").val("");

 }
 /*若信息不匹配，则界面抖动*/
 else
 {
  $("#login_form").removeClass('shake_effect');  
  setTimeout(function()
  {
   $("#login_form").addClass('shake_effect')
  },1);  
 }
}

/*函数功能：验证注册表单*/
function check_register(){
	var ide = $("#ide").text();
	console.log("|"+ide+"|");
	var email = $("#r_email").val();
	var phone = $("#r_phone").val();
	var psw = $("#r_password").val();
	if(email != "" && phone!="" && psw!="" )
	 {
	  alert("注册成功！");
	  change_animate("r-to-l");
	  $("#user_name").val(email);
	  $("#password").val(psw);
	 }
	 /*若信息不匹配，则界面抖动*/
	 else
	 {
	  $("#login_form").removeClass('shake_effect');  
	  setTimeout(function()
	  {
	   $("#login_form").addClass('shake_effect')
	  },1);  
	 }
}

/*函数功能：验证手机验证表单*/
function check_p_login(){
	/*获取表单信息*/
	var check=$("#p_check").val();
	/*信息验证*/
	if(is_checked==check)
	{
		/*将按键复原以及停止计时器*/
		showtimer("pb_check");
		clearInterval(refresh);

		alert("登录成功！");
		$(".p_login-form input").val("");//将input标签内容清空
	}
	/*若信息不匹配，则界面抖动*/
	else
	{
	  $("#login_form").removeClass('shake_effect');  
	  setTimeout(function()
	  {
	   $("#login_form").addClass('shake_effect')
	  },1);  
	}
}
/*不同表单切换动画*/
function change_animate(classname){
	$("."+classname+" form").animate({
		height: 'toggle',
		opacity: 'toggle'
	}, 'slow');
}

/*函数功能：获取验证码*/
/*定义两个全局变量用来存储随机数*/
// var p_rand;
// var R_rand;
// function createrand(id){
// 	/*若为注册界面*/
// 	if(id=="rb_check"){
// 		var name=$("#r_email").val();
// 		if(name!=""){
// 			r_rand=Math.floor(Math.random()*10000)+1000;
// 			alert("您的验证码为"+r_rand);
// 		}
// 	}
// 	/*若为手机注册界面*/
// 	else{
// 		var name=$("#p_user_name").val();
// 		if(name!=""){
// 			p_rand=Math.floor(Math.random()*10000)+1000;
// 			alert("您的验证码为"+p_rand);
// 		}
// 	}
// }

 /*校验手机号是否合法*/
function isPhoneNum(phonenum){
    var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(!reg.test(phonenum)){
        alert('请输入有效的手机号码！');
        return false;
    }else{
        return true;
    }
}

 //用ajax提交到后台的发送短信接口
function sendcheck(check_id,phone_id){
    var phone = $("#"+phone_id).val();
    // var result = isPhoneNum(phone);
    result=true;
    if(result) {
        $.ajax({
            url:"http://jie12366.xyz:85/sendMsg",
            data:{phone:phone},
            dataType:"json",
            type:"post",
            cache : false,
           	success:function(res){
           		alert(res.data)
            },
            error:function(xhr) { 
            	console.log(xhr.status);
            	$(function (){$.messager.show({title : "提示",msg : "失败，请联系管理员",timeout : 3000});});
         	}
        });
       
        timer(intDiff,check_id);//开始倒计时
    }
}

/*函数功能：隐藏倒计时并让按键无效*/
function hidetimer(id){
	$("#"+id).text("("+second+"s)后重新发送");
	$("#"+id).attr({
		"disabled":true
	})
	$("#"+id).css({
		"background-color":"#00C1FFFF",
		"font-size":"13px"
	})
}

/*函数功能：显示倒计时且让按键重新有效*/
function showtimer(id){
	$("#"+id).text("获取验证码");
	$("#"+id).attr({
		"disabled":false
	})
	$("#"+id).css({
		"background-color":"#0084FFFF",
		"font-size":"14px"
	})
}

/*函数功能：构建并显示验证码倒计时*/
var refresh;//定义定时器
var intDiff = parseInt(60);//倒计时总秒数量
var second=0;//倒计时数
function timer(intDiff,id){
	if(id=="rb_check"){
		var name=$("#r_email").val();
	}
	else{
		var name=$("#p_user_name").val();
	}
	if(name!=""){
	    refresh = window.setInterval(function(){
	    second=0;//时间默认值        
	    if(intDiff > 0){
	        second=intDiff;
	    }
	    if (second <= 9) second = '0' + second;
	    /*重新计时的时候，禁用按键功能*/
	    if(second>0){
	    	hidetimer(id);
		}
		/*倒计时一过，恢复按键功能并停止计时*/
		else{
			showtimer(id);
			clearInterval(refresh);
		}
	    intDiff--;
	    }, 1000);
	}
} 

/*jquery入口*/
$(function(){
	$("#create").click(function(){
		check_register();
		return false;
	})
	$("#login").click(function(){
		check_login();
		return false;
	})
	$("#p_login").click(function(){
		check_p_login();
		return false;
	})
	$('.message a').click(function () {
		var id = $(this).parent().siblings(".check").attr('id');
		change_animate("r-to-l");
		$("input").val("");
		showtimer(id);
		clearInterval(refresh);
	});
	/*登录方式转换*/
	$('.typeoflogin a').click(function () {
		var id = $(this).parent().siblings(".check").attr('id');
		change_animate("l-to-p");
		$("input").val("");
		showtimer(id);
		clearInterval(refresh);
	});
	$('.p_login-form .message a').click(function () {
		$('.l-to-p form').hide();
	});
	/*身份选择框处理*/
	$('.select').on('click', '.placeholder', function(e) {
		var parent = $(this).closest('.select');
		parent.css({
			"box-shadow":"0 0 20px 0 rgba(0, 111, 255, 0.2), 0 5px 5px 0 rgba(0, 111, 255, 0.24)"
		});
		if (!parent.hasClass('is-open')) {
			parent.addClass('is-open');
			$('.select.is-open').not(parent).removeClass('is-open');
		} else {
			parent.removeClass('is-open');
		}
		e.stopPropagation();
	}).on('click', 'ul>li', function() {
		var parent = $(this).closest('.select');
		parent.removeClass('is-open').find('.placeholder').text($(this).text());
	});


	$('body').click(function() {
		$('.select').css({
			"box-shadow":"none"
		});
		$('.select.is-open').removeClass('is-open');
	});

	$(".check").click(function(){
		/*获取两个class为check的button的id值（用来区分按钮）*/
		var check_id=$(this).attr('id');
		var phone_id=$(this).siblings(".phone").attr("id");

		/*模拟获取验证码*/
		// createrand(check_id);

		/*获取验证码*/
		sendcheck(check_id,phone_id);
	})
})

