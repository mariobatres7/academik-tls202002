package edu.academik.telus.springweb.mvc.controller;

import edu.academik.telus.springweb.mvc.dao.ProductoDao;
import edu.academik.telus.springweb.mvc.model.SignUp;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mario Batres
 */
@Controller
public class IndexController {

    @Resource
    private ProductoDao productoDao;

    @GetMapping("/index")
    public String get(Model model) {

        System.out.println(this.getClass().getName());

        var signUp = new SignUp();

        signUp.setUsername("default");

        model.addAttribute("message", "Bienvenido a Index");
        model.addAttribute("signUp", signUp);

        var productList = this.productoDao.list();

        model.addAttribute("productList", productList);

        return "index";
    }

    @PostMapping("/index")
    public String post(Model model, @ModelAttribute("signUp") SignUp signUp) {

        System.out.println(signUp);

        if (signUp.getUsername().equalsIgnoreCase("pablo") && signUp.getPassword().equalsIgnoreCase("123")) {
            return "sample";
            //request.getDispatcher()
            //response.sendRedirect   "redirect:sample";
        }

        model.addAttribute("signUp", signUp);
        model.addAttribute("message", "Usuario no encontrado");

        return "index";
    }

}
