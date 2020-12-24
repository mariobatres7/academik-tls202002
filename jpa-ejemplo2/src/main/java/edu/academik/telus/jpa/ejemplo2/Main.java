package edu.academik.telus.jpa.ejemplo2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException, IOException {

        EntityManager entityManager = Persistence.createEntityManagerFactory("TELUS_DS").createEntityManager();

//        Motocicleta motocicleta = entityManager.find(Motocicleta.class, 1);
        ObjectMapper mapper = new ObjectMapper();

        //      String json = mapper.writeValueAsString(motocicleta);
        String json = "{\n"
                + "    \"modelo\": \"2023\",\n"
                + "    \"color\": \"Azul - Marino\",\n"
                + "    \"clienteId\": 1\n"
                + "}";
        
        System.out.println(json);
        
        Motocicleta motocicleta = mapper.readValue(json, Motocicleta.class);
        
        try {
            entityManager.getTransaction().begin();
            
            entityManager.persist(motocicleta);
            
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger("Main").log(Level.SEVERE, "error", ex);
            entityManager.getTransaction().rollback();
        }

        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
        
        Root<Motocicleta> root = query.from(Motocicleta.class);
        
        root.join("");
        
        
        
        
        /*
        try {
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setContacto("Pablo");
            cliente.setNombre("Academika Cursos");
            cliente.setDireccion("Guatemala");

            entityManager.persist(cliente);

            Motocicleta motocicleta = new Motocicleta();
            motocicleta.setCliente(cliente);
            
            motocicleta.setClienteId(cliente.getClienteId());
            motocicleta.setColor("Azul");
            motocicleta.setModelo("2022");

            entityManager.persist(motocicleta);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger("Main").log(Level.SEVERE, "error", ex);
            entityManager.getTransaction().rollback();
        }*/
        entityManager.close();
    }
}
