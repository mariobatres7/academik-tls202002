package edu.academik.telus.decorator.qualifier;

/**
 *
 * @author Mario Batres
 */
@CodeGenerator(type = CodeGenerator.Type.MD5)
public class MD5ProductCodeGenerator implements ProductCodeGenerator {

    @Override
    public String generator(String code) {
        return code.hashCode() + "X000007";
    }

}
