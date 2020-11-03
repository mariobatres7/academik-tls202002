package edu.academik.telus.springweb.mvc.dao;

import edu.academik.telus.springweb.mvc.model.Producto;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario Batres
 */
@Repository
public class ProductoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Producto> list() {

        var criteriaBuilder = this.sessionFactory.getCurrentSession().getCriteriaBuilder();

        var query = criteriaBuilder.createQuery(Producto.class);

        query.from(Producto.class);

        return this.sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

}
