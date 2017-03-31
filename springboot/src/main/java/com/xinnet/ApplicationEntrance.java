package com.xinnet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@EnableAutoConfiguration
@ComponentScan
@MapperScan("com.xinnet.mapper")*/
//@EnableTransactionManagement
//@MapperScan("com.xinnet.mapper")
@SpringBootApplication
public class ApplicationEntrance {
	
//  
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEntrance.class, args);
	}
	
}
