package groupproject.webinterface.model;

import java.util.HashMap;

/**
 * queries handled already:
 *      get all nodes in graph
 *
 *      get number of companies
 *
 *      get all tweet nodes where the tweet was submitted before lockdown began
 *
 *      get all tweet nodes where the tweet was submitted after lockdown began
 *
 *      get count of the tweet nodes where a specific company tweeted it
 *
 *      get all tweet nodes where a user submitted it and mentioned a company
 * */

public class QueryNexus {
    static HashMap<String, String> queryTemplates;

    //if a better way to do this exists, please replace
    public static void initQueryNexus(){
        String[][] keysAndQueryBases =
                {
                        //with no params
                        {"count_companies", "MATCH (n:company) RETURN count(n) as count"},

                        {"all", "match(n) return(n)"},

                        {"tweets_before_lockdown", "match(t:tweet) WHERE t.date<date({year:2020, month:03, day:27}) return t"},

                        {"tweets_since_lockdown", "match(t:tweet) WHERE t.date>=date({year:2020, month:03, day:27}) return t"},

                        //with some params
                        {"count_tweets_by_company", "MATCH (company{username:$company})--(tweet) RETURN count(tweet)"},

                        {"tweets_user_mentions_company", "match(user{username:$user})-[:TWEETED]-(t:tweet)-[:IS_MENTIONED_IN]-(u:user{username:$company}) return t"},

                };
        queryTemplates = new HashMap<>();
        for (String[] pair:keysAndQueryBases){
            queryTemplates.put(pair[0],pair[1]);
        }
    }

    public static String getQueryTemplate(String key){return queryTemplates.get(key);}
}
