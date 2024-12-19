package com.example.edureka.notificationservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Configuration
public class FreemerkerConfig {

    @Value("${application.template.dir}")
    private String templateDir;

    @Bean
    @Primary
    public freemarker.template.Configuration freemarkerConfiguration() throws IOException {

        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
        cfg.setDirectoryForTemplateLoading(new File(templateDir));
        cfg.setIncompatibleImprovements(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);

        return cfg;
    }
}
