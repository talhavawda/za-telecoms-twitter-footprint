package groupproject.webinterface.model;

import org.neo4j.driver.*;


import java.util.HashMap;
import java.util.List;

/**
 * Todo - make this a singleton class (Apply the Singleton Design Pattern)
 *
 * Use an instance of this class to establish a  connection to the database
 * */
public class Database implements AutoCloseable{

    private static Database database;
    private Driver driver;

    private Database() {
        connect();
    }

    public void connect(){

        // make sure to change username to the name of a user profile you created in the DB,
        // BUT NOT "neo4j" as this is reserved user and will cause authentication error
        //same for password
        driver = GraphDatabase.driver("bolt://localhost:11003", AuthTokens.basic("java_application", "12345"));
    }

    /*DEPRECATED

    causes ResultConsumedException
    as there is a limited time neo4j driver allows us to work on a result

    use queryAsRecordList instead


    public Result query(String q){
        try ( Session session = driver.session() )
        {

            Query query = new Query(q);
            Result result = session.run(query);
            return result;

        }

    }

     */


    public List<Record> query(String templateKey, HashMap<String,Object> params){
        Database database = Database.instance();


        String template = QueryNexus.getQueryTemplate(templateKey);
        Query query = new Query(template,params);
        try ( Session session = driver.session() )
        {
            Result result = session.run(query);

            return result.list();
        }
    }
    public List<Record> query(String templateKey){
        Database database = Database.instance();
        String template = QueryNexus.getQueryTemplate(templateKey);
        Query query = new Query(template);
        try ( Session session = driver.session() )
        {
            Result result = session.run(query);

            return result.list();
        }
    }






    @Override
    public void close() throws Exception {
        driver.close();
    }


    /*
        Singleton application
     */
    public static Database instance() {
        if (database == null ) {
            database = new Database();
        }

        return database;
    }

}
