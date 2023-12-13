package com.codeup.codeupspringblog;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringTest {
    public static void main(String[] args) {

        // get a ref to the running spring app
        // var
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CodeupSpringBlogApplication.class);

        // print all the bean names
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);

    }
}
