
function getMD5HexStr(originStr){
	
}

function getTempSign(){
	return $.ajax({url:"/user/tempsign",async:false}).responseText;
}

function login(username, postPass){
	alert("!!");
	return $.ajax({
	  type: 'POST',
	  url: "/user/login",
	  data: { postPass: postPass, username: username },
	  async:false
	}).responseText;
}

$(function(){
	$("#login").click(function(){
		alert("Start to login");
		var tempSign = getTempSign();
		alert("get the tempSign : " + tempSign);
		var username = $("#username").val();
		var password = $("#password").val();
		alert("username : " + username +"\npassword : " + password);
		
		var postPass = md5(tempSign + username + md5(password));
		alert("postPass : " + postPass);
		
		alert("login: "+ login(username, postPass));
		
		
    });
});