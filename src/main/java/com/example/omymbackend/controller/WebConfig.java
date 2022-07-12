package com.example.omymbackend.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : WebConfig
 * author : ds
 * date : 2022-07-12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-12         ds          최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*").allowedOrigins("http://localhost:3000").allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PATCH.name()
        ); // allowedOrigins만 했을때 업데이트에서 오류나므로 강제적으로 메소드 허용 설정
    }
}
