package com.dhl.dashboard.db.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "apemEntityManagerFactory", transactionManagerRef = "apemTransactionManager", basePackages = "com.dhl.dashboard")
public class DBConfig {

	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "apemDataSource")
//	 @ConfigurationProperties(prefix = "apem.datasource")
	public DataSource apemDataSource() {
		return DataSourceBuilder.create().driverClassName(env.getProperty("apem.datasource.driverClassName"))
				.url(env.getProperty("apem.datasource.url")).username(env.getProperty("apem.datasource.username"))
				.password(env.getProperty("apem.datasource.password")).build();
//	  return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "apemEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("apemDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.dhl.dashboard").persistenceUnit("db1").build();
	}

	@Autowired
	@Bean(name = "apemTransactionManager")
	public PlatformTransactionManager apemTransactionManager(
			@Qualifier("apemEntityManagerFactory") EntityManagerFactory apemEntityManagerFactory) {
		return new JpaTransactionManager(apemEntityManagerFactory);
	}

	@Autowired
	@Bean(name = "apemJdbcTemplate")
	public JdbcTemplate apemJdbcTemplate(@Qualifier("apemDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Autowired
	@Bean(name = "euDataSource")
//	 @ConfigurationProperties(prefix = "eu.datasource")
	public DataSource euDataSource() {
		return DataSourceBuilder.create().driverClassName(env.getProperty("eu.datasource.driverClassName"))
				.url(env.getProperty("eu.datasource.url")).username(env.getProperty("eu.datasource.username"))
				.password(env.getProperty("eu.datasource.password")).build();
//	  return DataSourceBuilder.create().build();
	}

	@Autowired
	@Bean(name = "euJdbcTemplate")
	public JdbcTemplate euJdbcTemplate(@Qualifier("euDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Autowired
	@Bean(name = "amDataSource")
	@ConfigurationProperties(prefix = "am.datasource")
	public DataSource amDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Autowired
	@Bean(name = "amJdbcTemplate")
	public JdbcTemplate amJdbcTemplate(@Qualifier("amDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
