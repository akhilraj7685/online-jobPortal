package com.nt.cgf;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages= {"com.nt.entity"})
@EnableJpaRepositories(basePackages= {"com.nt.repo"})
@EnableTransactionManagement
@Import(value= {ControllerConfig.class,RepoConfig.class,ServiceConfig.class,EmailConfig.class})
public class AppConfig {
	
	@Autowired
	javax.sql.DataSource ds;
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager createTxMgr() {
		DataSourceTransactionManager dsmgr =new DataSourceTransactionManager();
		dsmgr.setDataSource(ds);
		return dsmgr;
		
	}

}
