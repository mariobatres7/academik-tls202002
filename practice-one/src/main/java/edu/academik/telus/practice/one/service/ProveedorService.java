package edu.academik.telus.practice.one.service;

import edu.academik.telus.practice.one.model.Proveedor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mario Batres
 */
@Service
public class ProveedorService {

    public static List<Proveedor> proveedorList = new ArrayList<>();

    public List<Proveedor> buscarEliminados() {

        /*
        List<Proveedor> proveedoresEliminados = new ArrayList<>();

        for (Proveedor proveedor : proveedorList) {
            if (proveedor.isEliminado()) {
                proveedoresEliminados.add(proveedor);
            }
        }*/

        return proveedorList.stream().filter(proveedor -> proveedor.isEliminado()).collect(Collectors.toList());

    }
    
    
    public List<Proveedor> buscarNoEliminados() {
        return proveedorList.stream().filter(proveedor -> !proveedor.isEliminado()).collect(Collectors.toList());

    }

    public boolean agregarProveedor(Proveedor proveedor) {

        boolean existe = proveedorList.stream().anyMatch(p -> p.equals(proveedor));

        if (!existe) {
            proveedorList.add(proveedor);
        }

        return !existe;

    }

    public Proveedor buscarProveedor(String codigo) {
        /*
        for (Proveedor p : proveedorList){
            if (p.getCodigo().equalsIgnoreCase(codigo)){
                return p;
            }
        }
        return null;*/

        return proveedorList.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Proveedor> buscarProveedorList() {
        return proveedorList;
    }

}
