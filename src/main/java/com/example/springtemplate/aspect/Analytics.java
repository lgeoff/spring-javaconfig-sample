package com.example.springtemplate.aspect;

import com.example.springtemplate.models.Book;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.logging.Logger;

/**
 * Created by lgeoff on 03.07.2015.
 */

@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class Analytics {
    Logger logger= Logger.getLogger(Analytics.class.getName());


    @Before("execution(* *.addBook(*)) && within(com.example.springtemplate.services..*) and args(book)")
    public void addElem(Book book){
        logger.info("Adding a new book:"+book.toString());
    }

    @Bean
    public Analytics analytics(){
        return new Analytics();
    }

}
