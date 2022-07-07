package imageio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.util.Map;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.05.10
 */
public class Main {

    public static void main(String[] args) {
        String body = "{\"dicSearch\":\"巴巴爸爸\",\"lists\":[\"aaa\",\"bbb\"],\"obj\":{\"key\":\"name\",\"value\":[{\"aaa\":\"bbb\"},\"bbbb\"]}}";
        boolean b = checkSpecials(body, false);
        System.out.println(b);


    }

    public static boolean checkSpecials(String body,boolean isSign) {
        if (isSign) return true;
        boolean isJson = FastJsonUtils.isJSON(body);
        if (!isJson) {
            if ("巴巴爸爸1".equals(body)) return true;
            return false;
        }
        Object parse = JSON.parse(body);
        if (parse instanceof JSONObject) {
            JSONObject jsonObject = FastJsonUtils.strToJson(body);
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object value = entry.getValue();
                if (null == value) continue;
                boolean b = checkSpecials(value + "", false);
                if (b) return b;
            }
        }
        if (parse instanceof JSONArray) {
            Object[] jsonArray = FastJsonUtils.toArray(body);
            if (0 < jsonArray.length){
                for (Object o: jsonArray) {
                    boolean b = checkSpecials(o + "", false);
                    if (b) return b;
                }
            }
        }
        return false;
    }
}
