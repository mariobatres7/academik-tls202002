package edu.telus.ejbjpa.soap;

import edu.telus.ejbjpa.modelo.Producto;
import edu.telus.ejbjpa.servicio.ProductoServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author Mario Batres
 */
@WebService(
        name = "ProductoSOAP",
        serviceName = "ProductoServicioSOAP"
)
public class ProductoWebService {

    @EJB
    private ProductoServicio productoServicio;

    @WebMethod(operationName = "findProductos")
    @WebResult(name = "productoResponse")
    public List<Producto> findProductos() {
        return this.productoServicio.findProductos();
    }
}
