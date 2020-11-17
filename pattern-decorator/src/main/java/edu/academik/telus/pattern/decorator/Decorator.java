package edu.academik.telus.pattern.decorator;

/**
 *
 * @author Mario Batres
 */
public abstract class Decorator implements Validator {

    protected final Validator validator;

    public Decorator(Validator validator) {
        this.validator = validator;
    }

}
