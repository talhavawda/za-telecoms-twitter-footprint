package groupproject.webinterface.model.sentiment;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SentimentAnalyzer {
    public static JSONArray classify(String data) {
        JSONArray arrayResult = null;
        try{
            MonkeyLearn ml = new MonkeyLearn("7c78656cdf1c6331842074f08fecb05f33b59e1d");
            String modelId = "cl_pi3C7JiL";
            String[] dataArray = {data};
            MonkeyLearnResponse res = ml.classifiers.classify(modelId, dataArray, false);
            arrayResult = res.arrayResult;

        }catch (MonkeyLearnException e){
            e.printStackTrace();
        }

        System.out.println(arrayResult);
        return arrayResult;
    }

}