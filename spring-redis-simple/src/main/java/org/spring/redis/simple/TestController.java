package org.spring.redis.simple;

import javax.annotation.Resource;

import org.spring.redis.core.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@Resource
	private CacheService cacheService;
	
	@RequestMapping("/test")
	public String testCache(){
		cacheService.put("test", "test spring redis cache");
		System.err.println("------"+cacheService.get("test"));
		return "success";
	}

}
