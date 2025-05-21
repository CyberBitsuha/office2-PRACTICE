package com.office2.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        // PostgreSQL JDBC Driver
        ds.setDriverClassName("org.postgresql.Driver");
        // Адрес вашей БД. Замените <host>,<port>,<dbname> на реальные:
        ds.setUrl("jdbc:postgresql://localhost:5432/CR_2");
        ds.setUsername("postgres");
        ds.setPassword("admin");
        // (опционально) пул настроек:
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource());
        sfb.setPackagesToScan("com.office2.model");
        Properties hprops = new Properties();
        // PostgreSQL Dialect
        hprops.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hprops.put("hibernate.hbm2ddl.auto", "update");  // или validate
        hprops.put("hibernate.show_sql", "true");
        sfb.setHibernateProperties(hprops);
        return sfb;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txm = new HibernateTransactionManager();
        txm.setSessionFactory(sessionFactory);
        return txm;
    }
}
