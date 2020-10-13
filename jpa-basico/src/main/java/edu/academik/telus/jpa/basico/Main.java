package edu.academik.telus.jpa.basico;

import edu.academik.telus.jpa.basico.modelo.Cliente;
import edu.academik.telus.jpa.basico.modelo.Factura;
import edu.academik.telus.jpa.basico.modelo.FacturaDetalle;
import edu.academik.telus.jpa.basico.modelo.Medida;
import edu.academik.telus.jpa.basico.modelo.Membresia;
import edu.academik.telus.jpa.basico.modelo.Producto;
import edu.academik.telus.jpa.basico.modelo.ProductoMedida;
import edu.academik.telus.jpa.basico.modelo.ProductoMedidaPK;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

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

    public static void crearClienteYFactura(EntityManager entityManager) {

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Cliente cliente = new Cliente();
            cliente.setDireccion("Dirección Guatemala");
            cliente.setNit("1231023123");
            cliente.setNombre("Pedro Perez");

            entityManager.persist(cliente);

            Factura factura = new Factura();
            factura.setCliente(cliente);
            factura.setFecha(LocalDateTime.now());
            factura.setNumero("00001");

            entityManager.persist(factura);

            entityTransaction.commit();
        } catch (Exception ex) {
            entityTransaction.rollback();
            System.err.println(ex.getMessage());
        }
    }

    public static void consultarFactura(EntityManager entityManager) {

        Factura factura = entityManager.find(Factura.class, 1);

        //por alguna validación
        System.out.println(factura);

        Cliente cliente = factura.getCliente();

        System.out.println(cliente);
    }

    public static void consultarFactura2(EntityManager entityManager) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Factura> facturaQuery = builder.createQuery(Factura.class);

        Root<Factura> facturaRoot = facturaQuery.from(Factura.class);

        Join<Factura, Cliente> facturaFetch = (Join) facturaRoot.fetch("cliente");

        facturaQuery.where(
                builder.equal(facturaRoot.get("facturaId"), 1),
                builder.equal(facturaFetch.get("nit"), "")
        );

        Factura factura = entityManager.createQuery(facturaQuery).getSingleResult();

        //por alguna validación
        System.out.println(factura);

        Cliente cliente = factura.getCliente();

        System.out.println(cliente);
    }

    public static void hacerJoin(EntityManager entityManager) {

        /*
            select 
                f.numero
                , c.nombre
                , f.fecha
            from factura f 
            inner join cliente c on c.cliente_id = f.cliente_id
            where c.cliente_id = 1
                and f.fecha between ? and ?
         */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> facturaQuery = builder.createQuery(Tuple.class);

        Root<Factura> facturaRoot = facturaQuery.from(Factura.class);

        Join<Factura, Cliente> clienteJoin = facturaRoot.join("cliente");

        Join<Cliente, Membresia> membresiaJoin = clienteJoin.join("membresia", JoinType.LEFT);

        facturaQuery.multiselect(
                facturaRoot.get("numero"),
                clienteJoin.get("nombre"),
                facturaRoot.get("fecha")
        );

        facturaQuery.where(
                builder.equal(clienteJoin.get("clienteId"), 1),
                builder.between(facturaRoot.get("fecha").as(LocalDate.class), LocalDate.now(), LocalDate.now().plusDays(10)),
                builder.equal(builder.coalesce(membresiaJoin.get("codigo"), "123"), "123")
        ); // coalesce == nvl en Oracle

        List<Tuple> tupleList = entityManager.createQuery(facturaQuery).getResultList();

        tupleList.stream().forEach(System.out::println);

    }

    public static void ejecutarGroupBy(EntityManager entityManager) {

        /**
         * select cliente_id , count(factura_id) total from factura group by
         * cliente_id
         */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root<Factura> root = query.from(Factura.class);

        query.multiselect(
                root.get("clienteId").alias("clienteId"),
                builder.count(root.get("facturaId")).alias("total")
        );

        query.groupBy(root.get("clienteId"));

        List<Tuple> resultList = entityManager.createQuery(query).getResultList();

        resultList.stream().forEach(tuple -> {

            System.out.println(tuple.get("clienteId") + "  " + tuple.get("total"));
        });
    }

    public static void ejecutarExists(EntityManager entityManager) {

        /*
        select * from producto p
            where exists (
                select 1 from factura_detalle fd 
                where fd.producto_id = p.producto_id
            )

         */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> productoQuery = builder.createQuery(Producto.class);

        Root<Producto> productoRoot = productoQuery.from(Producto.class);

        //------Subquery---------------------
        Subquery facturaDetalleSubquery = builder.createQuery().subquery(Integer.class);

        facturaDetalleSubquery.select(builder.literal(1));

        Root<FacturaDetalle> facturaDetalleRoot = facturaDetalleSubquery.from(FacturaDetalle.class);

        facturaDetalleSubquery.where(
                builder.equal(facturaDetalleRoot.get("producto"), productoRoot.get("productoId"))
        );
        //-----------------------------------

        productoQuery.where(builder.exists(facturaDetalleSubquery));

        List<Producto> productoList = entityManager.createQuery(productoQuery).getResultList();

        productoList.stream().forEach(System.out::println);

    }

    public static void ejecutarSubquery(EntityManager entityManager) {

        /*
      
select 
    p.*
    , coalesce((select sum(fd.total) from factura_detalle fd  where fd.producto_id = p.producto_id), 0)
from producto p

         */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> productoQuery = builder.createQuery(Tuple.class);

        Root<Producto> productoRoot = productoQuery.from(Producto.class);

        //------Subquery---------------------
        Subquery<BigDecimal> facturaDetalleSubquery = builder.createQuery().subquery(BigDecimal.class);

        Root<FacturaDetalle> facturaDetalleRoot = facturaDetalleSubquery.from(FacturaDetalle.class);

        facturaDetalleSubquery.select(
                builder.sum(facturaDetalleRoot.get("total"))
        );

        facturaDetalleSubquery.where(
                builder.equal(facturaDetalleRoot.get("producto"), productoRoot.get("productoId"))
        );
        //-----------------------------------

        productoQuery.multiselect(
                productoRoot.get("productoId"),
                builder.coalesce(facturaDetalleSubquery.getSelection(), BigDecimal.ZERO)
        );

        List<Tuple> productoList = entityManager.createQuery(productoQuery).getResultList();

        productoList.stream().forEach(tuple -> {

            System.out.println(tuple.get(0) + "  " + tuple.get(1));
        });

    }

    public static void ejecutarSubquery2(EntityManager entityManager) {
        /*
select 
    p.*
    , coalesce((select sum(fd.total) from factura_detalle fd  where fd.producto_id = p.producto_id), 0)
from producto p

         */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> productoQuery = builder.createQuery(Tuple.class);

        Root<Producto> productoRoot = productoQuery.from(Producto.class);

        //------Subquery---------------------
        Subquery<BigDecimal> facturaDetalleSubquery = builder.createQuery().subquery(BigDecimal.class);

        Root<FacturaDetalle> facturaDetalleRoot = facturaDetalleSubquery.from(FacturaDetalle.class);

        facturaDetalleSubquery.select(
                builder.sum(facturaDetalleRoot.get("total"))
        );

        facturaDetalleSubquery.where(
                builder.equal(facturaDetalleRoot.get("producto"), productoRoot.get("productoId"))
        );
        //-----------------------------------

        productoQuery.multiselect(
                productoRoot.alias("producto"),
                builder.coalesce(facturaDetalleSubquery.getSelection(), BigDecimal.ZERO).alias("total")
        );

        List<Tuple> productoList = entityManager.createQuery(productoQuery).getResultList();

        productoList.stream().forEach(tuple -> {

            Producto producto = tuple.get("producto", Producto.class);

            BigDecimal total = tuple.get("total", BigDecimal.class);

            System.out.println("producto:  " + producto);
            System.out.println("total:  " + total);
        });

    }

    public static void ejecutarNativo(EntityManager entityManager) {

        String sqlString = "select \n"
                + "    i1.producto_id\n"
                + "    , p.nombre\n"
                + "    , i1.total\n"
                + "from (\n"
                + "    select \n"
                + "        fd.producto_id,\n"
                + "        sum(fd.total) as total\n"
                + "    from factura_detalle fd \n"
                + "    group by fd.producto_id\n"
                + ") as i1\n"
                + "inner join producto p on p.producto_id = i1.producto_id \n"
                + "where i1.producto_id = :productoId";

        List<Object[]> resultList = entityManager
                .createNativeQuery(sqlString)
                .setParameter("productoId", 2)
                .getResultList();

        resultList.stream().forEach(array -> {
            for (Object obj : array) {
                System.out.print(obj + "\t");
            }

            System.out.println();
        });

    }

    public static void ejecutarMerge2(EntityManager entityManager, Integer productoId, BigDecimal precio) {

        Producto producto = entityManager.find(Producto.class, productoId);

        producto.setPrecio(precio);

        try {
            entityManager.getTransaction().begin();

            //update
            entityManager.merge(producto);

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    public static void crearYAsociarMedida(EntityManager entityManager) {

        entityManager.getTransaction().begin();
        try {

            Medida medida = new Medida();
            medida.setDescripcion("Mi Nueva Medida con Embedded");

            entityManager.persist(medida);

            ProductoMedidaPK pk = new ProductoMedidaPK();
            pk.setMedidaId(medida.getMedidaId());
            pk.setProductoId(2);

            ProductoMedida productoMedida = new ProductoMedida();
            productoMedida.setExistencia(BigDecimal.TEN);
            productoMedida.setPk(pk);

            /*
            productoMedida.setMedidaId(medida.getMedidaId());
            productoMedida.setProductoId(2);*/
            entityManager.persist(productoMedida);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            entityManager.getTransaction().rollback();
        }

    }

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("MYSQL_PU")
                .createEntityManager();

        //ejectuarCriteria2(entityManager, 30000);
        //ejecutarCriteria6(entityManager);
        //ejecutarCriteria5(entityManager);
        //ejecutarRemove(entityManager);
        //crearClienteYFactura(entityManager);
        //consultarFactura(entityManager);
        //hacerJoin(entityManager);
        //consultarFactura2(entityManager);
        //ejecutarGroupBy(entityManager);
        //ejecutarNativo(entityManager);
        //ejecutarMerge2(entityManager, 2, BigDecimal.valueOf(52.12));
        //crearYAsociarMedida(entityManager);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> query = builder.createQuery(Producto.class); //select 

        query.from(Producto.class); // from productos

        List<Producto> productoList = entityManager.createQuery(query).getResultList();

        productoList.stream().forEach(System.out::println);
        
        
        productoList.stream().forEach(producto -> {
            boolean valido = producto.getProductoId() % 2 == 0;
            producto.setValido(valido);
        });
        
        productoList.stream()
                .filter(producto -> producto.isValido())
                .forEach(producto -> {
                    System.out.println(producto.getCodigoNombre());
                
                });
        
        /* ProductoMedidaPK pk = new ProductoMedidaPK();
        pk.setProductoId(2);
        pk.setMedidaId(1);

        ProductoMedida productoMedida = entityManager.find(ProductoMedida.class, pk);
        System.out.println(productoMedida);

        /*
        Producto producto = entityManager.find(Producto.class, 2);
        
        producto.getProductoMedidaList().stream().forEach(productoMedida -> {
            System.out.println(productoMedida);
        });*/ entityManager.close();
    }

}
