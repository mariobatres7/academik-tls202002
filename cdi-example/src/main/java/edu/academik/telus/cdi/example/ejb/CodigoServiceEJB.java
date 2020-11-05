package edu.academik.telus.cdi.example.ejb;

import edu.academik.telus.cdi.example.cdi.CodigoServiceCDI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class CodigoServiceEJB {

    @Inject
    private CodigoServiceCDI codigoServiceCDI;
    
    @PostConstruct
    private void init() {
        
        System.out.println("codigoServiceCDI:  " + codigoServiceCDI);
        
        System.out.println("Inicializando:  " + this.getClass().getName());
    }

    public String generarCodigo() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
