package com.kravchenko.config;


import com.kravchenko.dao.TaskDAO;
import com.kravchenko.dao.TaskDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Main app configuration.
 */
@Configuration
@ComponentScan("com.kravchenko")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {


    /**
     * Task DAO bean.
     * @return returns Task DAO Implementation
     */
    @Bean
    public TaskDAO taskDAO() {
        return new TaskDAOImpl();
    }

    /**
     * This method configures resources handlers to intercept all calls from /** location.
     * @param registry Injected param
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");

    }

    /**
     * Sets view resolver for jsp pages
     * @return TnternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp");
        bean.setViewClass(JstlView.class);
        bean.setSuffix(".jsp");
        return bean;
    }

}
