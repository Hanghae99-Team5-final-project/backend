package com.sparta.team5finalproject;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
<<<<<<< HEAD
=======
import org.slf4j.LoggerFactory;
>>>>>>> 4c4cb730d54c2fa0630bbb248b2a28112d547b1b
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableEncryptableProperties
public class Team5FinalProjectApplication {

    public static void main(String[] args) {
<<<<<<< HEAD
=======

>>>>>>> 4c4cb730d54c2fa0630bbb248b2a28112d547b1b
//        // assume SLF4J is bound to logback in the current environment
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);
<<<<<<< HEAD
=======

>>>>>>> 4c4cb730d54c2fa0630bbb248b2a28112d547b1b
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
