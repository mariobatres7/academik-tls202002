package edu.academik.telus.customer.service;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mario Batres
 */
@Path("/customers")
public class CustomerEndpoint {
    
    @Inject
    private CustomerService customerService;
    
    @Inject
    private CustomerCDIService customerCDIService;
    
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response method(@PathParam("customerId") Long customerId){
    
        Map map = new HashMap(); 
        map.put("result", this.customerService.getCustomer(customerId));
        
        return Response.ok(map).build();
    }
    
    @GET
    @Path("/cdi/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response methodcdi(@PathParam("customerId") Long customerId){
    
        Map map = new HashMap(); 
        map.put("result", this.customerCDIService.getCustomer(customerId));
        
        return Response.ok(map).build();
    }
    
}
