package kk.lanluyu.projecthelper.produce.entity;

import kk.lanluyu.projecthelper.util.Util;
import org.junit.Test;

/**
 * @author zzh
 * @date 2024-04-22
 */
public class ProduceEasyExcelEntityTest {

    /**
     * 根据表头生成EasyExcel实体类
     */
    @Test
    public void produceEasyExcelEntity(){
        /**
         * 输入信息
         * 第一行  表头
         * 第二行 表头对应的变量名称
         * **/ 
        String input = "年份\t地区名称\t地区代码\t级别\t属省代码\t属省名称\t属市代码\t属市名称\t属县代码\t属县名称\t大中城市";
        String className = "Region2023Excel";
        /**
         * baiduTranslate = 1
         * randomTranslate = 2
         * */
        int variableType = 1;


        String[] split = input.split("\n");
        String header = split[0];
        String[] items = header.split("\t");
        StringBuilder naming = new StringBuilder();
        if(split.length >= 2){
            naming = new StringBuilder(split[1]);
        } else {
            if(variableType == 1){
                for (int i = 0; i < items.length; i++) {
                    String translated = Util.getChineseVariableName(items[i]);
                    naming.append(translated);
                    if(i != items.length - 1){
                        naming.append("\t");
                    }
                }
            }else if(variableType == 2){
                for (int i = 0; i < items.length; i++) {
                    String translated = Util.getRandomName();
                    naming.append(translated);
                    if(i != items.length - 1){
                        naming.append("\t");
                    }
                }
            }

        }
        String[] names = naming.toString().split("\t");
        // 添加包名
        StringBuilder code = new StringBuilder("import com.alibaba.excel.annotation.ExcelProperty;\n" +
                "import lombok.Data;");
        // 添加注释
        code.append("/**\n" +
                " * ")
                .append(header)
                .append("\n* @author zzh\n" +
                        " * @date 2024-04-22\n" +
                        " */");
        // 代码
        code.append("@Data\n" +
                "public class ")
                .append(className)
                .append(" {\n");
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            code.append("    @ExcelProperty(value = \"")
                    .append(item)
                    .append("\", index = ")
                    .append(i)
                    .append(")\n")
                    .append("    private String ")
                    .append(names[i])
                    .append(";\n")
            ;
        }
        code.append("}");
        Util.printAndCopy2Clipboard(code);
    }

}
