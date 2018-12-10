package com.yyc.oper.nobid.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
public class CustomRedisProperties extends RedisProperties {

	private String p;



	public String getP() {
		return p;
	}



	public void setP(String p) {
		this.setPassword(p);
		this.p = p;
	}

}
