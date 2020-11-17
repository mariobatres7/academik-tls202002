package edu.academik.telus.pattern.decorator;

/**
 *
 * @author Mario Batres
 */
public class ConcreteValidator implements Validator {

    @Override
    public void validate() {
        System.out.println(this.getClass().getName());
    }
}
