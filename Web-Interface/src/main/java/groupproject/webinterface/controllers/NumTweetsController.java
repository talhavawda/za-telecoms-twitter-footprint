package groupproject.webinterface.controllers;

import model.ModelBackend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class NumTweetsController {
    @RequestMapping(value="/num_tweets", method = RequestMethod.GET)
    public String numTweets(@RequestParam(value="company") String company, Model model){
        String res;// = (String)(server.serveRequest(new String[]{"numtweets", company}));
        res = "2";
        model.addAttribute("count",res);
        return "numtweets";
    }
}
