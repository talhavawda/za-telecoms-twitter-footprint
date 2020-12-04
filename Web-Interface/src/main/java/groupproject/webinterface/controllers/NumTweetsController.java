package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;


@Controller
@CrossOrigin
public class NumTweetsController {
    @RequestMapping(value="/numtweets", method = RequestMethod.GET)
    public String numTweets(@RequestParam(value="company") String company, Model viewTemplate){

        String result;
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("company", company);
            List<Record> records = Database.instance().query("count_tweets_by_company",params);
            result = records.get(0).get(0)+"";

        }
        catch (Exception e){
            e.printStackTrace();
            result = "something went wrong";
        }



        viewTemplate.addAttribute("company",company);
        viewTemplate.addAttribute("count",result);

        return "numtweets";
    }
}
