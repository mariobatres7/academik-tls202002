package edu.academik.telus.cdi.example.rest;

import edu.academik.telus.cdi.example.cdi.CodigoServiceCDI;
import edu.academik.telus.cdi.example.ejb.CodigoServiceEJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mario Batres
 */
@Path("/codigos")
public class CodigoEndpoint {

    @Inject
    private CodigoServiceEJB codigoServiceEJB;

    @Inject
    private CodigoServiceCDI codigoServiceCDI;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCodigoEJB() {
        var codigo = "ejb:  " + this.codigoServiceEJB.generarCodigo();
        return Response.ok(codigo).build();
    }
    
    
    @GET
    @Path("/cdi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCodigoCDI() {
        var codigo = "cdi:  " + this.codigoServiceCDI.generarCodigo();
        return Response.ok(codigo).build();
    }

}
