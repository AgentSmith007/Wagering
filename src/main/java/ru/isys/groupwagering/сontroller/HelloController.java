package ru.isys.groupwagering.сontroller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Just testing class
 */
@RestController
public class HelloController {

    @RequestMapping("/GET2")
    public String index() {
        return "Greetings from  Spring Boot!";
    }

}


