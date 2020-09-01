package edu.academik.telus.springweb.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Mario Batres
 */
@Controller
public class SampleController {

    @GetMapping("/sample")
    public String get() {
        System.out.println(this.getClass().getName());
        return "sample";
    }
}
