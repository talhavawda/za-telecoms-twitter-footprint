package GroupProject.WebInterface.controllers;

import GroupProject.WebInterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * handles requests for endpoint "/mentions"
 * */
@Controller
@CrossOrigin
public class MentionsCountController {
    /**
     * if URL Endpoint is "/mentions"
     * adds attributes to the model as described below:
     *      number of times each company was mentioned in the dataset
     * returns the html page mentions from templates folder, formatted by Thymeleaf
     * */
    @RequestMapping(value="/mentions", method = RequestMethod.GET)
    public String mentions(@RequestParam(value="company") String company, Model viewTemplate) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("company", company);

        viewTemplate.addAttribute("company",company);
        String before = CountHandler.getCountFromTemplateKey("tweets_mention_company_before",params);
        String after = CountHandler.getCountFromTemplateKey("tweets_mention_company_after",params);
        String total = Integer.toString(Integer.parseInt(before) + Integer.parseInt(after));

        viewTemplate.addAttribute("countbefore",before);
        viewTemplate.addAttribute("countafter",after);
        viewTemplate.addAttribute("count",total);

        return "mentions";
    }


}
