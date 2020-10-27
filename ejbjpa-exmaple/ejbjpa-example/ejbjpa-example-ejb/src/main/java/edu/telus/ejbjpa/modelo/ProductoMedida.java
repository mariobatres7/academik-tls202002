package edu.telus.ejbjpa.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "producto_medida")
@SelectBeforeUpdate
@DynamicUpdate
@DynamicInsert
public class ProductoMedida implements Serializable {

    /*  Primera opcion
    @Id
    @Column(name = "producto_id")
    private Integer productoId;

    @Id
    @Column(name = "medida_id")
    private Integer medidaId;*/
    @EmbeddedId
    private ProductoMedidaPK pk;

    @Column(name = "existencia")
    private BigDecimal existencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id",
            insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medida_id", referencedColumnName = "medida_id",
            insertable = false, updatable = false)
    private Medida medida;

    public ProductoMedidaPK getPk() {
        return pk;
    }

    public void setPk(ProductoMedidaPK pk) {
        this.pk = pk;
    }

    /*
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
    }*/
    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    /*
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
        final ProductoMedida other = (ProductoMedida) obj;
        if (!Objects.equals(this.productoId, other.productoId)) {
            return false;
        }
        if (!Objects.equals(this.medidaId, other.medidaId)) {
            return false;
        }
        return true;
    }*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.pk);
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
        final ProductoMedida other = (ProductoMedida) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }
}
