package kk.lanluyu.projecthelper.csv;

import org.dromara.hutool.poi.csv.CsvData;
import org.dromara.hutool.poi.csv.CsvReader;
import org.dromara.hutool.poi.csv.CsvUtil;
import org.dromara.hutool.poi.excel.ExcelUtil;
import org.dromara.hutool.poi.excel.ExcelWriter;
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
