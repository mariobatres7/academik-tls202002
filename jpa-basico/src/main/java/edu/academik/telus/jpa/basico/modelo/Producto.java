package edu.academik.telus.jpa.basico.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * +---------------+---------------+------+-----+---------+----------------+ |
 * Field | Type | Null | Key | Default | Extra |
 * +---------------+---------------+------+-----+---------+----------------+ |
 * producto_id | int(11) | NO | PRI | NULL | auto_increment | | codigo |
 * varchar(255) | YES | | NULL | | | codigo_barras | varchar(36) | NO | | NULL |
 * | | precio | decimal(16,6) | YES | | NULL | |
 * +---------------+---------------+------+-----+---------+----------------+
 *
 *
 *
 *
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    @Id // que nuestra llave primera está definida como un solo campo
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql, postgres, sqlserver;  oracle cambia esta configuración
    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio")
    private BigDecimal precio;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.productoId);
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
        final Producto other = (Producto) obj;
        if (!Objects.equals(this.productoId, other.productoId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "productoId=" + productoId + ", codigo=" + codigo + ", codigoBarras=" + codigoBarras + ", precio=" + precio + '}';
    }

}
