package PDF;

import java.util.List;

/**
 * @Description: 签章关键词文本位置
 * @author: 任传君
 * @date: 2022.07.18
 */

public class KeyWordLocation {

    /**
     * 文本
     */
    private String text;
    /**
     * 关键词所在pdf页码
     */
    private Integer pageNum;
    /**
     * 文本块所在矩形左下角坐标
     */
    private float llx;
    private float lly;
    /**
     * 文本块所在矩形右上角角坐标
     */
    private float urx;
    private float ury;
    /**
     * 文本块宽高
     */
    private float keyWordTextBlockWidth;
    private float keyWordTextBlockHeight;


    /**
     * 关键词匹配类型 参见keyFinder
     */
    private Integer keywordMatchType;

    /**
     * 关键词被解析成多个TextRenderInfo
     */
    private List<KeyWordCharLocation> mixMatchCharList;

}
