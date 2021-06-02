package com.health.SchoolHealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableWebMvc
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SchoolHealthApplication implements WebMvcConfigurer  {

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
