package back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward all paths that don't have a dot (not a file) to index.html
        // This handles nested SPA routes like /student/timetable or /admin/students
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
        
        registry.addViewController("/**/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
                
        // Explicitly handle root-level nested paths if needed
        registry.addViewController("/{path1:[^\\.]*}/{path2:[^\\.]*}")
                .setViewName("forward:/index.html");
                
        registry.addViewController("/{path1:[^\\.]*}/{path2:[^\\.]*}/{path3:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}
