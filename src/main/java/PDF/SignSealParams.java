package PDF;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.18
 */
public class SignSealParams {
    /**
     * 原因
     */
    private String reason;
    /**
     * 位置
     */
    private String location;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 签名域名称，多次追加签名的时候，签名域名称不能一样
     */
    private String fieldName;
    /**
     * 证书密码
     */
    private String password;
    /**
     * 图章左下角x
     */
    private int llx;
    /**
     * 图章左下角y
     */
    private int lly;
    /**
     * 图章右上角x
     */
    private int urx;
    /**
     * 图章右上角y
     */
    private int ury;

    SignSealParams() {
        this.pageNum = 1;
        this.fieldName = "sig1";
        this.llx = 200;
        this.lly = 200;
        this.urx = 300;
        this.ury = 300;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLlx() {
        return llx;
    }

    public void setLlx(int llx) {
        this.llx = llx;
    }

    public int getLly() {
        return lly;
    }

    public void setLly(int lly) {
        this.lly = lly;
    }

    public int getUrx() {
        return urx;
    }

    public void setUrx(int urx) {
        this.urx = urx;
    }

    public int getUry() {
        return ury;
    }

    public void setUry(int ury) {
        this.ury = ury;
    }
}
