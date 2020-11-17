package edu.academik.telus.pattern.decorator;

/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        boolean validateFirst = false;

        boolean validateSecond = false;

        Validator validator = new ConcreteValidator();

        if (validateFirst) {
            validator = new FirstDecoratorValidator(validator);
        }

        if (validateSecond) {
            validator = new SecondDecoratorValidator(validator);
        }
        
        validator.validate();

    }
}
