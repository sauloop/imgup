package info.pablogiraldo.imgup;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);

//		registry.addResourceHandler("/img/**").addResourceLocations("file:/C:/pruebas/img/");

		registry.addResourceHandler("/img/**").addResourceLocations("file:/static/img/");

	}

}
