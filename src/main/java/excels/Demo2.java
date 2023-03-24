package excels;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class Demo2 {

    @Excel(name = "县派单位")
    private String unit;
    @Excel(name = "县派干部")
    private String name;
    @Excel(name = "县派干部职务")
    private String job;
    @Excel(name = "联系电话")
    private String tel;
    @Excel(name = "镇")
    private String type;
}


