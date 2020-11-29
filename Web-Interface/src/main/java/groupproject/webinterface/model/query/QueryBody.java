package groupproject.webinterface.model.query;

public class QueryBody {
    String baseQuery;
    Object[] params;

    public QueryBody(String baseQuery) {
        this.baseQuery = baseQuery;
    }

    public QueryBody(String baseQuery, Object[] params) {
        this.baseQuery = baseQuery;
        this.params = params;
    }


    public void setBaseQuery(String baseQuery) {
        this.baseQuery = baseQuery;
    }
    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getFullQuery(){
        return String.format(baseQuery, params);
    }
}
