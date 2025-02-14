package GroupProject.WebInterface.model;

import java.util.HashMap;

/**
 * singleton class that allows prototype queries to be passed to neo4j Query objects from a common source.
 * callers simply need to use the templatekey and will receive a parameterized or 0-parameter cypher string
 * neo4j query class can then add params to the query string
 * */

public class QueryNexus {

    private static  QueryNexus nexus;

    private QueryNexus(){
        initQueryNexus();
    }

    public static QueryNexus instance(){
        if (nexus ==null)
            nexus = new QueryNexus();

        return nexus;
    }


    //data
    static HashMap<String, String> queryTemplates;

    /**
     * set up the data structure to have a list of all queries we need
     * add the new query and template key to the array,
     * init will take care of adding them to the map for external use
     * */
    private static void initQueryNexus(){

        String[][] keysAndQueryBases =
                {
                        //with no params
                        {"companies", "MATCH (n:company) RETURN n"},

                        {"all", "match(n) return(n)"},

                                //in general , for all companies, users
                        {"tweets_before_lockdown", "match(t:tweet) WHERE t.date<date({year:2020, month:03, day:27}) return t"},
                        {"tweets_since_lockdown", "match(t:tweet) WHERE t.date>=date({year:2020, month:03, day:27}) return t"},

                        //with some params



                        //num tweets by companies
                        {"count_tweets_by_company", "MATCH (company{username:$company})--(t:tweet) RETURN count(t)"},
                        {"count_tweets_by_company_after", "MATCH (company{username:$company})--(t:tweet) where(t.date>=date({year:2020, month:03, day:27})) RETURN count(t)"},
                        {"count_tweets_by_company_before", "MATCH (company{username:$company})--(t:tweet) where(t.date<date({year:2020, month:03, day:27})) RETURN count(t)"},



                        //mentions of companies
                            // as tweets
                        {"tweets_mention_company_after","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date>=date({year:2020, month:03, day:27})) return t"},
                        {"tweets_mention_company_before","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date<date({year:2020, month:03, day:27})) return t"},
                            // as counts
                        {"tweets_mention_company_after","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date>=date({year:2020, month:03, day:27})) return count(t)"},
                        {"tweets_mention_company_before","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date<date({year:2020, month:03, day:27})) return count(t)"},


                        //mentions of users (top 10 most frequent)
                        {"frequent_user_mentions","Match(u:user)-[i:IS_MENTIONED_IN]-(:tweet) RETURN DISTINCT u.username as name ,count(i) as count ORDER BY count(i) DESC LIMIT 10"},



                        //sentiment
                        {"tweets_user_mentions_company", "match(user{username:$user})-[:TWEETED]-(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) return t"},
                        {"tweets_all_by_user", "match(user{username:$user})-[:TWEETED]-(t:tweet) return t"},

                        //general sentiment
                        //after lockdown
                        {"company_general_sentiment_after","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date>=date({year:2020, month:03, day:27})) return t"},
                        //before lockdown
                        {"company_general_sentiment_before","match(t:tweet)-[:IS_MENTIONED_IN]-(c:company{username:$company}) where(t.date<date({year:2020, month:03, day:27})) return t"},


                        //most frequent hashtags
                        //general
                        {"frequent_hashtags_all","Match(n:hashtag)-[t:USES_HASHTAG]-(:tweet) RETURN DISTINCT n.name as name ,count(t) as count ORDER BY count(t) DESC LIMIT 10"},
                        //user
                        {"frequent_hashtags_user","Match(n:hashtag)-[t:USES_HASHTAG]-(:tweet)-[:TWEETED]-(:user) RETURN DISTINCT n.name as name ,count(t) as count ORDER BY count(t) DESC LIMIT 10"},
                        //company
                        {"frequent_hashtags_company","Match(n:hashtag)-[t:USES_HASHTAG]-(:tweet)-[:TWEETED]-(:company) RETURN DISTINCT n.name as name ,count(t) as count ORDER BY count(t) DESC LIMIT 10"},

                };


        queryTemplates = new HashMap<>();

        for (String[] pair:keysAndQueryBases){
            queryTemplates.put(pair[0],pair[1]);
        }
    }


    /**
     * this method lets external objects retrieve a cypher query if they have the key for the template
     * */
    public String getQueryTemplate(String key){return queryTemplates.get(key);}
}
