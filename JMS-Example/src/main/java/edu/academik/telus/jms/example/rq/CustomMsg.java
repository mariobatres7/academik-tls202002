package edu.academik.telus.jms.example.rq;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Mario Batres
 */
public class CustomMsg implements Serializable {

    private String titulo;

    private String content;
    
    private BigDecimal precio;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "CustomMsg{" + "titulo=" + titulo + ", content=" + content + ", precio=" + precio + '}';
    }

    

}
