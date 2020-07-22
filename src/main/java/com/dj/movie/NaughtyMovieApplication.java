package com.dj.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.dj.movie.mapper")
public class NaughtyMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaughtyMovieApplication.class, args);
    }

}
