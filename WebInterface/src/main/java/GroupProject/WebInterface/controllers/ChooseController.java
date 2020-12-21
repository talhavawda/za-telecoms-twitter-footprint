package GroupProject.WebInterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * serves the endpoint "/choose"
 * */
@Controller
public class ChooseController {

    /**
     * if URL Endpoint is "/choose"

     * returns the html page ChooseQueries from templates folder
     * */
    @GetMapping("/choose")
    public String choose(){
        return "ChooseQueries";
    }
}
