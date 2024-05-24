package org.example.projectmd3_smartphone_ecommerce.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.example"})
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Cau Hinh Thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;

    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }




    //Cau hinh datasource
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/project_md3_smartphone_ecommerce?createDatabaseIfNotExist=true");
        source.setUsername("root");
        source.setPassword("root");
        return source;
    }

    //Cau hinh sessionFactory
    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        //thiet lap thong so ket noi CSDL
        factoryBean.setDataSource(getDataSource());
        //Quet qua cac package tim class ORM
        factoryBean.setPackagesToScan("rikkei.ss20_addtocart_session.entity");
        //cac thong so cau hinh cua hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql",true);//Ghi log cac cau lenh ma hibernate thuc thi
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");//Noi vs hibernate biet la dang thao tac voi he CSDL nao;
        factoryBean.setHibernateProperties(properties);

        try {
            factoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factoryBean.getObject();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/asset/css/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("/uploads/");
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver()  {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800); // 50MB
        resolver.setMaxUploadSize(209715200);
        return resolver;
    }



}