package edu.academik.telus.first.maven;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new 
                    ClassPathXmlApplicationContext("/beans.xml");

        Customer customer1 = (Customer) context.getBean("customer1");

        System.out.println(customer1.toString());
        
        Customer customer2 = (Customer) context.getBean("customer2");

        System.out.println(customer2);
        
        
        // Tarea1:  Crear otra clase y hacer injection por medio del context
        
        // Leer y/o investigar:  Bean Validation
        
        
        
        
        /*
        System.out.println("Hello world");

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Pablo");
        customer1.setAddress("Guatemala");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Avenir");
        customer2.setAddress("Mixco");

        System.out.println(customer1.toString());

        System.out.println(customer2.toString());*/
    }

}
