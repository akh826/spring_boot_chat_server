package akh826.com.chat_server;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;

import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
@EnableWebMvc
@ComponentScan
public class ThymeleafConfig implements WebMvcConfigurer,
        ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        // 模板資源路徑的前綴
        templateResolver.setPrefix("classpath:/templates/");
        // 模板資源路徑的後綴
        templateResolver.setSuffix(".html");
        // 編碼
        templateResolver.setCharacterEncoding("utf-8");
        // 快取設定
        templateResolver.setCacheable(false);
        // 模板模式
        templateResolver.setTemplateMode("html5");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(true);

        Set<IDialect> sets = new HashSet<IDialect>();
        sets.add(new LayoutDialect());
        sets.add(new Java8TimeDialect());
        sets.add(new SpringStandardDialect());
        templateEngine.setDialects(sets);

        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine engine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setRedirectHttp10Compatible(false);
        viewResolver.setCache(false);
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 靜態資源的路徑
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
