package com.saffy.gotcha.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
				.allowedHeaders("Content-Type", "X-AUTH-TOKEN", "Authorization", "Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials")
				.exposedHeaders("Content-Disposition", "X-AUTH-TOKEN", "Authorization", "Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials")
				.allowCredentials(true).maxAge(3600);
	}
}
