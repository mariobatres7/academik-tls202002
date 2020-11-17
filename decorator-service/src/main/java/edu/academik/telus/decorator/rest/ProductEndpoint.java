package edu.academik.telus.decorator.rest;

import edu.academik.telus.decorator.domain.Product;
import edu.academik.telus.decorator.qualifier.CodeGenerator;
import edu.academik.telus.decorator.qualifier.ProductCodeGenerator;
import edu.academik.telus.decorator.service.ProductService;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mario Batres
 */
@Path("/product")
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @Inject
    @CodeGenerator(type = CodeGenerator.Type.MD5)
    private ProductCodeGenerator productCodeGenerator;
    


    /*
        curl -X POST -d '{"id": 100, "code": "1000", "name": "Camisa", "enabled": true, "minQuantity" : 100, "maxQuantity" : 150}'  -H 'Content-Type:  application/json'  http://localhost:8080/decorator-service-1.0.0/rest/product
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sellProduct(Product product) {
        String resultado = this.productService.sellProduct(product);
        Map map = Map.of("resultado", resultado);
        return Response.ok(map).build();
    }

    @GET
    @Path("/code/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateCode(@PathParam("code") String code) {
        String resultado = this.productCodeGenerator.generator(code);
        Map map = Map.of("resultado", resultado);
        return Response.ok(map).build();
    }

}
