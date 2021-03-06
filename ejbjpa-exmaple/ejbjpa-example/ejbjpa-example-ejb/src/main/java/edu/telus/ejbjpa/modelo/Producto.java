package edu.telus.ejbjpa.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

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
@SelectBeforeUpdate
@DynamicUpdate
@DynamicInsert
public class Producto implements Serializable {

    @Id // que nuestra llave primera está definida como un solo campo
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql, postgres, sqlserver;  oracle cambia esta configuración
    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private BigDecimal precio;

    @JsonIgnore
    @ManyToMany(mappedBy = "productoSet")
    private Set<Cliente> clienteSet;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<FacturaDetalle> facturaDetalleList;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<ProductoMedida> productoMedidaList;

    //para aquellos campos que no están definidos en nuestra tabla
    @Transient
    private boolean valido;

    @Transient
    private String codigoNombre;

    @PostLoad
    protected void postLoad() {
        this.codigoNombre = this.codigo + " " + this.nombre;
    }

    public String getCodigoNombre() {
        return this.codigoNombre;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Set<Cliente> getClienteSet() {
        return clienteSet;
    }

    public void setClienteSet(Set<Cliente> clienteSet) {
        this.clienteSet = clienteSet;
    }

    @XmlTransient
    public List<FacturaDetalle> getFacturaDetalleList() {
        return facturaDetalleList;
    }

    public void setFacturaDetalleList(List<FacturaDetalle> facturaDetalleList) {
        this.facturaDetalleList = facturaDetalleList;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    @XmlTransient
    public List<ProductoMedida> getProductoMedidaList() {
        return productoMedidaList;
    }

    public void setProductoMedidaList(List<ProductoMedida> productoMedidaList) {
        this.productoMedidaList = productoMedidaList;
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
