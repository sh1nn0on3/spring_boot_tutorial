package com.example.Spring.Boot.Tutorial.database;

import com.example.Spring.Boot.Tutorial.model.Product;
import com.example.Spring.Boot.Tutorial.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

//                Product productA = new Product("iphone 12" , 2021 , 999.0 , "" );
//                 System.out.println("insert data" +productRepository.save(productA));
            }
        };
    }
}
