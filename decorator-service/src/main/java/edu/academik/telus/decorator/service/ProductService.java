package edu.academik.telus.decorator.service;

import edu.academik.telus.decorator.domain.Product;
import edu.academik.telus.decorator.interceptor.ProductView;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mario Batres
 */
@RequestScoped
@ProductView
public class ProductService {

    public String sellProduct(Product product) {

        System.out.println("Producto vendido");

        return "producto-vendido";
        //vamos a vender
    }

}
