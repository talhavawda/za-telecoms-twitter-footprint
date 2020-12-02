package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.QueryNexus;
import groupproject.webinterface.model.query.QueryBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.neo4j.driver.*;



@Controller
@CrossOrigin
public class NumTweetsController {
    @RequestMapping(value="/numtweets", method = RequestMethod.GET)
    public String numTweets(@RequestParam(value="company") String company, Model viewTemplate){

        QueryBody query = new QueryBody(QueryNexus.get("numtweets") , new String[]{company});

        String result;

        try {
            Database database = Database.instance();


            String q = (query.getFullQuery());
            result = database.queryAsRecordList(q).get(0).get(0)+"";

        }catch (Exception e){
            e.printStackTrace();
            result = "something went wrong";
        }



        viewTemplate.addAttribute("company",company);
        viewTemplate.addAttribute("count",result);

        return "numtweets";
    }
}
