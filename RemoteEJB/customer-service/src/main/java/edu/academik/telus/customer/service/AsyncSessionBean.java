package edu.academik.telus.customer.service;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class AsyncSessionBean {

    public void method() {
        // Este crea documentos

        this.upload();
    }

    @Asynchronous
    public void upload() {
        // 5 minutos en subir
    }

}
