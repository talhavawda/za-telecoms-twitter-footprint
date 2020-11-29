package groupproject.webinterface.model;

/**
 * Use an instance of this class to establish a  connection to the database
 * */
public class DataBaseConnection {
    public void connect(Object[] args){
        //code the authentication and connection here
    }
    public Object[] query(String query){
        Object[] result = null;
        //code the query to db and return the result
        //dummy code: until bd querying is implemented:
        result = new Object[]{"2"};
        return result;
    }
}
