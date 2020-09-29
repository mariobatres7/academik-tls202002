package edu.academik.telus.jpa.basico.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_detalle_id")
    private Integer facturaId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "total")
    private BigDecimal total;
    
    @ManyToOne
    @JoinColumn(name = "factura_id", referencedColumnName = "factura_id")
    private Factura factura;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    private Producto producto;

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.facturaId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FacturaDetalle other = (FacturaDetalle) obj;
        return Objects.equals(this.facturaId, other.facturaId);
    }
    
    
    
}
