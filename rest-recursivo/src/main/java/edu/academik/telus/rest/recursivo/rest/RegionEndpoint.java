package edu.academik.telus.rest.recursivo.rest;

import edu.academik.telus.rest.recursivo.dominio.Region;
import edu.academik.telus.rest.recursivo.servicio.RegionService;
import edu.academik.telus.rest.recursivo.wrapper.RegionWrapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Mario Batres
 */
@Path("/regiones")
public class RegionEndpoint {

    @Inject
    private RegionService regionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Region> findAll() {
        return this.regionService.findAll();
    }

    @GET
    @Path("/customizado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegionWrapper> findRegioneCustomizado() {
        return this.regionService.findRegioneCustomizado();
    }

    @GET
    @Path("/{regionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Region findById(@PathParam("regionId") Long regionId) {
        return this.regionService.findById(regionId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Region create(Region region) {
        this.regionService.create(region);
        return region;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Region region) {
        this.regionService.update(region);
    }

    @DELETE
    @Path("/{regionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("regionId") Long regionId) {
        this.regionService.delete(regionId);
    }
}
