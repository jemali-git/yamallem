package com.atn.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atn.commons.CommonsConfig;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/config.properties")
public class AppConfig extends CommonsConfig {

//	@Override
//	@Bean(name = "dataSource")
//	public DataSource dataSource() {
//		Driver driver = new org.h2.Driver();
//		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource(
//				driver,
//				"jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=2");
//		DataSource dataSource = new LazyConnectionDataSourceProxy(
//				simpleDriverDataSource);
//		return dataSource;
//	}
//	@Bean
//	public DataSourceInitializer dataSourceInitializer() {
//	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//	    resourceDatabasePopulator.addScript(new ClassPathResource("metadata/schema.sql"));
//	    resourceDatabasePopulator.addScript(new ClassPathResource("metadata/data.sql"));
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource());
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//        return dataSourceInitializer;
//    }  

}
