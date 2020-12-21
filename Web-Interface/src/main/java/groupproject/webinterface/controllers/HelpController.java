package groupproject.webinterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * handles requests for endpoint "/queryhelp"
 * */
@Controller
public class HelpController {
    /**
    * if URL Endpoint is "/queryhelp"
    * returns the html page queyhelp from templates folder, formatted by Thymeleaf
    * */
    @GetMapping("/queryhelp")
    public String index(){
        return "queryhelp";
    }
}
