package com.mx.nativelabs.backendapp.config;

//import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {
	
	//private static final org.apache.log4j.Logger logger = Logger.getLogger(SpringMVCConfig.class);

	/**
	 * Desactiva el CORS  y se configuran los metodos permitios para el 
	 * ser consumidos por el cliente 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
	//  Set routes that allow cross domain routing 
	    registry.addMapping("/**")
	   
	            .allowedMethods("HEAD", "GET","POST", "DELETE", "PATCH")  .allowedHeaders("*")
                .allowedOriginPatterns("*");
	}

}
