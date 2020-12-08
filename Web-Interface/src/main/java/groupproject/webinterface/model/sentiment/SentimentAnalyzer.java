package groupproject.webinterface.model.sentiment;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnResponse;
import com.monkeylearn.MonkeyLearnException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SentimentAnalyzer {
    public static String classify(String data) {
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

        return finalClassifcation(arrayResult);
    }

    public static String finalClassifcation(JSONArray array) {
        System.out.println(array);

        JSONArray firstLayer = (JSONArray)(array.get(0));
        System.out.println(firstLayer);

        JSONArray secondLayer = (JSONArray)(firstLayer.get(0));
        System.out.println(secondLayer);

        JSONObject thirdLayer = (JSONObject)(secondLayer.get(0));
        System.out.println(thirdLayer);

        String tag = (String)(thirdLayer.get("label"));
        System.out.println(tag);

        return  tag;

    }
}