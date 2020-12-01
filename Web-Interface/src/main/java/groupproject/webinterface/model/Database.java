package groupproject.webinterface.model;

import org.neo4j.driver.*;

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
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345"));

    }

    public Result query(String q){
        try ( Session session = driver.session() )
        {
            Query query = new Query(q);
            Result result = session.run(query);
            return result;

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
