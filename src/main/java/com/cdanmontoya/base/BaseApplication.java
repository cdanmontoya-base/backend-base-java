package com.cdanmontoya.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BaseApplication {


  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }

}
