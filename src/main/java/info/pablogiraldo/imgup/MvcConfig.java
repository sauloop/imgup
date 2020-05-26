package info.pablogiraldo.imgup;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	ServletContext context;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);

//		registry.addResourceHandler("/img/**").addResourceLocations("file:/C:/pruebas/img/");

		String ruta = context.getRealPath("img/");

		registry.addResourceHandler("/img/**").addResourceLocations(ruta);

	}

}
