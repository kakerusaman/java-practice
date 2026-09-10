package com.github.kakerusaman.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import jakarta.servlet.ServletContext;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws jakarta.servlet.ServletException {
		System.out.println("=== WebAppInitializer.onStartup() 開始 ===");
		try {
			AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
			context.register(DataSourceConfig.class);
			System.out.println("=== DataSourceConfig 登録完了 ===");
			servletContext.addListener(new ContextLoaderListener(context));
			System.out.println("=== ContextLoaderListener 登録完了 ===");
		} catch (Throwable t) {
			System.err.println("=== WebAppInitializer エラー: " + t.getMessage());
			t.printStackTrace(System.err);
			throw new jakarta.servlet.ServletException(t);
		}
	}
	
}
