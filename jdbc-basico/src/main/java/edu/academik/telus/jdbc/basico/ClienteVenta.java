package edu.academik.telus.jdbc.basico;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Mario Batres
 */
public class ClienteVenta {

    private Integer clienteId;

    private String clienteNombre;

    private Integer ventaId;

    private String numero;

    //disponible desde java 8
    private LocalDateTime fechaFactura;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDateTime fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @Override
    public String toString() {
        return "ClienteVenta{" + "clienteId=" + clienteId + ", clienteNombre=" + clienteNombre + ", ventaId=" + ventaId + ", numero=" + numero + ", fechaFactura=" + fechaFactura + '}';
    }

}
