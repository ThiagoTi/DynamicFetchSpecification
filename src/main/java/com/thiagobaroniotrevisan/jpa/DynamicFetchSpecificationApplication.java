package com.thiagobaroniotrevisan.jpa;

import com.thiagobaroniotrevisan.jpa.repository.impl.MyRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MyRepositoryImpl.class)
public class DynamicFetchSpecificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicFetchSpecificationApplication.class, args);
    }

}
