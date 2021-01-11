$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");
	//
	// //发送AJAX请求之前，将CSRF令牌设置到请求的消息头中
	//如要使用这样的避免攻击就要在每个异步请求里都要写上这几行代码
	//否则服务器就会认为被攻击了，就会阻止访问
	// var token = $("meta[name='_csrf']").attr("content");
	// var header = $("meta[name='_csrf_header']").attr("content");
	// $(document).ajaxSend(function (e,xhr,options){
	// 	xhr.setRequestHeader(header,token);
	// });

	//获取标题和内容
	var title=$("#recipient-name").val();
	var content=$("#message-text").val();
	//发送异步的请求
	$.post(
		CONTEXT_PATH+"/discuss/add",
		{"title":title,"content":content},
		//回调函数，就是返回的时候
		function (data){
			data=$.parseJSON(data);
			//在提示框中显示返回消息
			$("#hintBody").text(data.msg);
			//显示提示框
			$("#hintModal").modal("show");
			//2秒后自动隐藏提示框
			setTimeout(function(){
				$("#hintModal").modal("hide");
				//刷新页面
				if(data.code==0){
					window.location.reload();
				}

			}, 2000);

		}
	);
	}