package kk.lanluyu.projecthelper.produce.entity;

import kk.lanluyu.projecthelper.generate.VelocityInitializer;
import kk.lanluyu.projecthelper.generate.entity.Columns;
import kk.lanluyu.projecthelper.generate.entity.impl.EasyExcelDomain;
import kk.lanluyu.projecthelper.util.Util;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import org.junit.Test;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzh
 * @date 2024-04-22
 */
public class ProduceEasyExcelEntityTest {

    @Test
    public void produceEasyExcelEntity1(){
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
         * pinyin首字母 = 3
         * */
        int variableType = 3;


        String[] split = input.split("\n");
        String header = split[0];
        String[] items = header.split("\t");
        StringBuilder naming = new StringBuilder();
        Set<String> namingSets = new HashSet<>();
        if(split.length >= 2){
            naming = new StringBuilder(split[1]);
        } else {
            for (int i = 0; i < items.length; i++) {
                String translated = getTranslateInfoByType(variableType, items[i], namingSets);
                naming.append(translated);
                if(i != items.length - 1){
                    naming.append("\t");
                }
            }
        }
        String[] names = naming.toString().split("\t");
        EasyExcelDomain easyExcelDomain = new EasyExcelDomain();

        List<Columns> columns = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Columns column = new Columns();
            column.setHead(items[i]);
            column.setVariable(names[i]);
            column.setIndex(i);
//            if(i == 0){
//                column.setJavaType("Long");
//            }
            columns.add(column);
        }
        easyExcelDomain.setColumns(columns);
        easyExcelDomain.setClassName(className);
        VelocityInitializer.initVelocity();
        VelocityContext context = prepareContext(easyExcelDomain);

        Template tpl = Velocity.getTemplate("templates/easyexcel.java.vm");
        StringWriter sw = new StringWriter();
        tpl.merge(context, sw);
        Util.printAndCopy2Clipboard(sw.toString());
    }

    public static VelocityContext prepareContext(EasyExcelDomain easyExcelDomain)
    {

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("columns", easyExcelDomain.getColumns());
        velocityContext.put("functionName", easyExcelDomain.getFunctionName());
        velocityContext.put("className", easyExcelDomain.getClassName());
        velocityContext.put("author", easyExcelDomain.getAuthor());
        velocityContext.put("datetime", easyExcelDomain.getDatetime());
        velocityContext.put("packageName", easyExcelDomain.getPackageName());

        return velocityContext;
    }

    private String getTranslateInfoByType(Integer type, String text, Set<String> namingSet){

        if(type == null){
            return PinyinUtil.getFirstLetter(text, "");
        }
        switch (type){
            case 1:
                return Util.getChineseVariableName(text);
            case 2:
                return Util.getRandomName();
            case 3:
            default:
                return Util.getFirstLetterUnrepeated(text, namingSet);
        }
    }

}
