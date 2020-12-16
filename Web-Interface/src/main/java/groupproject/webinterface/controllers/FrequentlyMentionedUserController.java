package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@CrossOrigin
public class FrequentlyMentionedUserController {
    @RequestMapping(value="/freqmentioneduser", method = RequestMethod.GET)
    public String hashtags(Model viewTemplate){

        templateKeytoAttributeAdded("frequent_user_mentions",viewTemplate);

        String queryText = Database.instance().textOfQuery("frequent_user_mentions");
        viewTemplate.addAttribute("queryText",queryText);

        return "freqmentioneduser";
    }



    //use subset id for code simplification ,despite redundancy
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
