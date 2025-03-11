//package com.example.cricket;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
//@SpringBootApplication
////@EnableAspectJAutoProxy
//public class CricketApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(CricketApplication.class, args);
//	}
//}
package com.example.cricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CricketApplication {
	public static void main(String[] args) {
		SpringApplication.run(CricketApplication.class, args);
	}
}