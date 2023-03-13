package com.example.coco_spring;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class CocoSpringApplication {
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    public static void main(String[] args) {

        SpringApplication.run(CocoSpringApplication.class, args);


        Stripe.apiKey = "sk_test_51MfpbjFZqNx8XmmhC1sHRKoes1AgZ98bQTRXs27ztbjZ6X4mBaV5X2oAif5LaLmSkQSqZEi7bUyFe4ukscn5Jjy200DlYLAV3X";


    }



}
