package test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.labyrinth.app.model.Student;

@Configuration
@ComponentScan("main.java.com.labyrinth.app")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:/hibernatetest.properties")
public class TestApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Autowired
	Environment env;

	static Properties hibernateProps = new Properties();

	/*static {
		try {
			hibernateProps.load(ClassLoader.getSystemResourceAsStream("hibernatetest.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"));
		// objectMapper.setPropertyNamingStrategy(new
		// PropertyNamingStrategy.PascalCaseStrategy());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl(env.getProperty("testdburl"));
		dataSource.setUsername(env.getProperty("testdbusername"));
		dataSource.setPassword(env.getProperty("testdbpassword"));
		

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.addAnnotatedClasses(Student.class).scanPackages("main.java.com.labyrinth.app.model")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		//sessionBuilder.setProperty("hibernate.hbm2ddl.auto", "create");

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}
