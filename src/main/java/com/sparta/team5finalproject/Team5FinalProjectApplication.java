package com.sparta.team5finalproject;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableEncryptableProperties
public class Team5FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5FinalProjectApplication.class, args);
    }

//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//            + "classpath:application.properties,"
//            + "classpath:aws.yml";

//    public static void main(String[] args) {
//        new SpringApplicationBuilder(Team5FinalProjectApplication.class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
//    }


}
