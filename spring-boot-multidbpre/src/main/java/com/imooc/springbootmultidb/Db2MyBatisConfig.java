package com.imooc.springbootmultidb;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value = "com.imooc.springbootmultidb.mapper2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class Db2MyBatisConfig {
	@Autowired // 自动装配
	@Qualifier("db2") // 指定注入名为db1的组件
	private DataSource db2;

	@Bean
	public SqlSessionFactory sqlSessionFactory2() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(db2);// sqlSessionFactory2使用的数据源为db2
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory2());// sqlSessionTemplate2使用的数据源也是关联到db2
	}
}