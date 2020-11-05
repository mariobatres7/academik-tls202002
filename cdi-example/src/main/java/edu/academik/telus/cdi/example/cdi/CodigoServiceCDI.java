package edu.academik.telus.cdi.example.cdi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mario Batres
 */
@RequestScoped
public class CodigoServiceCDI {


    @PostConstruct
    private void init() {
        System.out.println("Inicializando:  " + this.getClass().getName());
    }

    public String generarCodigo() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
