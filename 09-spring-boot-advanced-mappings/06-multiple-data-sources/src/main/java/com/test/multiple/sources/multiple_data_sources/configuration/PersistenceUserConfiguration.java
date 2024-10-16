package com.test.multiple.sources.multiple_data_sources.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
@PropertySource({"classpath:persistence-multiple-db.properties"})
@EnableJpaRepositories(
		basePackages = "com.test.multiple.sources.multiple_data_sources.dao.user",
		entityManagerFactoryRef = "userEntityManager",
		transactionManagerRef = "userTransactionManager"		
		)
public class PersistenceUserConfiguration {
	
	@Autowired
	private Environment env;


    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		
		entityManager.setDataSource(userDataSource());
		entityManager.setPackagesToScan(
				new String[] {"com.test.multiple.sources.multiple_data_sources.model.user"});
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		entityManager.setJpaPropertyMap(properties);
		
		return entityManager;
	}


    @Primary
    @Bean
	public DataSource userDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("user.jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		
		return dataSource;
	}
    
    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {
    	JpaTransactionManager transactionManager = new JpaTransactionManager();
    	
    	transactionManager.setEntityManagerFactory(userEntityManager().getObject());
    	
    	return transactionManager;
    }

}
