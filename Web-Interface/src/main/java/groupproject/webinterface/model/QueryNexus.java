package groupproject.webinterface.model;

import groupproject.webinterface.Abstract.DataModel;

import java.util.HashMap;

public class QueryNexus {
    static HashMap<String, String> queryBodies;

    //if a better way to do this exists, please replace
    public static void initQueryNexus(){
        String[][] keysAndQueryBases =
                {
                        {"fullgraph", "match(n) return(n)"},
                        {"numtweets", "match(n:%s) return n.tweets"}
                };

        queryBodies = new HashMap<>();
        for (String[] pair:keysAndQueryBases){
            queryBodies.put(pair[0],pair[1]);
        }
    }

    public static String get(String key){return queryBodies.get(key);}
}
