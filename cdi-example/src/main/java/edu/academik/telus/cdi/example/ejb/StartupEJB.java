package edu.academik.telus.cdi.example.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 *
 *  EL @Startup solo funciona con el paquete javax.ejb.Singleton
 * 
 *  NO UTILIZAR java.inject.Singleton
 * 
 * @author Mario Batres
 */
@Startup
@Singleton
public class StartupEJB {

    @PostConstruct
    public void init() {
        System.out.println("ME HE INICIALIZADO .............................");
    }
    
}


/*
 
    @Decorator <-- CDI 


    Patrón de diseño: Decorator

    Investigar el patrón de diseño decorador 

    Hacer un ejemplo


 */