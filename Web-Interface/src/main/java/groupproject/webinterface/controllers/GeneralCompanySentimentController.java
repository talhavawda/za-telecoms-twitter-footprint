package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.sentiment.SentimentAnalyzer;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@Controller
@CrossOrigin
public class GeneralCompanySentimentController {
    @RequestMapping(value="/generalsentiment", method = RequestMethod.GET)
    public String generalSentiment(@RequestParam(value="company") String company, Model viewTemplate) {

        /*
        localhost:8080/generalsentiment?company=____
        */

        HashMap<String,Object> params = new HashMap<>();
        params.put("company",company);

        int[] befores = templateToSentimentCounts("company_general_sentiment_before",params);
        int[] afters = templateToSentimentCounts("company_general_sentiment_after",params);


        viewTemplate.addAttribute("beforePositives",befores[0]+"");
        viewTemplate.addAttribute("beforeNeutrals",befores[1]+"");
        viewTemplate.addAttribute("beforeNegatives",befores[2]+"");

        viewTemplate.addAttribute("afterPositives",afters[0]+"");
        viewTemplate.addAttribute("afterNeutrals",afters[1]+"");
        viewTemplate.addAttribute("afterNegatives",afters[2]+"");


        viewTemplate.addAttribute("company",company);

        return "generalsentiment";
    }


    private int[] templateToSentimentCounts(String templateKey, HashMap<String,Object> params){
        List<Record> Records = null;

        try{
            //get the tweets
            Records = Database.instance().query(templateKey,params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<String > Classifications = new ArrayList<>();
        for (Record record:Records) {
            String tweet = record.get(0).get("tweet").asString();
            String current = SentimentAnalyzer.classify(tweet);
            Classifications.add(current);
        }


        int Positives = Collections.frequency(Classifications,"Positive");
        int Neutrals = Collections.frequency(Classifications,"Neutral");
        int Negatives = Collections.frequency(Classifications,"Negative");

        int[] result = {Positives, Neutrals, Negatives};
        return result;
    }

}
