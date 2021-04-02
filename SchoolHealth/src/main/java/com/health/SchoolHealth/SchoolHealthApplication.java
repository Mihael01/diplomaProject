package com.health.SchoolHealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;


@SpringBootApplication
//@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@EnableWebMvc
public class SchoolHealthApplication implements WebMvcConfigurer  { // extends WebMvcConfigurerAdapter //implements WebMvcConfigurer { //

	public static void main(String[] args) {

		SpringApplication.run(SchoolHealthApplication.class, args);
	}


		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry
					.addResourceHandler("/webjars/**")
					.addResourceLocations("/webjars/");
		}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//		localeChangeInterceptor.setParamName("lang");
//		registry.addInterceptor(localeChangeInterceptor);
//	}
//
//	@Bean
//	public LocaleResolver localeResolver() {
//		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//		java.util.Locale bgLocale=new java.util.Locale("bg","BG");
//		cookieLocaleResolver.setDefaultLocale(bgLocale);
//		return cookieLocaleResolver;
//	}
}
