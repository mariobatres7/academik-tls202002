package edu.academik.telus.spring.example;

import org.springframework.context.annotation.Bean;

/**
 *
 * @author Mario Batres
 */
public class Product {

    private Long productId;

    private String name;

    public void init(){
        System.out.println("Init");
    }
    
    
    public void destroy(){
        System.out.println("Destroy");
    }
    
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + productId + ", name=" + name + '}';
    }

}
