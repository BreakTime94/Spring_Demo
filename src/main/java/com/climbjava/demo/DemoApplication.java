package com.climbjava.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


@MapperScan("com.climbjava.demo.mapper")
@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		log.info("Hello World");
		SpringApplication.run(DemoApplication.class, args);
	}

}
