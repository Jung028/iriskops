package com.alipay.riskops.web;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alipay.riskops")
@MapperScan("com.alipay.riskops.common.dal.auto.custom") // <- package of your DAOs
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}