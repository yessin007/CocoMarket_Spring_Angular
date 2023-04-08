package com.example.coco_spring.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Proxy;

@Configuration
public class MyGsonConfiguration {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Proxy.class, new ProxyTypeAdapter())
                .create();
    }
}
