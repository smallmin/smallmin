package com.smallmin.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smallmin.App;
import com.smallmin.Tool.TokenTool;

import antlr.Token;

@Controller
public class QingController {
	
	@RequestMapping(value="/qing", method = RequestMethod.GET)
	public String qing(HttpServletRequest requset, Model model){
		return "qing";
	}
	
	@RequestMapping(value="/qing/log", method = RequestMethod.POST)
	@ResponseBody
	public String qing_log(@RequestParam("begin_time") String begin_time,
							 @RequestParam("end_time") String end_time,
							 @RequestParam("img_id") String img_id,
							 HttpServletRequest requset,
							 HttpServletResponse response){
		try {
			
			String addr = requset.getRemoteAddr();
			
			Long begin_timestamp = Long.valueOf(begin_time);
			Long end_timestamp = Long.valueOf(end_time);
			String val = String.valueOf(end_timestamp-begin_timestamp);
			addr = addr + "_" + img_id;
			
			System.out.println(addr+" " +val);
			if(App.qing_log.containsKey(addr)) {
				Long pre = Long.valueOf(App.qing_log.get(addr));
				Long cur = Long.valueOf(val);
				if(cur>pre)App.qing_log.put(addr,val);
			}
			
			else App.qing_log.put(addr,val);
			
			return "OK";
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			return "failed by error, please concat to admin";
		}
	}
	
	@RequestMapping(value="/qing/info", method = RequestMethod.GET)
	public String qing_info(HttpServletRequest requset, Model model){
		
		try {
			String content = "";
			content+="<table>";
			
			
			
			for (Map.Entry<String, String> it: App.qing_log.entrySet()) {
				String str = it.getKey();
				String ip_str = str.substring(0, str.length()-2);
				String type_str = str.substring(str.length()-1);
				System.out.println(ip_str+" "+type_str);
				content+="<tr>";
				content+="<th>"+ip_str+"</th>";
				content+="<th>"+type_str+"</th>";
				content+="<th>"+it.getValue()+"</th>";
				
				
				content+="</tr>";
			}
			content+="</table>";
			model.addAttribute("content", content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qing_info";
	}
	
}
