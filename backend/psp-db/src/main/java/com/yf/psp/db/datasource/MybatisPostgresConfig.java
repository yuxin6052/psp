package com.yf.psp.db.datasource;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean; 
import org.mybatis.spring.annotation.MapperScan; 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
 

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.yf.psp.db.postgres",sqlSessionFactoryRef="postgresSqlSessionFactory")
public class MybatisPostgresConfig {
	
	@Value(value = "${spring.postgres.datasource.min-evictable-idle-time-millis}")
	private long minEvictableIdleTimeMillis;
	@Value(value = "${spring.postgres.datasource.max-evictable-idle-time-millis}")
	private long maxEvictableIdleTimeMillis;
	
	@Bean
    public PlatformTransactionManager txManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(postgresDataSource());
    }

	 @Primary
	 @Bean(name="postgresDataSource")
	 @ConfigurationProperties(prefix="spring.postgres.datasource")
	 public DataSource postgresDataSource() throws PropertyVetoException {
		 /* //ok 
		  ComboPooledDataSource ds = new ComboPooledDataSource ();
		 ds.setJdbcUrl("jdbc:postgresql://115.159.114.116:5432/home?currentSchema=cf");
		 ds.setDriverClass("org.postgresql.Driver");
		 ds.setPassword("040fa61e-4af7-4997-a8a4-387123997ea5");
		 ds.setUser("home"); 
		  return ds;
		  */
		/* //ok 
		 DruidDataSource ds = new DruidDataSource(); 
		   
		   ds.setDriverClassName( "org.postgresql.Driver");
	 
		  ds.setUrl("jdbc:postgresql://115.159.114.116:5432/home?currentSchema=cf"); 
		  ds.setPassword("040fa61e-4af7-4997-a8a4-387123997ea5");
			 ds.setUsername("home");
			  return ds; */
		 DruidDataSource ds =	DruidDataSourceBuilder.create().build();
		 ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		 ds.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
		 return ds;
	 } 
	
	  @Primary
	 @Bean(name="postgresSqlSessionFactory") 
	 public SqlSessionFactory postgresSqlSessionFactory(@Qualifier("postgresDataSource") DataSource dataSource) throws Exception  {
		 SqlSessionFactoryBean b = new SqlSessionFactoryBean();
		 b.setDataSource(dataSource);
		 b.setMapperLocations(new PathMatchingResourcePatternResolver()
	                .getResources("classpath*:**/*Mapper.xml"));
		 b.setConfigLocation(new PathMatchingResourcePatternResolver()
	                .getResources("classpath:mybatis-config-postgres.xml")[0]);
		 return b.getObject();	
	 }
	 
	 /*@Primary
	 @Bean(name="postgresSqlSessionTemplate") 
	 public SqlSessionTemplate postgresSqlSessionTemplate() throws Exception   {
		 SqlSessionTemplate b = new SqlSessionTemplate(postgresSqlSessionFactory());
		return b;
	 }*/
	 
	 
}
