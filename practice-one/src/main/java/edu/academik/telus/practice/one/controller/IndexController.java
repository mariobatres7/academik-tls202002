package edu.academik.telus.practice.one.controller;

import edu.academik.telus.practice.one.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Mario Batres
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String get(Model model) {

        model.addAttribute("proveedorList", ProveedorService.proveedorList);

        return "index";
    }

}
