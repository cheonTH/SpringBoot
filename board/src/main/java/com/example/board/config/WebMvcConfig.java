package com.example.board.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링에서 해당 클래스를 설정 클래스로 인식하고 빈으로 등록함
public class WebMvcConfig implements WebMvcConfigurer { // WebMvcConfigurer 인터페이스를 구현하여 CORS 설정을 포함한 MVC 설정을 커스터마이징할 수 있음


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // addMapping("/**"): 모든 경로에 대해 CORS 설정을 적용
        registry.addMapping("/**")
            // allowedOrigins("http://localhost:3000"): React 애플리케이션이 실행되는 도메인(출처)에서 오는 요청을 허용
            .allowedOrigins("http://localhost:3000")
            // allowedMethods("GET", "POST", "PUT", "DELETE"): HTTP 메서드(GET, POST, PUT, DELETE)를 허용
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            // allowedHeaders("*"): 모든 헤더를 허용
            .allowedHeaders("*")
            // allowCredentials(true): 쿠키나 인증 정보를 포함한 요청을 허용
            .allowCredentials(true);
          
    }
}