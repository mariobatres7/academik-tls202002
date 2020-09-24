package edu.academik.telus.jpa.basico;

import edu.academik.telus.jpa.basico.modelo.Producto;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void ejectuarCriteria1(EntityManager entityManager) {
        //String queryString = "select * from producto";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> query = builder.createQuery(Producto.class); //select 

        query.from(Producto.class); // from productos

        //getResultList NO lanza ninguna exception al no encontrar resultados
        List<Producto> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(System.out::println);
    }

    public static void ejectuarCriteria2(EntityManager entityManager, Integer productoId) {
        //String queryString = "select * from producto where producto_id = ?";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> query = builder.createQuery(Producto.class); //select 

        Root<Producto> root = query.from(Producto.class); // from productos

        query.where(
                builder.equal(root.get("productoId"), productoId)
        );

        try {
            Producto producto = entityManager.createQuery(query).getSingleResult();

            System.out.println(producto);

        } catch (NoResultException nre) {
            System.err.println(nre.getMessage());
        }
    }

    public static void ejectuarCriteria3(EntityManager entityManager, BigDecimal rangoInicial, BigDecimal rangoFinal) {
        //String queryString = "select * from producto where precio between ? and ? order by precio";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> query = builder.createQuery(Producto.class); //select 

        Root<Producto> root = query.from(Producto.class); // from productos

        query.where(
                builder.between(root.get("precio"), rangoInicial, rangoFinal)
        );

        query.orderBy(builder.asc(root.get("precio")));

        List<Producto> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("MYSQL_PU")
                .createEntityManager();

        //ejectuarCriteria2(entityManager, 30000);
        ejectuarCriteria3(entityManager, BigDecimal.valueOf(100), BigDecimal.valueOf(400));

        entityManager.close();
    }

}
