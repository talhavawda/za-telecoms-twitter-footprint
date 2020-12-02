package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.QueryNexus;
import groupproject.webinterface.model.query.QueryBody;
import org.neo4j.driver.summary.ResultSummary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
public class SimpleQueryController {
    @RequestMapping(value="/data", method = RequestMethod.GET)
    public String simple(Model viewTemplate){

        QueryBody query = new QueryBody(QueryNexus.get("proof") , new String[]{""});


        Database database = Database.instance();

        String q = (query.getFullQuery());

        String result = database.queryAsRecordList(q).get(0).get(0)+"";

        viewTemplate.addAttribute("data",result);

        return "SimpleQuery";
    }
}
