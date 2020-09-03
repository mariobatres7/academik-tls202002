package edu.academik.telus.practice.one.controller.proveedor;

import edu.academik.telus.practice.one.model.Proveedor;
import edu.academik.telus.practice.one.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mario Batres
 */
@Controller
public class ProveedorController {

    @GetMapping("/proveedor")
    public String get(Model model) {
        
        model.addAttribute("proveedor", new Proveedor());

        return "proveedor/crear-proveedor";
    }

    @GetMapping("/proveedor/codigo/{codigo}")
    public String getByCodigo(Model model, @PathVariable("codigo") String codigo) {
        
        var proveedor = ProveedorService.buscarProveedor(codigo);
        
        model.addAttribute("proveedor", proveedor);

        return "proveedor/editar-proveedor";
    }

    @PostMapping("/proveedor/crear")
    public String create(Model model, @ModelAttribute("proveedor") Proveedor proveedor) {

        boolean agregado = ProveedorService.agregarProveedor(proveedor);

        if (agregado) {
            model.addAttribute("proveedor", new Proveedor());
            model.addAttribute("mensaje", "Proveedor agregado satisfactoriamente");
        } else {
            model.addAttribute("mensaje", "¡¡Proveedor ya existe!!");
        }

        return "proveedor/crear-proveedor";
    }
    
    
    
    @PostMapping("/proveedor/editar")
    public String edit(Model model, @ModelAttribute("proveedor") Proveedor proveedor) {

       
        return "proveedor/editar-proveedor";
    }

}
