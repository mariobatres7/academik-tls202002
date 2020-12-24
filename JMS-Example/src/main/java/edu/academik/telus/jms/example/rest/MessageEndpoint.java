package edu.academik.telus.jms.example.rest;

import edu.academik.telus.jms.example.rq.CustomMsg;
import edu.academik.telus.jms.example.service.ProducerService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mario Batres
 */
@Path("/messages")
public class MessageEndpoint {
    
    @Inject
    private ProducerService messageService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMessage(CustomMsg message){
        
        this.messageService.createMessage(message);
        
        return Response.ok().build();
    }
    
    
}
