package edu.academik.telus.rest.recursivo.servicio;

import edu.academik.telus.rest.recursivo.dominio.Region;
import edu.academik.telus.rest.recursivo.wrapper.RegionWrapper;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author Mario Batres
 */
@RequestScoped // CDI
public class RegionService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Region> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Region> query = builder.createQuery(Region.class);

        query.from(Region.class);

        return this.entityManager.createQuery(query).getResultList();
    }

    public Region findById(Long regionId) {
        return this.entityManager.find(Region.class, regionId);
    }

    @Transactional
    public void create(Region region) {
        this.entityManager.persist(region);
    }

    @Transactional
    public void update(Region region) {
        this.entityManager.merge(region);
    }

    @Transactional
    public void delete(Long regionId) {
        Region region = this.entityManager.find(Region.class, regionId);
        this.entityManager.remove(region);
    }

    /// Métodos mas customizadods
    public List<RegionWrapper> findRegioneCustomizado() {

        //select r.*, r2.nombre from regiones r left outer join regiones r2 on r2.padre = r.region_id;
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<RegionWrapper> query = builder.createQuery(RegionWrapper.class);

        Root<Region> root = query.from(Region.class);

        Join<Region, Region> join = root.join("padreRegion", JoinType.LEFT);

        query.multiselect(
                root.get("regionId"),
                root.get("codigo"),
                root.get("nombre"),
                root.get("padre"),
                join.get("nombre")
        );

        return this.entityManager.createQuery(query).getResultList();
    }

}
