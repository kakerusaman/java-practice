package com.github.kakerusaman.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("src/main/resources/META-INF/spring/config.properties")
public class DataSourceConfig {
	
	@Value("${DB_URL}")
	private String dbUrl;
	
	@Value("${DB_USERNAME}")
	private String dbUserName;
	
	@Value("${DB_PASSWORD}")
	private String dbPassword;
	

	@Bean
	public DataSource dateSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(dbUrl);
		ds.setUsername(dbUserName);
		ds.setPassword(dbPassword);
		ds.setDriverClassName("org.postgresql.Driver");
		
		
		// 起動時に接続確認
		try (var test = ds.getConnection()) {
			System.out.println("接続完了");
		} catch (Exception e) {
			System.out.println("接続失敗: " + e.getMessage());
			e.printStackTrace();
		}
		return ds;
	}
}
