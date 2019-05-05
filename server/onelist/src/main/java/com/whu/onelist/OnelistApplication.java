package com.whu.onelist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.whu.onelist.mapper")
@SpringBootApplication
public class OnelistApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnelistApplication.class, args);
	}

}
