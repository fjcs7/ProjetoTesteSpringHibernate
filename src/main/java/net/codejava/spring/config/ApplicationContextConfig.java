package net.codejava.spring.config;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.codejava.spring.dao.RedeSocialDAO;
import net.codejava.spring.dao.RedeSocialDAOI;

@Configuration
@ComponentScan("net.codejava.spring")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("org.postgresql.Driver");
    	dataSource.setUrl("jdbc:postgresql://localhost:5432/sportsgo");
    	dataSource.setUsername("usuario");
    	dataSource.setPassword("construcaosw");
    	
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    	properties.put("hibernate.hbm2ddl.auto","update");
    	return properties;
    }

    private Class[] getClassesAnotadas(){
    	  ArrayList<Class> classes = new ArrayList<Class>();

    	  // the following will detect all classes that are annotated as @Entity
    	  ClassPathScanningCandidateComponentProvider scanner =
    	    new ClassPathScanningCandidateComponentProvider(false);
    	  scanner.addIncludeFilter(new AnnotationTypeFilter(javax.persistence.Entity.class));

    	  // only register classes within "com.fooPackage" package
    	  for (BeanDefinition bd : scanner.findCandidateComponents("net.codejava.spring.model")) {
    	    String name = bd.getBeanClassName();
    	    try {
    	      classes.add(Class.forName(name));
    	    } catch (Exception E) {
    	      // TODO: handle exception - couldn't load class in question
    	    }
    	  } // for

    	  return classes.toArray(new Class[classes.size()]);
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	
    	AnnotationSessionFactoryBean anotationSeesionFactoryBean = new AnnotationSessionFactoryBean();
    	anotationSeesionFactoryBean.setDataSource(dataSource);
    	anotationSeesionFactoryBean.setPackagesToScan("net.codejava.spring.model");
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(getClassesAnotadas());
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "redeSocialDao")
    public RedeSocialDAOI getRedeSocialDAO(SessionFactory sessionFactory) {
    	return new RedeSocialDAO(sessionFactory);
    }
}
