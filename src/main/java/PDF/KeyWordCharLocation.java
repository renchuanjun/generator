package PDF;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.18
 */
public class KeyWordCharLocation extends KeyWordLocation{

    /**
     * 混乱匹配时的起始位置
     */
    private Integer mixMatchStart;

    /**
     * 匹配的字符数
     */
    private Integer mixMatchCount;

    public Integer getMixMatchStart() {
        return mixMatchStart;
    }

    public void setMixMatchStart(Integer mixMatchStart) {
        this.mixMatchStart = mixMatchStart;
    }

    public Integer getMixMatchCount() {
        return mixMatchCount;
    }

    public void setMixMatchCount(Integer mixMatchCount) {
        this.mixMatchCount = mixMatchCount;
    }
}
