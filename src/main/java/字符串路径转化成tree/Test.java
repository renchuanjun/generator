package 字符串路径转化成tree;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentException;

import java.util.*;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.09.14
 */
public class Test {


    public static void main(String[] args) throws DocumentException {
       /* String context = "[{\"name\":\"正卷\",\"children\":[{\"name\":\"原告提交证据材料\",\"children\":[{\"name\":\"身份证材料\",\"categoryCode\":null,\"categoryType\":\"pdf\",\"fileId\":\"644f1fbc-59b9-4e47-a4f6-74dd329f3735\"},{\"name\":\"委托材料\",\"categoryType\":\"pdf\",\"fileId\":\"644f1fbc-59b9-4e47-a4f6-74dd329f3735\"},{\"name\":\"证据材料\",\"categoryType\":\"pdf\",\"fileId\":\"644f1fbc-59b9-4e47-a4f6-74dd329f3735\"}]}]},{\"name\":\"副卷\",\"children\":[{\"name\":\"判决书\",\"children\":[{\"name\":\"身份证材料\",\"categoryType\":\"pdf\",\"fileId\":\"644f1fbc-59b9-4e47-a4f6-74dd329f3735\"},{\"name\":\"证据材料\",\"categoryType\":\"pdf\",\"fileId\":\"644f1fbc-59b9-4e47-a4f6-74dd329f3735\"}]}]}]";
        JSONArray objects = JSONArray.parseArray(context);
        List<FileInfo> list = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            String name = jsonObject.getString("name");
            //1正卷 0 副卷
            int j = 2;
            if ("正卷".equals(name)) j = 1;
            if ("副卷".equals(name)) j = 0;
            JSONArray jsonArray = jsonObject.getJSONArray("children");
            getList(list, name, jsonArray,  j);
        }
        System.out.println(list);*/
        List<String> txt = new ArrayList<>();
        txt.add("正卷|原告提交证据材料|身份证材料|原告提交证据材");
//        txt.add("副卷|判决书|身份证材料");
        txt.add("正卷|原告提交证据材料|委托材料");
        List<ElectronicView> list = new ArrayList<>();

        for (String a : txt) {
            String[] split = a.split("\\|");
            List tempList = list;
            ElectronicView electronicView = null;
            for (int i = 0; i < split.length; i++) {
                electronicView = new ElectronicView();
                electronicView.setName(split[i]);
                ElectronicView exist = isExist(electronicView, tempList);
                if (null == exist){
                    tempList.add(electronicView);
                    tempList = electronicView.getChildren();
                    if (i+1 == split.length){
                        System.out.println("叶子节点");
                    }
                }else{
                    tempList = exist.getChildren();
                }
            }
        }
        String jsonArray2 = JSONArray.toJSONString(list);
        System.out.println(jsonArray2);
    }


    private static List<FileInfo> getList(List<FileInfo> list, String name, JSONArray objects, int j) {
        String na = name;
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = (JSONObject) objects.get(i);
            String names = jsonObject.getString("name");
            JSONArray children = jsonObject.getJSONArray("children");
            if (null != children && 0 < children.size()) {
                na = na + "|" + names;
                getList(list, na, children, j);
            } else {
                FileInfo fileInfo = new FileInfo();
                String fileId = jsonObject.getString("fileId");
                fileInfo.setFileId(fileId);
                fileInfo.setZfjbs(j);
                String name1 = jsonObject.getString("name");
                fileInfo.setDossierCatalogue(na + "|" + name1);
                fileInfo.setFileName(name1);
                String categoryType = jsonObject.getString("categoryType");
                fileInfo.setFileType(categoryType);
                list.add(fileInfo);
            }
        }
        return list;
    }


    private static ElectronicView isExist(ElectronicView menu,List<ElectronicView> list){
        for (ElectronicView obj : list) {
            if(menu.getName().equals(obj.getName())){
                return obj;
            }

        }
        return null;
    }
}






















































