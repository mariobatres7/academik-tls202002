package edu.telus.ejbjpa.servicio;

import edu.telus.ejbjpa.modelo.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class ProductoServicio {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Producto> findProductos() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> query = builder.createQuery(Producto.class); //select 

        query.from(Producto.class); // from productos

        List<Producto> productoList = entityManager.createQuery(query).getResultList();

        return productoList;
    }

    public void crearProducto(Producto producto) {
        this.entityManager.persist(producto);
    }
}
