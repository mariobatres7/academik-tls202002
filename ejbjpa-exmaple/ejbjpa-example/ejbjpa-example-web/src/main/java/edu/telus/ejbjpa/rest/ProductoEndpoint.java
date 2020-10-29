package edu.telus.ejbjpa.rest;

import edu.telus.ejbjpa.modelo.Producto;
import edu.telus.ejbjpa.servicio.ProductoServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("/{productoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto findProductoById(@PathParam("productoId") Integer productoId) {
        return this.productoServicio.findProductoById(productoId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducto(Producto producto) {
        this.productoServicio.createProducto(producto);
        return Response.ok().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProducto(Producto producto) {
        this.productoServicio.createProducto(producto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{productoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProductos(@PathParam("productoId") Integer productoId) {
        this.productoServicio.deleteProducto(productoId);
        return Response.ok().build();
    }

}
