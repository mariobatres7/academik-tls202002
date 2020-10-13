package edu.academik.telus.jpa.basico.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mario Batres
 */
@Embeddable
public class ProductoMedidaPK implements Serializable {

    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "medida_id")
    private Integer medidaId;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getMedidaId() {
        return medidaId;
    }

    public void setMedidaId(Integer medidaId) {
        this.medidaId = medidaId;
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.productoId);
        hash = 61 * hash + Objects.hashCode(this.medidaId);
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
        final ProductoMedidaPK other = (ProductoMedidaPK) obj;
        if (!Objects.equals(this.productoId, other.productoId)) {
            return false;
        }
        if (!Objects.equals(this.medidaId, other.medidaId)) {
            return false;
        }
        return true;
    }

}
