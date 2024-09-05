package com.DevPointSystem.Comptabilite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class ComptabiliteCoreApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(ComptabiliteCoreApplication.class, args);
//	}
    @Autowired
  private ObjectMapper objectMapper;
    
    private static final Logger log = LoggerFactory.getLogger(ComptabiliteCoreApplication.class);
    public static String jwtSecret = "";
    public static void main(String[] args) throws UnknownHostException {

        SpringApplication app = new SpringApplication(ComptabiliteCoreApplication.class);
 
        ConfigurableApplicationContext ctx = app.run(args);
        Environment env = ctx.getEnvironment();
        Boolean kafkaProfileStauts = Arrays.stream(env.getActiveProfiles()).anyMatch("kafka"::equals);
        String consumersStauts = "Consumer(s): \n";
 

        String protocol = "http";

        if (env.getProperty(
                "server.ssl.key-store") != null) {
            protocol = "https";
        }
//        defaultLang= env.getProperty("default-lang");

        log.info(
                "\n----------------------------------------------------------\n\t"
                + "Application's name '{}' is running! Access URLs:\n\t"
                + "Local: \t\t{}://localhost:{}{}\n\t"
                + "External: \t{}://{}:{}{}\n\t"
                + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                env.getProperty("server.contextPath"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.contextPath"),
                env.getActiveProfiles());
    }

//    -----------------------------------------
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration crosConfiguration = new CorsConfiguration();
        crosConfiguration.setAllowCredentials(true);
        crosConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4900"));
        crosConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        crosConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        crosConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", crosConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);

    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    @PostConstruct
  public void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }
}
