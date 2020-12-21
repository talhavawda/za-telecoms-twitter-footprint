package groupproject.webinterface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalysisController {

    @GetMapping("/Analysis")
    public String choose(){
        return "Analysis";
    }
}

