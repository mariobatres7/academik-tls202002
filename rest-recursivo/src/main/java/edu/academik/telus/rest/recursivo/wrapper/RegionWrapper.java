package edu.academik.telus.rest.recursivo.wrapper;

/**
 *
 * @author Mario Batres
 */
public class RegionWrapper {

    private Long regionId;
    private String codigo;
    private String nombre;
    private Long padre;
    private String padreNombre;

    public RegionWrapper() {
    }

    public RegionWrapper(Long regionId, String codigo, String nombre, Long padre, String padreNombre) {
        this.regionId = regionId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.padre = padre;
        this.padreNombre = padreNombre;
    }

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

    public String getPadreNombre() {
        return padreNombre;
    }

    public void setPadreNombre(String padreNombre) {
        this.padreNombre = padreNombre;
    }

}
