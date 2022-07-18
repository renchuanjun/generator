package strategy;

public enum OrderTypeEnum {

    Normal("1", "普通"),
    Group("2", "团队"),
    Promotion("3", "促销");

    private String code;    //代码
    private String name;    //名称，描述

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    OrderTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (OrderTypeEnum temp : OrderTypeEnum.values()) {
            if (temp.getCode().equals(code)) {
                return temp.getName();
            }
        }
        return null;
    }
}
