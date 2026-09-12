package com.github.kakerusaman.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws jakarta.servlet.ServletException {
		System.out.println("=== WebAppInitializer.onStartup() 開始 ===");
		try {
			AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
			// DBConfig
			context.register(DataSourceConfig.class);
			
			// WebAPの設定
            context.register(WebConfig.class);
			ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
			dispatcher.setLoadOnStartup(1);
			dispatcher.addMapping("/");

		} catch (Throwable t) {
			System.err.println("=== WebAppInitializer エラー: " + t.getMessage());
			t.printStackTrace(System.err);
			throw new jakarta.servlet.ServletException(t);
		}
	}
	
}
