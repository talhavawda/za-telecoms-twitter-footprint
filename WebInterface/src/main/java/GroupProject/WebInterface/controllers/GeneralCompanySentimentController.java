package GroupProject.WebInterface.controllers;

import GroupProject.WebInterface.model.Database;
import GroupProject.WebInterface.model.sentiment.SentimentEngine;
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

/**
 * handles requests for endpoint "/generalsentiment"
 * */
@Controller
@CrossOrigin
public class GeneralCompanySentimentController {
    /**
     * if URL Endpoint is "/generalsentiment"
     * makes use of the url parameter ?company
     * adds attributes to the model as described below:
     *      number of instances of positive, neutral and negative sentiments toward the company before lockdown began
     *      number of instances of positive, neutral and negative sentiments toward the company after lockdown began
     *      number of instances of positive, neutral and negative sentiments toward the company overall
     * returns the html page generalsentiment from templates folder, formatted by Thymeleaf
     * */
    @RequestMapping(value="/generalsentiment", method = RequestMethod.GET)
    public String generalSentiment(@RequestParam(value="company") String company, @RequestParam(value="sample") int sample, Model viewTemplate) {

        /*
        localhost:8080/generalsentiment?company=____
        */

        HashMap<String,Object> params = new HashMap<>();
        params.put("company",company);


        System.out.println("analysing tweets before lockdown");
        int[] befores = templateToSentimentCounts("company_general_sentiment_before",params, sample);

        System.out.println("analysing tweets before after");
        int[] afters = templateToSentimentCounts("company_general_sentiment_after",params, sample);


        viewTemplate.addAttribute("beforePositives",befores[0]+"");
        viewTemplate.addAttribute("beforeNeutrals",befores[1]+"");
        viewTemplate.addAttribute("beforeNegatives",befores[2]+"");

        viewTemplate.addAttribute("afterPositives",afters[0]+"");
        viewTemplate.addAttribute("afterNeutrals",afters[1]+"");
        viewTemplate.addAttribute("afterNegatives",afters[2]+"");




        viewTemplate.addAttribute("company",company);

        return "generalsentiment";
    }

/**
 * takes the template key and a map of params to form a query in database.
 * this template will be a call for tweets mentioning the company before or after the lockdown
 * returns an int count of positive, neutral and negative tweets
 * */
    private int[] templateToSentimentCounts(String templateKey, HashMap<String,Object> params, int sample){
        List<Record> Records = null;

        try{
            //get the tweets
            Records = Database.instance().query(templateKey,params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        //put each tweet as a string into a list
        ArrayList<String > tweetTexts = new ArrayList<>();
        for (Record record:Records) {
            String tweet = record.get(0).get("tweet").asString();
            tweetTexts.add(tweet);
        }


        SentimentEngine engine = new SentimentEngine();
        ArrayList<String> Classifications = engine.concatAndJudgeStrings(tweetTexts, sample);


        int Positives = Collections.frequency(Classifications,"Positive");
        int Neutrals = Collections.frequency(Classifications,"Neutral");
        int Negatives = Collections.frequency(Classifications,"Negative");

        return new int[]{Positives, Neutrals, Negatives};
    }



}
