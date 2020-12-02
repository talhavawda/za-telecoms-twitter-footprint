package groupproject.webinterface.model;

import java.util.HashMap;

public class QueryNexus {
    static HashMap<String, String> queryBodies;

    //if a better way to do this exists, please replace
    public static void initQueryNexus(){
        String[][] keysAndQueryBases =
                {
                        {"fullgraph", "match(n) return(n)"},
                        {"numtweets", "MATCH (company{username:'%s'})--(tweet) RETURN count(tweet)"},
                        {"proof", "MATCH (n:company) RETURN count(n) as count"}

                };

        queryBodies = new HashMap<>();
        for (String[] pair:keysAndQueryBases){
            queryBodies.put(pair[0],pair[1]);
        }
    }

    public static String get(String key){return queryBodies.get(key);}
}
