package edu.academik.telus.decorator.interceptor;

import edu.academik.telus.decorator.domain.Product;
import edu.academik.telus.decorator.service.validator.ProductValidator;
import java.util.stream.Stream;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Mario Batres
 */
@Interceptor
@ProductView
@Priority(Interceptor.Priority.APPLICATION)
public class ProductViewInterceptor {

    @Inject
    private ProductValidator productValidator;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {

        // Reflection
        System.out.println("HOLA SOY EL INTERCEPTOR PREVIO A LLAMAR AL MÉTODO ... ");
        //HACER COSAS

        System.out.println(context.getTarget());

        System.out.println(context.getMethod());

        Stream.of(context.getParameters()).forEach(System.out::println);

        Stream.of(context.getParameters())
                .filter(obj -> obj instanceof Product)
                .map(obj -> (Product) obj)
                .findFirst()
                .ifPresent(product -> {
                    this.productValidator.validate(product);
                });
        
        Object obj = context.proceed();

        if (obj instanceof String) {
            obj = obj.toString().toUpperCase();
        }
        //HACER COSAS
        System.out.println("HOLA SOY EL INTERCEPTOR DESPUES DE HABER LLAMADO AL MÉTODO ....");

        return obj;
    }

}
