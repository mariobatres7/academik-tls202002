package edu.academik.telus.jpa.ejemplo2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "motocicleta")
public class Motocicleta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motocicleta_id")
    private Integer motocicletaId;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "color")
    private String color;
    
    @Column(name = "cliente_id")
    private Integer clienteId;

    /*
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", insertable = false, updatable = false)
    private Cliente cliente;*/

    public Integer getMotocicletaId() {
        return motocicletaId;
    }

    public void setMotocicletaId(Integer motocicletaId) {
        this.motocicletaId = motocicletaId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /*
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }*/

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.motocicletaId);
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
        final Motocicleta other = (Motocicleta) obj;
        return Objects.equals(this.motocicletaId, other.motocicletaId);
    }
    
    

}
