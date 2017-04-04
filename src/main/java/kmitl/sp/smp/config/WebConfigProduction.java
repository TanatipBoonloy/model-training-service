package kmitl.sp.smp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jo on 4/3/2017.
 */
@Profile("production")
@Configuration
@EnableWebMvc
public class WebConfigProduction extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PATCH","PUT", "DELETE")
                .allowedHeaders("*")
//                .exposedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
}
