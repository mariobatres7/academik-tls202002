package edu.academik.telus.decorator.service;

import edu.academik.telus.decorator.domain.Product;
import edu.academik.telus.decorator.service.validator.ProductValidator;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Mario Batres
 */
@RequestScoped
public class ProductService {

    @Inject
    private ProductValidator productValidator;

    public void sellProduct(Product product) {

        this.productValidator.validate(product);
        
        System.out.println("Producto vendido");

        //vamos a vender
    }

}
