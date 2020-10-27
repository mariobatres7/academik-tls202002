package edu.telus.ejbjpa.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario Batres
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nit")
    private String nit;

    @Column(name = "direccion")
    private String direccion;

    @ManyToMany
    @JoinTable(
            name = "cliente_producto",
            joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    )
    private Set<Producto> productoSet;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturaList;

    
    //@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
    //private Membresia membresia;
    
    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Producto> getProductoSet() {
        return productoSet;
    }

    public void setProductoSet(Set<Producto> productoSet) {
        this.productoSet = productoSet;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

   /* public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }*/

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.clienteId);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.clienteId, other.clienteId);
    }

    
    
}
