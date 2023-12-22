package com.example.buysell_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class BuysellBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuysellBackApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(ProductRepository productRepository) {
//        return args -> {
////            productRepository.save(new Product(null, "PlayStation 5", 128.5, "New PS"));
////            productRepository.save(new Product(null, "Table", 500.0, "Black wooden table"));
////            productRepository.save(new Product(null, "Window", 285.7, "Plastic window"));
//            productRepository.save(Product.builder()
//                    .id(null)
//                    .name("PlayStation 5")
//                    .price(128.5)
//                    .description("New PS")
//                    .build());
//            productRepository.save(Product.builder()
//                    .id(null)
//                    .name("Table")
//                    .price(500.0)
//                    .description("Black wooden table")
//                    .build());
//            productRepository.save(Product.builder()
//                    .id(null)
//                    .name("Window")
//                    .price(285.7)
//                    .description("Plastic window")
//                    .build());
//        };
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
