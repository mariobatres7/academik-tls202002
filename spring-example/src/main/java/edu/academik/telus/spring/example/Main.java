package edu.academik.telus.spring.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Mario Batres
 */
public class Main {

    
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/beans.xml");

        Product product = (Product) context.getBean("product");

        System.out.println(product);
        
        ClassPathXmlApplicationContext cxt = (ClassPathXmlApplicationContext) context;
        cxt.close();
    }
}
