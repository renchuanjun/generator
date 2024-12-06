package PDF.Seal;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 实现功能描述：生成签名
 *
 * @param:null
 * @author: 任传君
 * @date: 2022-07-18 13:03
 */
public class Main {

    /**
     * 实现功能描述：生成带有红★和主文字的公章
     *
     * @param mainFontText 主标题
     * @param imageSize    公章大小
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 10:57
     */
    public static BufferedImage sealOne(String mainFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*-------------主文字-------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(35);
        mainFont.setFontSpace(35.0);
        configuration.setMainFont(mainFont);
        /*-----------中心文字------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText("★");
        centerFont.setFontSize(100);
        configuration.setCenterFont(centerFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 140));
        //todo 生成到本地
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章1.png");
        //返回
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }

    /**
     * 实现功能描述：实现功能描述：生成带有红★,主文字,副标题(底部环绕)的公章
     *
     * @param mainFontText 主标题
     * @param viceFontText 副标题
     * @param imageSize    公章大小
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 10:58
     */
    public static BufferedImage sealTwo(String mainFontText, String viceFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*-------------主文字-------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(35);
        mainFont.setFontSpace(35.0);
        configuration.setMainFont(mainFont);
        /*-----------中心文字------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText("★");
        centerFont.setFontSize(100);
        configuration.setCenterFont(centerFont);
        /*-----------副标题------------*/
        SealFont viceFont = new SealFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        viceFont.setFontText(viceFontText);
        viceFont.setFontSize(22);
        viceFont.setFontSpace(30.0);
        configuration.setViceFont(viceFont);
        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 140));
        //生成到本地
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章2.png");
        //返回
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }

    /**
     * 实现功能描述：生成带有红★,主文字,副标题(红★底部)的公章
     *
     * @param mainFontText  主标题
     * @param titleFontText 抬头标题
     * @param imageSize     公章大小
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 10:58
     */
    public static BufferedImage sealThree(String mainFontText, String titleFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*-------------主文字-------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(35);
        mainFont.setFontSpace(35.0);
        configuration.setMainFont(mainFont);
        /*-----------中心文字------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText("★");
        centerFont.setFontSize(100);
        configuration.setCenterFont(centerFont);
        /*---------------抬头文字----------------*/
        SealFont titleFont = new SealFont();
        titleFont.setBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(22);
        titleFont.setMarginSize(68);
        titleFont.setFontText(titleFontText);
        configuration.setTitleFont(titleFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 140));
        //生成到本地
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章3.png");
        //返回
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }

    /**
     * 实现功能描述：双圈公章
     *
     * @param mainFontText   主标题
     * @param centerFontText 中心文字
     * @param imageSize      公章大小
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 10:59
     */
    public static BufferedImage sealFour(String mainFontText, String centerFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*-------------主文字-------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(7);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(23);
        mainFont.setFontSpace(37.0);
        configuration.setMainFont(mainFont);
        /*-----------中心文字------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        //"淘宝欢乐\n制图网淘宝\n专用章"
        centerFont.setFontSize(20);
        centerFont.setFontText(centerFontText);
        configuration.setCenterFont(centerFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 140));
        //内边线粗细、半径
        configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
        //内环线粗细、半径
        configuration.setInnerCircle(new SealCircle(2, 105, 105));
        //生成到本地
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章4.png");
        //返回
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }

    /**
     * 实现功能描述：椭圆形印章
     *
     * @param mainFontText   主标题
     * @param centerFontText 中心文字
     * @param titleFontText  抬头文字
     * @param imageSize      公章大小
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 13:28
     */
    public static BufferedImage sealFive(String mainFontText, String centerFontText, String titleFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*---------------主文字---------------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(25);
        mainFont.setFontSpace(12.0);
        configuration.setMainFont(mainFont);
        /*------------中心文字----------------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText(centerFontText);
        centerFont.setFontSize(20);
        configuration.setCenterFont(centerFont);
        /*-----------抬头文字----------------------*/
        SealFont titleFont = new SealFont();
        titleFont.setBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(22);
        titleFont.setFontText(titleFontText);
        titleFont.setMarginSize(34);
        configuration.setTitleFont(titleFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 100));
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章5.png");
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }


    /**
     * 实现功能描述：椭圆形外圈双环印章
     *
     * @param mainFontText   主标题
     * @param centerFontText 中心文字
     * @param viceFontText   副标题
     * @param imageSize      公章大小 默认300
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 13:42
     */
    public static BufferedImage sealSix(String mainFontText, String centerFontText, String viceFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*---------------主文字---------------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(25);
        mainFont.setFontSpace(12.0);
        configuration.setMainFont(mainFont);
        /*------------中心文字----------------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText(centerFontText);
        centerFont.setFontSize(20);
        configuration.setCenterFont(centerFont);
        /*--------------副文字-----------------------*/
        SealFont viceFont = new SealFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        viceFont.setFontText(viceFontText);
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);
        configuration.setViceFont(viceFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 100));
        //内边线粗细、半径
        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));

//        configuration.setInnerCircle(new SealCircle(2, 85, 45));
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章6.png");
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }

    /**
     * 实现功能描述：
     *
     * @param mainFontText   主标题
     * @param centerFontText 中心文字
     * @param viceFontText   副标题
     * @param imageSize      公章大小 默认300
     * @param:
     * @author: 任传君
     * @date: 2022-07-19 14:16
     */
    public static BufferedImage sealSeven(String mainFontText, String centerFontText, String viceFontText, Integer imageSize) throws Exception {
        SealConfiguration configuration = new SealConfiguration();
        /*---------------主文字---------------------*/
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText(mainFontText);
        mainFont.setFontSize(25);
        mainFont.setFontSpace(12.0);
        configuration.setMainFont(mainFont);
        /*------------中心文字----------------------*/
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText(centerFontText);
        centerFont.setFontSize(20);
        configuration.setCenterFont(centerFont);
        /*--------------副文字-----------------------*/
        SealFont viceFont = new SealFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        viceFont.setFontText(viceFontText);
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);
        configuration.setViceFont(viceFont);

        //图片大小
        configuration.setImageSize(null == imageSize ? 300 : imageSize);
        //背景颜色
        configuration.setBackgroudColor(Color.RED);
        //边线粗细、半径
        configuration.setBorderCircle(new SealCircle(3, 140, 100));
        //内环线粗细、半径
        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        SealUtil.buildAndStoreSeal(configuration, "D:\\公章7.png");
        BufferedImage image = SealUtil.buildSeal(configuration);
        return image;
    }


    public static void main(String[] args) throws Exception {
//        sealOne("影研科技测试专用章",  300);
//        sealTwo("影研科技测试专用章", "测试专用", 300);
//        sealThree("影研科技测试专用章", "测试专用", 300);
//        sealFour(, "影研科技\r\n测试专用", 300);
//        sealFive("影研科技测试专用章", "影研科技有限公司", "正版认证", 300);
//        sealSix("影研科技测试专用章", "影研科技", "正版认证", 300);
//        sealSeven("影研科技测试专用章", "影研科技",  "正版认证", 300);
        //2.生成私章
        SealFont font = new SealFont();
        font.setFontSize(120).setBold(true).setFontText("任传君");
        SealUtil.buildAndStorePersonSeal(300, 16, font, "印", "D:\\私章.png");
    }
}
