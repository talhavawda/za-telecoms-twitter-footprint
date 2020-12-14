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
        HashMap<String, Object> params = new HashMap<>();
        params.put("company", company);

        viewTemplate.addAttribute("company",company);
        String before = CountHandler.getCountFromTemplateKey("count_tweets_by_company_before",params);
        String after = CountHandler.getCountFromTemplateKey("count_tweets_by_company_after",params);
        String total = Integer.toString(Integer.parseInt(before) + Integer.parseInt(after));

        viewTemplate.addAttribute("countbefore",before);
        viewTemplate.addAttribute("countafter",after);
        viewTemplate.addAttribute("count",total);

        return "numtweets";
    }

    /*
    moved to CountHandler
    private String getCountFrosmTemplateKey(String templateKey,HashMap<String, Object> params){
        String result;
        try {
            List<Record> records = Database.instance().query(templateKey,params);
            result = records.get(0).get(0)+"";

        }
        catch (Exception e){
            e.printStackTrace();
            result = "something went wrong";
        }

        return result;
    }

     */
}
