package com.noah.backend.global.config;

import com.noah.backend.global.interceptor.NicknameValidInterceptor;
import com.noah.backend.global.resolver.AccessTokenArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AccessTokenArgumentResolver accessTokenArgumentResolver;
    private final NicknameValidInterceptor nicknameValidInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost") // 허용할 출처를 여기에 명시
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
//
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(accessTokenArgumentResolver);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(nicknameValidInterceptor)
//                .addPathPatterns("/api/v1/member/nickname");
//    }
}
