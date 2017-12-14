function getTempSign(){
	return $.ajax({url:"/user/tempsign",async:false}).responseText;
}

function postLogin(username, postPass){
	return $.ajax({
	  type: 'POST',
	  url: "/user/login",
	  data: { postPass: postPass, username: username },
	  async:false
	}).responseText;
}

$(function(){
	$("#login").click(function(){
		var tempSign = getTempSign();
		var username = $("#username").val();
		var password = $("#password").val();
		var postPass = UTF8_MD5.get(tempSign + username + UTF8_MD5.get(password));
		var result = postLogin(username, postPass);
		alert(result);
    });
	
	//获取指定文件夹内的列表,默认每次返回20条
    $('#testcos').on('click', function () {
    	cos.getFolderList(
    		function successCallBack(result){
    			alert("success"+JSON.stringify(result));
    			
    		}, function errorCallBack(result){
    			alert("error"+JSON.stringify(result));	
    			
    		}, QCLOUD_BUKET , "/");
    });
	
});