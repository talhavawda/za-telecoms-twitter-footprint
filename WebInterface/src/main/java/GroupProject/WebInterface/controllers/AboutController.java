package GroupProject.WebInterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * serves the /"about"
 * */
@Controller
public class AboutController {
    /**
     * if URL Endpoint is "/about"
     * returns the html page about from templates folder
     * */
    @GetMapping("/about")
    public String choose(){
        return "about";
    }
}
