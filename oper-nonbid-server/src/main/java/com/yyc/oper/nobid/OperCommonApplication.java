package com.yyc.oper.nobid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.bus.BusAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.ZipkinAutoConfiguration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "com.yyc.oper", "com.sgcc.uap.rest.annotation" }, exclude = {
		JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class, BusAutoConfiguration.class,
		ZipkinAutoConfiguration.class })
//@EnableDiscoveryClient
@EnableSwagger2
@EnableEncryptableProperties
public class OperCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperCommonApplication.class, args);
	}
}