package edu.academik.telus.decorator.service.validator.decorator;

import edu.academik.telus.decorator.domain.Product;
import edu.academik.telus.decorator.service.validator.ProductValidator;
import java.math.BigDecimal;
import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 *
 * @author Mario Batres
 */
@Decorator
@Priority(1)
public class MaxQuantityProductValidator implements ProductValidator {

    @Inject
    @Delegate
    private ProductValidator productValidator;

    @Override
    public void validate(Product product) {

        if (product.getMaxQuantity() == null) {
            throw new RuntimeException("Cántidad Máxima es nula.");
        }

        /*
            -1  A < B
            0 A = B
            1 A > B
         */
        if (product.getMaxQuantity().compareTo(BigDecimal.valueOf(100L)) >= 0) {
            throw new RuntimeException("Cántidad Máxima es es mayor o igual que 100.  No es válida.");
        }

        this.productValidator.validate(product);

    }

}
