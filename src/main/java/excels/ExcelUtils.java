package excels;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterInputStream;

public class ExcelUtils {

    private static String fileUrl = "D:/work/需求文档/数字乡村二期-包头/驻村工作-驻村管理模板.xlsx";

    public static void main(String[] args) throws Exception {
        File file = new File(fileUrl);
        excel1(file);
    }

    //读取excel
    public static void excel1(File file) throws Exception {
        //创建Workbook对象
        Workbook hssfWorkbook = getWorkBook(file);
        //获取sheet数量
        int sheetNum = hssfWorkbook.getNumberOfSheets();
        List<Demo1> data = new ArrayList<>();
        if (0 == sheetNum) return;
        //遍历sheet页
        for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
            //获取sheet名称
            //String sheetName = hssfWorkbook.getSheetName(sheetPage);
            //读取每一页的数据
            sheet(data, file, sheetIndex);
        }

    }

    public static void sheet(List<Demo1> demo1s, File file, int sheetPage) throws Exception {
        ImportParams params = new ImportParams();
        //读取第Nsheet
        params.setStartSheetIndex(sheetPage);
        //定义表头行数
        params.setHeadRows(3);
        List<Demo1> objects = ExcelImportUtil.importExcel(new FileInputStream(file), Demo1.class, params);
        System.out.println(objects.toString());
        demo1s.addAll(objects);
    }


    //读取excel
    public static Workbook getWorkBook(File file) throws IOException {
        //这样写excel能兼容03和07
        FileInputStream fis = new FileInputStream(file);
        Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(fis);
        } catch (Exception ex) {
            fis = new FileInputStream(file);
            hssfWorkbook = new XSSFWorkbook(fis);
        }
        return hssfWorkbook;
    }
}
