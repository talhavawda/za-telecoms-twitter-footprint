package GroupProject.WebInterface.controllers;

import GroupProject.WebInterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * serves the  /freqmentionedusers endpoint
 */
@Controller
@CrossOrigin
public class FrequentlyMentionedUserController {
    /**
     * if URL Endpoint is "/freqmentioneduser"
     * adds attributes to the model  as described below:
     *      top ten most mentioned users
     * returns the html page freqmentioneduser from templates folder, formatted by Thymeleaf
     * */
    @RequestMapping(value="/freqmentioneduser", method = RequestMethod.GET)
    public String hashtags(Model viewTemplate){

        templateKeytoAttributeAdded("frequent_user_mentions",viewTemplate);


        //to allow visualisation
        String queryText = Database.instance().textOfQuery("frequent_user_mentions");
        viewTemplate.addAttribute("queryText",queryText);



        return "freqmentioneduser";
    }



    /**
     * takes a template key for a query and a Spring Model (front end model)
     * performs the query and adds each user returned to the attributtes of the Model.
     */
    void templateKeytoAttributeAdded(String templateKey, Model viewTemplate ){

        List<Record> records = Database.instance().query(templateKey);

        for (int i = 0; i < records.size(); i++) {
            String text = records.get(i).get("name").asString();
            String count = records.get(i).get("count") +"";

            viewTemplate.addAttribute("text_place_"+i,text);
            viewTemplate.addAttribute("count_place_"+i,count);

        }
    }

}
