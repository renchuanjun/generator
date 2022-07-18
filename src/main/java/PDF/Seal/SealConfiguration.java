package PDF.Seal;

import java.awt.*;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.18
 */
public class SealConfiguration {
    /**
     * 主文字
     */
    private SealFont mainFont;
    /**
     * 副文字
     */
    private SealFont viceFont;
    /**
     * 抬头文字
     */
    private SealFont titleFont;
    /**
     * 中心文字
     */
    private SealFont centerFont;
    /**
     * 边线圆
     */
    private SealCircle borderCircle;
    /**
     * 内边线圆
     */
    private SealCircle borderInnerCircle;
    /**
     * 内线圆
     */
    private SealCircle innerCircle;
    /**
     * 背景色，默认红色
     */
    private Color backgroudColor = Color.RED;
    /**
     * 图片输出尺寸，默认300
     */
    private Integer imageSize = 300;

    public SealFont getMainFont() {
        return mainFont;
    }

    public void setMainFont(SealFont mainFont) {
        this.mainFont = mainFont;
    }

    public SealFont getViceFont() {
        return viceFont;
    }

    public void setViceFont(SealFont viceFont) {
        this.viceFont = viceFont;
    }

    public SealFont getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(SealFont titleFont) {
        this.titleFont = titleFont;
    }

    public SealFont getCenterFont() {
        return centerFont;
    }

    public void setCenterFont(SealFont centerFont) {
        this.centerFont = centerFont;
    }

    public SealCircle getBorderCircle() {
        return borderCircle;
    }

    public void setBorderCircle(SealCircle borderCircle) {
        this.borderCircle = borderCircle;
    }

    public SealCircle getBorderInnerCircle() {
        return borderInnerCircle;
    }

    public void setBorderInnerCircle(SealCircle borderInnerCircle) {
        this.borderInnerCircle = borderInnerCircle;
    }

    public SealCircle getInnerCircle() {
        return innerCircle;
    }

    public void setInnerCircle(SealCircle innerCircle) {
        this.innerCircle = innerCircle;
    }

    public Color getBackgroudColor() {
        return backgroudColor;
    }

    public void setBackgroudColor(Color backgroudColor) {
        this.backgroudColor = backgroudColor;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }
}
