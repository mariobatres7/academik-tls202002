package edu.academik.telus.javamail.example.rest;

import edu.academik.telus.javamail.example.service.MailService;
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
@Path("/mail")
public class MailEndpoint {

    @Inject
    private MailService mailService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testMail() {
        this.mailService.sendMail();
        return Response.ok().build();
    }
}
