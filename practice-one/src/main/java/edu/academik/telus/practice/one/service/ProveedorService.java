package edu.academik.telus.practice.one.service;

import edu.academik.telus.practice.one.model.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario Batres
 */
public class ProveedorService {

    public static List<Proveedor> proveedorList = new ArrayList<>();

    public static boolean agregarProveedor(Proveedor proveedor) {

        boolean existe = proveedorList.stream().anyMatch(p -> p.equals(proveedor));

        if (!existe) {
            proveedorList.add(proveedor);
        }

        return !existe;

    }

    public static Proveedor buscarProveedor(String codigo) {
        return proveedorList.stream().filter(p -> p.getCodigo().equalsIgnoreCase(codigo)).findFirst().orElse(null);
    }

}
