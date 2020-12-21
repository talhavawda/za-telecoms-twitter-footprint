package GroupProject.WebInterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * handles requests for  endpoint "/"
 * */
@Controller
public class IndexController {
    /**
     * if URL Endpoint is "/"
     * returns the html page index from templates folder
     * */
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
