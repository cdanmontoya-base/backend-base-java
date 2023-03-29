package com.cdanmontoya.base.infrastructure.configuration.database;

import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class DatabaseConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource driverManagerDataSource() {
    return new DriverManagerDataSource();
  }

  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

  @Bean
  public Jdbi jdbi(DataSource dataSource) {
    TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);

    return Jdbi.create(proxy)
        .installPlugin(new SqlObjectPlugin())
        .installPlugin(new PostgresPlugin());
  }

}
