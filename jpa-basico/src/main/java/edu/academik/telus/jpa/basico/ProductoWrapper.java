package edu.academik.telus.jpa.basico;

import java.math.BigDecimal;

/**
 *
 * @author Mario Batres
 */
public class ProductoWrapper {

    private Integer productoId;

    private BigDecimal precio;

    public ProductoWrapper() {
    }

    public ProductoWrapper(Integer productoId) {
        this.productoId = productoId;
    }

    public ProductoWrapper(Integer productoId, BigDecimal precio) {
        this.productoId = productoId;
        this.precio = precio;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
