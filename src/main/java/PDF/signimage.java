package PDF;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.font.FontDesignMetrics;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Description: 生成 一个签章图片
 * @author: 任传君
 * @date: 2022.07.18
 */
public class signimage {

    /**
     * @param doctorname   string 医生名字
     * @param hospitalname string 医生名称
     * @param date         string 签名日期
     *                     图片高度
     * @param jpgname      string jpg图片名
     * @return
     */
    /*public static boolean createsigntextimg( String doctorname, String hospitalname, String date, String jpgname) {
        int width = 255;
        int height = 100;
        FileOutputStream out = null;
        //背景色new Color(255, 255, 255)
        Color bgcolor = new Color(0, 0, 0,1);
        //字色
        Color fontcolor = Color.red;
        Font doctornamefont = new Font(null, Font.BOLD, 20);
        Font othortextfont = new Font(null, Font.BOLD, 18);
        try { // 宽度 高度
            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            // 背景色
//            g.setColor(bgcolor);
            // 画一个矩形
//            g.fillRect(0, 0, width, height);

            g.drawOval(0, 0, width, height);

            // 去除锯齿(当设置的字体过大的时候,会出现锯齿)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(Color.red);
            g.fillRect(0, 0, 8, height);
            g.fillRect(0, 0, width, 8);
            g.fillRect(0, height - 8, width, height);
            g.fillRect(width - 8, 0, width, height);
            // 字的颜色
            g.setColor(fontcolor);
            // 字体字形字号
            g.setFont(doctornamefont);
            FontMetrics fm = FontDesignMetrics.getMetrics(doctornamefont);
            int font1_hight = fm.getHeight();
            int strwidth = fm.stringWidth(doctorname);
            int y = 35;
            int x = (width - strwidth) / 2;
            // 在指定坐标除添加文字
            g.drawString(doctorname, x, y);
            // 字体字形字号
            g.setFont(othortextfont);

            fm = FontDesignMetrics.getMetrics(othortextfont);
            int font2_hight = fm.getHeight();
            strwidth = fm.stringWidth(hospitalname);
            x = (width - strwidth) / 2;
            // 在指定坐标除添加文字
            g.drawString(hospitalname, x, y + font1_hight);

            strwidth = fm.stringWidth(date);
            x = (width - strwidth) / 2;
            // 在指定坐标除添加文字
            g.drawString(date, x, y + font1_hight + font2_hight);

            g.dispose();
            // 指定输出文件
            out = new FileOutputStream(jpgname);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(50f, true);
            // 存盘
            encoder.encode(bimage, param);
            out.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        createsigntextimg("影研", "影研科技", "2022.01.01", "sign.png");
    }*/






}
