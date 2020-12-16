package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.sentiment.SentimentEngine;
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
public class UserSentimentToCompanyController {
    @RequestMapping(value="/usersentiment", method = RequestMethod.GET)
    public String userSentiment(@RequestParam(value = "user") String user, @RequestParam(value="company") String company, @RequestParam(value = "sample") int sample, Model viewTemplate) {

        /*
        localhost:8080/usersentiment?user=____&company=____
        */
        List<Record> records = null;
        try{

            //get the tweets
            HashMap<String,Object> params = new HashMap<>();
            params.put("user",user);
            params.put("company",company);

            records = Database.instance().query("tweets_user_mentions_company",params);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //sentiment analysis

        ArrayList<String > tweetTexts = new ArrayList<>();

        for (Record record:records) {
            String tweet = record.get(0).get("tweet").asString();
            tweetTexts.add(tweet);
        }

        SentimentEngine engine = new SentimentEngine();
        ArrayList<String> classifications = engine.concatAndJudgeStrings(tweetTexts, sample);



        int Positives = Collections.frequency(classifications,"Positive");
        int Neutrals = Collections.frequency(classifications,"Neutral");
        int Negatives = Collections.frequency(classifications,"Negative");




        viewTemplate.addAttribute("positives",Positives+"");
        viewTemplate.addAttribute("neutrals",Neutrals+"");
        viewTemplate.addAttribute("negatives",Negatives+"");



        viewTemplate.addAttribute("company",company);
        viewTemplate.addAttribute("user",user);





        return "usersentiment";
    }



}
