package edu.academik.telus.practice.one.model;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mario Batres
 */
public class Proveedor {

    @NotEmpty(message = "Código está vacío.")
    @NotNull(message = "Código es nulo.")
    private String codigo;

    @NotEmpty(message = "Nombre comercial está vacío.")
    @NotNull(message = "Nombre comercial es nulo.")
    private String nombreComercial;

    @Size(max = 2000, message = "Dirección excede de 2,000 carácteres")
    private String direccion;

    @NotEmpty(message = "Correo electrónico está vacío.")
    @NotNull(message = "Correo electrónico es nulo.")
    @Email(message = "Correo electrónico no es válido.")
    private String email;

    private boolean eliminado;
    
    private boolean activo;

    public Proveedor() {
        this.activo = true;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigo);
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
        final Proveedor other = (Proveedor) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Proveedor{" + "codigo=" + codigo + '}';
    }

}
