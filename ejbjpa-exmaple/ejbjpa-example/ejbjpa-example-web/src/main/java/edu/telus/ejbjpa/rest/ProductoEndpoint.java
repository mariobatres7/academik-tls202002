package edu.telus.ejbjpa.rest;

import edu.telus.ejbjpa.modelo.Producto;
import edu.telus.ejbjpa.servicio.ProductoServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Mario Batres
 */
@Path("/productos")
public class ProductoEndpoint {

    @EJB
    private ProductoServicio productoServicio;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> findProductos() {
        return this.productoServicio.findProductos();
    }

}
