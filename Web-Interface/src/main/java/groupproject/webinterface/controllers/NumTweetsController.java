package groupproject.webinterface.controllers;

import groupproject.webinterface.model.DataBaseConnection;
import groupproject.webinterface.model.QueryNexus;
import groupproject.webinterface.model.query.QueryBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@CrossOrigin
public class NumTweetsController {
    @RequestMapping(value="/numtweets", method = RequestMethod.GET)
    public String numTweets(@RequestParam(value="company") String company, Model viewTemplate){

        QueryBody query = new QueryBody(QueryNexus.get("numtweets") , new String[]{company});


        DataBaseConnection dbx = new DataBaseConnection();
        //find a way to securely store connection string such that it can be accessed here
        //dbx.connect();

        String res = String.valueOf(dbx.query(query.getFullQuery())[0]);

        System.out.println(res+" IS THE ANSWER");

        viewTemplate.addAttribute("company",company);
        viewTemplate.addAttribute("count",res);

        return "numtweets";
    }
}
