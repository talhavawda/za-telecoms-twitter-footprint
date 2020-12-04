package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.SentimentIdentifier;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@CrossOrigin
public class UserSentimentToCompanyController {
    @RequestMapping(value="/usersentiment", method = RequestMethod.GET)
    public String userSentiment(@RequestParam(value = "user") String user, @RequestParam(value="company") String company, Model viewTemplate) {

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




            List<Object> sentiments = new ArrayList<>();
            for (Record record:records){
                Object sentiment = SentimentIdentifier.instance().getSentiment(record.get("tweet").asString());

                sentiments.add(sentiment);
            }

            Object sentimentSummary = SentimentIdentifier.instance().SummariseSentiments(sentiments);

            viewTemplate.addAttribute("Sentiment",sentimentSummary);




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println(records.get(0).get(0).get("tweet"));

        viewTemplate.addAttribute("data",records.get(0).get("t").get("tweet"));
        viewTemplate.addAttribute("company",company);
        viewTemplate.addAttribute("user",user);





        return "usersentiment";
    }


    private List<String> stripTweetText(List<Object> data){
        return null;
    }
}
