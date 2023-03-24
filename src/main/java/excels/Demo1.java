package excels;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

import java.util.List;

@Data
public class Demo1 {


    @Excel(name = "镇")
    private String town;
    @Excel(name = "村")
    private String village;
    @Excel(name = "类型", replace = {"脱贫村_1", "脱贫村_2", "重点帮扶村_3", "相对后进村_4"})
    private Integer type;

    @Excel(name = "单位", groupName = "第一书记（工作队队长）", orderNum = "1")
    private String captainWorkUnit;
    @Excel(name = "姓名")
    private String captainName;
    @Excel(name = "职务")
    private String captainJobDuty;
    @Excel(name = "联系方式")
    private String captainTel;

    @Excel(name = "党组织书记兼村委会主任姓名", groupName = "嘎查村“两委”负责人", orderNum = "1")
    private String principalsName;
    @Excel(name = "联系电话")
    private String principalsTel;

    @ExcelCollection(name = "工作队员")
    private List<Demo2> demo2;
}

