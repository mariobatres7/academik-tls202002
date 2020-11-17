package edu.academik.telus.pattern.decorator;

/**
 *
 * @author Mario Batres
 */
public class FirstDecoratorValidator extends Decorator {

    public FirstDecoratorValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate() {

        this.validator.validate();

        System.out.println(this.getClass().getName());

    }

}
