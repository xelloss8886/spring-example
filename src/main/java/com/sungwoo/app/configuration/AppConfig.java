package com.sungwoo.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.sungwoo.app.converter.RoleToUserProfileConverter;

@Configuration
@Import(JpaConfiguration.class)
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
public class AppConfig extends WebMvcConfigurerAdapter{
     
     
    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;
     
 
    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
 
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }
     
    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
     
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter((Converter<?, ?>) roleToUserProfileConverter);
    }
     
 
    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
     
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [<a class="vglnk" href="https://jira.spring.io/browse/SPR-6164" rel="nofollow"><span>https</span><span>://</span><span>jira</span><span>.</span><span>spring</span><span>.</span><span>io</span><span>/</span><span>browse</span><span>/</span><span>SPR</span><span>-</span><span>6164</span></a>], still present in Spring 4.3.0.
     * This is a workaround for this issue.
     */
     
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
     
}