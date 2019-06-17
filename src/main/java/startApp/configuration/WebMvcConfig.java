package startApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        Если нужно добавить "viewer" без какого либо спецдоступа (USER / ADMIN)
//        registry.addViewController("/login2").setViewName("login");
//        registry.addViewController("/registration2").setViewName("registration2");
//        registry.addViewController("/account").setViewName("account");
//        registry.addViewController("/main").setViewName("main");
//        registry.addViewController("/").setViewName("main");
    }
}
