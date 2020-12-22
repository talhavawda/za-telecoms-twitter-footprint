package GroupProject.WebInterface.model;

import org.neo4j.driver.*;


import java.util.HashMap;
import java.util.List;

/**
 * Use an instance of this class to establish a  connection to the database
 *
 * This class applies the Singleton Design Pattern
 * */
public class Database implements AutoCloseable{

    private static Database database = null;
    private Driver driver;

    private Database() {
        connect();
    }


    /*
	Singleton DP application
 */
    public static Database instance() {
        if (database == null ) {
            database = new Database();
        }

        return database;
    }


    public void connect(){

        // make sure to change username to the name of a user profile you created in the DB,
        // BUT NOT "neo4j" as this is reserved user and will cause authentication error
        //same for password
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("java_application", "12345"));
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

    public String textOfQuery(String templateKey, HashMap<String,Object> params){
        String template = QueryNexus.instance().getQueryTemplate(templateKey);
        Query query = new Query(template,params);
        return query.text();
    }

    public String textOfQuery(String templateKey){
        String template = QueryNexus.instance().getQueryTemplate(templateKey);
        Query query = new Query(template);
        return query.text();
    }




    public List<Record> query(String templateKey, HashMap<String,Object> params){
        Database database = Database.instance();


        String template = QueryNexus.instance().getQueryTemplate(templateKey);
        Query query = new Query(template,params);
        try ( Session session = driver.session() )
        {
            Result result = session.run(query);

            return result.list();
        }
    }
    public List<Record> query(String templateKey){
        Database database = Database.instance();
        String template = QueryNexus.instance().getQueryTemplate(templateKey);
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




}
