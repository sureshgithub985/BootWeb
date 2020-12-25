/*
 * package com.ey.core.config;
 * 
 * import java.util.Properties;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.orm.hibernate5.HibernateTransactionManager; import
 * org.springframework.orm.hibernate5.LocalSessionFactoryBean; import
 * org.springframework.transaction.annotation.EnableTransactionManagement;
 * 
 * @EnableTransactionManagement
 * 
 * @Configuration public class ApplicationConfig {
 * 
 * @Autowired private DataSource dataSource;
 * 
 * @Bean public LocalSessionFactoryBean getLocalSessionFactoryBean() {
 * 
 * LocalSessionFactoryBean localSessionFactoryBean = new
 * LocalSessionFactoryBean();
 * localSessionFactoryBean.setPackagesToScan("com.ey.core.model");
 * localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
 * localSessionFactoryBean.setDataSource(dataSource); return
 * localSessionFactoryBean; }
 * 
 * private Properties getHibernateProperties() {
 * 
 * Properties props = new Properties(); props.put("hibernate.show_sql", "true");
 * props.put("hibernate.format_sql", "true");
 * props.put("hibernate.hbm2ddl.auto", "update"); return props; }
 * 
 * @Bean public HibernateTransactionManager getHibernateTransactionManager() {
 * HibernateTransactionManager hibernateTransactionManager = new
 * HibernateTransactionManager();
 * hibernateTransactionManager.setSessionFactory(getLocalSessionFactoryBean().
 * getObject()); return hibernateTransactionManager; }
 * 
 * }
 */