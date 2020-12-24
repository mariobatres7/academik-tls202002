package edu.academik.telus.rest.recursivo.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "regiones")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "padre")
    private Long padre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre", referencedColumnName = "region_id", insertable = false, updatable = false)
    private Region padreRegion;

    @JsonIgnore
    @OneToMany(mappedBy = "padreRegion")
    private List<Region> regionesHijasList;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPadre() {
        return padre;
    }

    public void setPadre(Long padre) {
        this.padre = padre;
    }

    public Region getPadreRegion() {
        return padreRegion;
    }

    public void setPadreRegion(Region padreRegion) {
        this.padreRegion = padreRegion;
    }

    public List<Region> getRegionesHijasList() {
        return regionesHijasList;
    }

    public void setRegionesHijasList(List<Region> regionesHijasList) {
        this.regionesHijasList = regionesHijasList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.regionId);
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
        final Region other = (Region) obj;
        return Objects.equals(this.regionId, other.regionId);
    }

    @Override
    public String toString() {
        return "Region{" + "regionId=" + regionId + '}';
    }

}
