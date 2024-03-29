package kk.lanluyu.projecthelper.csv;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Csv2Excel {


    /**
     * 单列数据的csv导出excel
     */
    @Test
    public void csv2Excel_oneField(){
        String path= "C:\\Users\\pc\\Desktop\\indexCode.csv";
        String excelPath= "C:\\Users\\pc\\Desktop\\indexCode.xlsx";

        CsvReader reader = CsvUtil.getReader();
        CsvData csvRows = reader.read(new File(path));
        List<String> rawRows = csvRows.getRows().stream().map(csvRow -> csvRow.get(0)).collect(Collectors.toList());
        ExcelWriter writer = ExcelUtil.getWriter(new File(excelPath));
        writer.write(rawRows);
        writer.close();
    }
}
