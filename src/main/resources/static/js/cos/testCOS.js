var selectedFile;

function wahaha(){
    selectedFile = document.getElementById("files").files[0];//获取读取的File对象
    file_name = selectedFile.name;//读取选中文件的文件名
    file_size = selectedFile.size;//读取选中文件的大小
    console.log("文件名:"+file_name+"大小："+file_size);
    var reader = new FileReader();//这里是核心！！！读取操作就是由它完成的。
    reader.readAsBinaryString(selectedFile);//读取文件的内容

    reader.onload = function(){
        //alert(this.result);//当读取完成之后会回调这个函数，然后此时文件的内容存储到了result中。直接操作即可。
    	file_content=this.result;
    	alert("加载完成");
    };
}

$(function(){
	var appid = "1252380644";
	var bucket = "test";
	var secret_id = "AKIDHTVYnHRjfmyEMJ87ZTDuSjBwmW4S4I5X";
	var secret_key = "S1kYck9RYUiojW73qS8xw2zoVBlAeEv8";
	
	var onceExpired = 0;
	var current = 	Math.round(new Date().getTime()/1000);
	var rdm = "23333";
	var expired =  current + 7200;
	var multi_effect_signature = "a="+appid+"&b="+bucket+"&k="+secret_id+"&e="+expired+"&t="+current+"&r="+rdm+"&f=";
	
	$(".test1").html(multi_effect_signature);
	
	$("#import").click(function(){//点击导入按钮，使files触发点击事件，然后完成读取文件的操作。
        $("#files").click();
    });
	
	var bucket = 'test';
    var appid = '1252380644';
    var sid = 'AKIDHTVYnHRjfmyEMJ87ZTDuSjBwmW4S4I5X';
    var skey = 'S1kYck9RYUiojW73qS8xw2zoVBlAeEv8';
    var region = 'sh';
	var cos = new CosCloud({
        appid: appid, // APPID 必填参数
        bucket: bucket, // bucketName 必填参数
        region: region, // 地域信息 必填参数 华南地区填gz 华东填sh 华北填tj
        getAppSign: function (callback) {//获取签名 必填参数
            callback("EWk+00Fc+OnfTYGIGwh0jhlD5hthPTEyNTIzODA2NDQmaz1BS0lESFRWWW5IUmpmbXlFTUo4N1pURHVTakJ3bVc0UzRJNVgmZT0xNTEyODQ2OTY1JnQ9MTUxMjgxMDk2NSZyPTExODI3NzA3OCZmPS8xMjUyMzgwNjQ0L3Rlc3QvJmI9dGVzdA==");
        },
        getAppSignOnce: function (callback) {//单次签名，参考上面的注释即可
            callback("EWk+00Fc+OnfTYGIGwh0jhlD5hthPTEyNTIzODA2NDQmaz1BS0lESFRWWW5IUmpmbXlFTUo4N1pURHVTakJ3bVc0UzRJNVgmZT0xNTEyODQ2OTY1JnQ9MTUxMjgxMDk2NSZyPTExODI3NzA3OCZmPS8xMjUyMzgwNjQ0L3Rlc3QvJmI9dGVzdA==");
        }
    });
	
	$(".test1").click(function(){
		alert(file_content);
	});
	
	var successCallBack = function (result) {
        console.log('request success.');
        $("#result").val(JSON.stringify(result));
    };

    var errorCallBack = function (result) {
        result = result || {};
        console.log('request error:', result && result.message);
        $("#result").val(result.responseText || 'error');
    };
    
    var lastTaskId;
    var taskReady = function (taskId) {
        lastTaskId = taskId;
    };
    
    var progressCallBack = function (curr, sha1) {
        var sha1CheckProgress = ((sha1 * 100).toFixed(2) || 100) + '%';
        var uploadProgress = ((curr || 0) * 100).toFixed(2) + '%';
        var msg = 'upload progress:' + uploadProgress + '; sha1 check:' + sha1CheckProgress + '.';
        console.log(msg);
        $("#result").val(msg);
    };
    
	$("#upload").click(function(){
		cos.uploadFile(successCallBack, errorCallBack, progressCallBack, bucket, "/"+file_name, selectedFile, 0, taskReady);
	});
	
	
	
	
});


