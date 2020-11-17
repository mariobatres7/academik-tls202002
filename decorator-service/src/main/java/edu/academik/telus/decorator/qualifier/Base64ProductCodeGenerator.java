package edu.academik.telus.decorator.qualifier;

import java.util.Base64;

/**
 *
 * @author Mario Batres
 */
@CodeGenerator(type = CodeGenerator.Type.BASE64)
public class Base64ProductCodeGenerator implements ProductCodeGenerator {

    @Override
    public String generator(String code) {
        return Base64.getEncoder().encodeToString(code.getBytes());
    }

}
