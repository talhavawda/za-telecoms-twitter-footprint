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
public class HashTagFrequencyController {
    @RequestMapping(value="/hashtags", method = RequestMethod.GET)
    public String hashtags(Model viewTemplate){

        templateKeytoAttributeAdded("frequent_hashtags_all",viewTemplate,"all");
        templateKeytoAttributeAdded("frequent_hashtags_user",viewTemplate,"user");
        templateKeytoAttributeAdded("frequent_hashtags_company",viewTemplate,"company");

        return "hashtags";
    }

    //use subset id for code simplification ,despite redundancy
    void templateKeytoAttributeAdded(String templateKey, Model viewTemplate, String subsetID){
        assert (subsetID.equals("all")||subsetID.equals("user") ||subsetID.equals("company"));


        List<Record> records = Database.instance().query(templateKey);

        for (int i = 0; i < records.size(); i++) {
            String text = records.get(i).get("name").asString();
            String count = records.get(i).get("count") +"";

            viewTemplate.addAttribute("text_"+subsetID+"_place_"+i,text);
            viewTemplate.addAttribute("count_"+subsetID+"_place_"+i,count);

        }
    }

}
