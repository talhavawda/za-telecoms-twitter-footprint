package groupproject.webinterface.model;

import java.util.List;

public class SentimentIdentifier {
    private static SentimentIdentifier sentimentIdentifier;

    public Object getSentiment(String text){
        //imported code for sentiment analysis goes here
        return  null;
    }


    public Object SummariseSentiments(List<Object>sentiments){
        //averaging or similar action done here
        return null;
    }





    /*
    Singleton application
 */
    public static SentimentIdentifier instance() {
        if (sentimentIdentifier == null ) {
            sentimentIdentifier = new SentimentIdentifier();
        }

        return sentimentIdentifier;
    }
}
