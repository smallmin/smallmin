package com.smallmin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smallmin.Enity.Tag;
import com.smallmin.Service.TagService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
	
	//一些数据在启动时直接加载到数据库中，不需要再次去数据库中读
	//实际上，因为博客数据量较小，整个数据库都可以一开始读进来..数据库的作用就是持久化存储数据
	//每次增加数据的时候，别忘了在内存中的也要加数据
	public static Map<Integer, Tag> tags = new HashMap<Integer, Tag>() ;

	
	
	
    public static void main( String[] args ) throws Exception
    {
    	SpringApplication.run(App.class, args);
    }

	
}
