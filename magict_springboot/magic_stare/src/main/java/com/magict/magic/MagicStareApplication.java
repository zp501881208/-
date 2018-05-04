package com.magict.magic;


import com.magict.magic.interceptor.IPInteceptor;
import com.magict.magic.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@MapperScan(basePackages = "com.magict.magic.mapper")
public class MagicStareApplication{
	public static void main(String[] args) {
		SpringApplication.run(MagicStareApplication.class, args);
	}

	@Configuration
	@Profile("dev")
	static class WebMvcConfigurerDev extends WebMvcConfigurerAdapter {
		@Bean
		IPInteceptor localInterceptor() {
			return new IPInteceptor();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
		}

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
	}

	@Configuration
	@Profile("prod")
	static class WebMvcConfigurerProd extends WebMvcConfigurerAdapter {
		@Bean
		IPInteceptor localInterceptor() {
			return new IPInteceptor();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
		}

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
	}

	@Configuration
	public class WebSocketConfig {
		@Bean
		public ServerEndpointExporter serverEndpointExporter() {
			return new ServerEndpointExporter();
		}

	}
}
