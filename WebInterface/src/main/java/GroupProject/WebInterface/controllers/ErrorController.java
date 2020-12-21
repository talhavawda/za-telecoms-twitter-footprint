package GroupProject.WebInterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * serves the error page
 * */
@Controller
public class ErrorController {
    /**
     * if URL Endpoint is "/error"
     * or if some error occurs serving another endpoint
     * returns the html page error from templates folder
     * */
    @GetMapping("/error")
    public String choose(){
        return "error";
    }
}
