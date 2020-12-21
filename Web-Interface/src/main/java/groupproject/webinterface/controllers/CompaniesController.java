package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
/**
 * serves the endpoint "/companies"
 * */
@Controller
@CrossOrigin
public class CompaniesController {
    /**
     * if URL Endpoint is "/companies"
     * adds attributes to the model as described below:
     *     the username of each company
     * returns the html page companies from templates folder, formatted by Thymeleaf
     * */
    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public String companies(Model viewTemplate){
        List<Record> records = Database.instance().query("companies");


        String comp1 = records.get(0).get(0).get("username").asString();
        String comp2 = records.get(1).get(0).get("username").asString();
        String comp3 = records.get(2).get(0).get("username").asString();
        String comp4 = records.get(3).get(0).get("username").asString();


        viewTemplate.addAttribute("comp1",comp1);
        viewTemplate.addAttribute("comp2",comp2);
        viewTemplate.addAttribute("comp3",comp3);
        viewTemplate.addAttribute("comp4",comp4);

        return "companies";
    }
}
