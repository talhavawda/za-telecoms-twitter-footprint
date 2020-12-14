package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@CrossOrigin
public class SimpleQueryController {
    @RequestMapping(value="/data", method = RequestMethod.GET)
    public String simple(Model viewTemplate){
        List<Record> records = Database.instance().query("count_companies");


        String result = records.get(0).get(0)+"";

        viewTemplate.addAttribute("data",result);

        return "SimpleQuery";
    }
}
