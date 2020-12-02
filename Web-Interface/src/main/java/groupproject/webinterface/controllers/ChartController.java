package groupproject.webinterface.controllers;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.QueryNexus;
import groupproject.webinterface.model.query.QueryBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.neo4j.driver.*;

import java.util.List;


@Controller
@CrossOrigin
public class ChartController {
    @RequestMapping(value="/chart", method = RequestMethod.GET)
    public String numTweets(Model viewTemplate){

        String[] companies = {"telkomza","mtnza","rainsouthafrica","afrihost"};
        int[] counts = new int[companies.length];

        for (int i=0; i<companies.length; i++) {
            QueryBody query = new QueryBody(QueryNexus.get("numtweetsbycompany") , new String[]{companies[i]});

            int result;

            try {
                Database database = Database.instance();

                String q = (query.getFullQuery());
                result = database.queryAsRecordList(q).get(0).get(0).asInt();


            }catch (Exception e){
                e.printStackTrace();
                result = -1;
            }

            counts[i] = result;
            viewTemplate.addAttribute(companies[i]+"name",companies[i]);
            viewTemplate.addAttribute(companies[i]+"count",result);
        }



        viewTemplate.addAttribute("test","iamatest");







        return "chart";
    }
}
