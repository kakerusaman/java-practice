package com.github.kakerusaman.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("src/main/resources/META-INF/spring/config.properties")
public class DataSourceConfig {
	/**
	 * DBURL
	 */
	@Value("${DB_URL}")
	private String dbUrl;
	
	/**
	 * DBユーザー名
	 */
	@Value("${DB_USERNAME}")
	private String dbUserName;
	
	/**
	 * DBパスワード
	 */
	@Value("${DB_PASSWORD}")
	private String dbPassword;

	/**
	 * プールサイズ
	 */
	@Value("${MAX_POOLSIZE}")
	private int maxPoolSize;

	/**
	 * コネクションタイムアウト
	 */
	@Value("${CONNECTION_TIMEOUT}")
	private int connectionTimeout;

	@Bean
	public DataSource dateSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(dbUrl);
		ds.setUsername(dbUserName);
		ds.setPassword(dbPassword);
		ds.setDriverClassName("org.postgresql.Driver");
		// HikariCP設定
		ds.setMaximumPoolSize(maxPoolSize);
		ds.setConnectionTimeout(connectionTimeout);
		
		
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
