package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.dom4j.*;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class FastJsonUtils {

    private static SerializeConfig config = null;


    static {
        config = new SerializeConfig();
    }

    /**
     * 解决 CDATA 问题
     */
    private static Pattern pattern = Pattern.compile("[<>&\"',]");

    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    /**
     * 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 转换成字符串 ,带有过滤器
     *
     * @param object
     * @return
     */
    public static String toJSONWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }


    /**
     * 转换成字符串 ,带有过滤器
     *
     * @param object
     * @return
     */
    public static String toJSONToNull(Object object) {
        SerializerFeature[] features = {
                SerializerFeature.WriteMapNullValue, // 输出空置字段
        };
        return JSON.toJSONString(object, config, features);
    }


    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static JSONObject beanToJSONObject(Map m) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(m);
        return jsonObject;
    }


    /**
     * 转成bean对象
     *
     * @param text
     * @return
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    /**
     * 转成具体的泛型bean对象
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * list 转json
     *
     * @param list
     * @return
     */
    public static String listToJson(List list) {
        String str = JSON.toJSONString(list);
        return str;
    }

    /**
     * 转换为数组Array
     *
     * @param text
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text) {
        return JSON.parseArray(text, (Class<Object>) null).toArray();
    }

    /**
     * 转换为具体的泛型数组Array
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为具体的泛型List
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     * 将string转化为JSONObject
     *
     * @param str
     * @return
     */
    public static JSONObject strToJson(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject;
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    public static Map stringToMap(String s) {
        Map m = JSONObject.parseObject(s, Feature.IgnoreNotMatch);
        return m;
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static String mapToString(Map m) {
        String s = JSONObject.toJSONString(m,SerializerFeature.WriteMapNullValue);
        return s;
    }

    /**
     * JSONObject
     *
     * @return
     */
    public static JSONObject mapToJSONObject(Map itemMap) {
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
        return itemJSONObj;
    }


    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 实现功能描述：判断字符串是否为JSON
     * @param:str
     * @author: 任传君
     * @date: 2022-06-08 15:34
     */
    public static boolean isJSON(String str) {
        boolean result = false;
        try {
            Object obj=JSON.parse(str);
            result = true;
        } catch (Exception e) {
            result=false;
        }
        return result;
    }

    /**
     * String 转 org.dom4j.Document
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document strToDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject documentToJSONObject(String xml) throws DocumentException {
        return elementToJSONObject(strToDocument(xml).getRootElement());
    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     *
     * @param node
     * @return
     */
    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
        for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {// 遍历所有一级子节点
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }



    /**
     * 根据at号获取所有的属性list
     */
    public static JSONObject getAttribute(JSONObject jdata) {
        JSONObject rdata = new JSONObject();
        try {
            Set<Map.Entry<String, Object>> setdata = jdata.entrySet();
            for (Iterator<Map.Entry<String, Object>> it = setdata.iterator(); it.hasNext();) {
                Map.Entry<String, Object> en = it.next();
                if(en.getKey().startsWith("@")){
                    rdata.put(en.getKey().substring(1),escape((String) en.getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rdata;
    }

    private static String escape(String string) {
        return pattern.matcher(string).find() ? "<![CDATA[" + string + "]]>" : string;
    }


    /**
     * Json to xmlstr 核心循环方法
     */
    public static String json2Xmlstr(JSONObject jObj, StringBuffer buffer) {
        Set<Map.Entry<String, Object>> se = jObj.entrySet();

        for (Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext();) {
            Map.Entry<String, Object> en = it.next();
            if (null == en.getValue()) {
                if (!en.getKey().startsWith("@")) {//@号的是属性，在前面已经处理过，这里就不处理了
                    buffer.append("<" + en.getKey() + ">");
                    buffer.append("</" + en.getKey() + ">");
                }
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
                JSONObject jo = jObj.getJSONObject(en.getKey());
                buffer.append("<" + en.getKey());

                //处理xml标签的属性值
                JSONObject attrlist = getAttribute(jo);
                Set<Map.Entry<String, Object>> attr = attrlist.entrySet();
                for (Iterator<Map.Entry<String, Object>> mt = attr.iterator(); mt.hasNext();) {
                    Map.Entry<String, Object> amap = mt.next();
                    buffer.append(" " + amap.getKey() + "=\""+escape((String) amap.getValue()) + "\"");
                }
                buffer.append(">");
                if(jo.containsKey(en.getKey())){
                    //标签直接有值的话，直接赋值
                    buffer.append(jo.getString(en.getKey()));
                }else{
                    json2Xmlstr(jo, buffer);
                }
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey());
                    JSONObject jsonobject = jarray.getJSONObject(i);

                    //处理xml标签的属性值
                    JSONObject attrlist = getAttribute(jsonobject);
                    Set<Map.Entry<String, Object>> attr = attrlist.entrySet();
                    for (Iterator<Map.Entry<String, Object>> mt = attr.iterator(); mt.hasNext();) {
                        Map.Entry<String, Object> amap = mt.next();
                        buffer.append(" " + amap.getKey() + "=\""+escape((String) amap.getValue()) + "\"");
                    }
                    buffer.append(">");

                    json2Xmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">");
                }
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                if(!en.getKey().startsWith("@")){//@号的是属性，在前面已经处理过，这里就不处理了
                    buffer.append("<" + en.getKey() + ">" + escape((String) en.getValue()));
                    buffer.append("</" + en.getKey() + ">");
                }
            }else if (en.getValue().getClass().getName().equals("java.lang.Integer")) {
                if(!en.getKey().startsWith("@")){//@号的是属性，在前面已经处理过，这里就不处理了
                    buffer.append("<" + en.getKey() + ">" + escape(en.getValue()+""));
                    buffer.append("</" + en.getKey() + ">");
                }
            }
        }
        return buffer.toString();
    }

    /**
     * json转xml调用
     *
     * @param json
     * @return java.lang.String
     */
    public static String json2Xml(String json) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><body>");
            JSONObject jObj = JSON.parseObject(json);
            json2Xmlstr(jObj, buffer);
            buffer.append("</body>");
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
