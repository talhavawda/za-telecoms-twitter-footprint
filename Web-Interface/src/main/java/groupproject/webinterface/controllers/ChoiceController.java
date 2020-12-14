package groupproject.webinterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ChoiceController {
    @GetMapping("/choose")
    public String choose(){
        return "ChooseQueries";
    }
}
