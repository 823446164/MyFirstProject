package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//在没有在yml文件中配置数据库时使用自动配置数据库，如果只是注解@SpringBootApplication会提示数据源找不到错误
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan
@MapperScan("com.example.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
