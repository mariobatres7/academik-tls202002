package edu.academik.telus.jpa.basico;

import edu.academik.telus.jpa.basico.modelo.Factura;
import edu.academik.telus.jpa.basico.modelo.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public static void ejecutarCriteria4(EntityManager entityManager) {
        //String queryString = "select producto_id, precio from producto ";

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class); //select 

        Root<Producto> root = query.from(Producto.class); // from productos

        //select producto_id, precio from ..
        query.multiselect(
                root.get("precio"),
                root.get("productoId")
        );

        List<Tuple> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(tuple -> {
            System.out.println(tuple.get(0) + ", " + tuple.get(1));
        });
    }

    public static void ejecutarCriteria5(EntityManager entityManager) {
        //String queryString = "select producto_id, precio from producto ";

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ProductoWrapper> query = builder.createQuery(ProductoWrapper.class); //select 

        Root<Producto> root = query.from(Producto.class); // from productos

        //select producto_id, precio from ..
        query.multiselect( //el orden acá es con base en un constructor 
                root.get("productoId"),
                root.get("precio")
        );

        List<ProductoWrapper> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(productWrapper -> {
            System.out.println(productWrapper.getProductoId() + ", " + productWrapper.getPrecio());
        });
    }

    public static void ejecutarCriteria6(EntityManager entityManager) {
        //String queryString = "select codigo from producto ";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> query = builder.createQuery(String.class); //select 

        Root<Producto> root = query.from(Producto.class); // from productos

        //select  precio from ..
        query.select(root.get("codigo"));

        List<String> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(codigo -> {
            System.out.println(codigo);
        });
    }

    public static void ejecutarPersist(EntityManager entityManager) {

        Producto producto = new Producto();
        producto.setCodigo("A0001");
        producto.setNombre("Coca cola");
        producto.setCodigoBarras("1231231231231231");
        producto.setPrecio(BigDecimal.valueOf(200.99));

        entityManager.getTransaction().begin();

        entityManager.persist(producto);

        entityManager.getTransaction().commit();
    }

    public static void ejecutarMerge(EntityManager entityManager) {

        Producto producto = entityManager.find(Producto.class, 1);

        producto.setPrecio(BigDecimal.valueOf(100.99));

        try {
            entityManager.getTransaction().begin();

            //update
            entityManager.merge(producto);

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }
    
     public static void ejecutarRemove(EntityManager entityManager) {

        Producto producto = entityManager.find(Producto.class, 1);

        try {
            entityManager.getTransaction().begin();

            //remover
            entityManager.remove(producto);

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }
    
    

    public static void ejecutarTransaccion(EntityManager entityManager) {

        Producto producto = entityManager.find(Producto.class, 1);

        producto.setPrecio(BigDecimal.valueOf(2123.99));

        try {
            entityManager.getTransaction().begin();

            //update
            entityManager.merge(producto); // comando1

            Factura factura = new Factura();
            factura.setNumero("0001");

            entityManager.persist(factura); // comando2

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();

            Logger.getLogger("update").log(Level.SEVERE, "Error", ex);
        }
    }

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("MYSQL_PU")
                .createEntityManager();

        //ejectuarCriteria2(entityManager, 30000);
        //ejecutarCriteria6(entityManager);
        //ejecutarCriteria5(entityManager);
        ejecutarRemove(entityManager);

        entityManager.close();
    }

}
