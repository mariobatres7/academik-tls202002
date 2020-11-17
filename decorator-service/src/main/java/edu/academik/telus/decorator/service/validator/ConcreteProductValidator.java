package edu.academik.telus.decorator.service.validator;

import edu.academik.telus.decorator.domain.Product;

/**
 *
 * @author Mario Batres
 */
public class ConcreteProductValidator implements ProductValidator {

    @Override
    public void validate(Product product) {

        if (!product.isEnabled()) {
            throw new RuntimeException("El producto no es válido.");
        }

    }

}
