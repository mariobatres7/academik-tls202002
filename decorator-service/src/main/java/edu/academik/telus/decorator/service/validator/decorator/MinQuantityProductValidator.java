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
@Priority(2)
public class MinQuantityProductValidator implements ProductValidator {

    @Inject
    @Delegate
    private ProductValidator productValidator;

    @Override
    public void validate(Product product) {

        if (product.getMinQuantity() == null) {
            throw new RuntimeException("Cántidad Máxima es nula.");
        }

        /*
            -1  A < B
            0 A = B
            1 A > B
         */
        if (product.getMinQuantity().compareTo(BigDecimal.TEN) <= 0) {
            throw new RuntimeException("Cántidad Mínim es es menor o igual que 10.  No es válida.");
        }

        this.productValidator.validate(product);

    }

}
