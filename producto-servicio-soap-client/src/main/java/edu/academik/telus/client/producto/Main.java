package edu.academik.telus.client.producto;

import edu.academik.telus.client.producto.soap.Producto;
import edu.academik.telus.client.producto.soap.ProductoServicioSOAP;
import java.util.List;

/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        ProductoServicioSOAP servicio = new ProductoServicioSOAP();
        List<Producto> productoList = servicio.getProductoSOAPPort().findProductos();

        productoList.stream().forEach(producto -> {
            System.out.println(producto.getCodigo() + "  " + producto.getNombre());

        });

    }
}
