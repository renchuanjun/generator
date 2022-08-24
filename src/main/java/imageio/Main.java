package imageio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.22
 */
public class Main {

    public static void main(String[] args) {
//        String data = "我的1<if test1='case == 1'>1在</if>我的2<if test='case == 2'>他2</if>";
//        String regex = "<if test='([\\s\\S]*?)'>([\\s\\S]*?)</if>";

        String data = "我的1<test>失节事大<test1>失节事大</test1></test><test1>失节事大</test1>";
        String regex = "<test>([\\s\\S]*?)</test>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
        }
    }
}
