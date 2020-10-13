package edu.academik.telus.jpa.basico.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "medida")
@SelectBeforeUpdate
@DynamicUpdate
@DynamicInsert
public class Medida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medida_id")
    private Integer medidaId;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "medida")
    private List<ProductoMedida> productoMedidaList;

    public Integer getMedidaId() {
        return medidaId;
    }

    public void setMedidaId(Integer medidaId) {
        this.medidaId = medidaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoMedida> getProductoMedidaList() {
        return productoMedidaList;
    }

    public void setProductoMedidaList(List<ProductoMedida> productoMedidaList) {
        this.productoMedidaList = productoMedidaList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.medidaId);
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
        final Medida other = (Medida) obj;
        if (!Objects.equals(this.medidaId, other.medidaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medida{" + "medidaId=" + medidaId + ", descripcion=" + descripcion + '}';
    }

}
