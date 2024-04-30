package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.NamingUtils;
import kk.lanluyu.projecthelper.core.util.Util;
import kk.lanluyu.projecthelper.core.util.VelocityUtil;
import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.function.generateclass.entity.Columns;
import kk.lanluyu.projecthelper.function.generateclass.entity.impl.EasyExcelDomain;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Component采用 HpExecutor_ + id的形式
 * @author zzh
 * @date 2024/04/27
 */
@Component("HpExecutor_3")
@Slf4j
public class ProduceEasyExcelEntity3Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {

        String className = "Region2023Excel";
        /**
         * baiduTranslate = 1
         * randomTranslate = 2
         * pinyin首字母 = 3
         * */
        int variableType = 3;
        String[] split = runDto.getText().split("\n");
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

        String render = VelocityUtil.render(easyExcelDomain, this::prepareContext, "vm/easyexcel.java.vm");
        log.info(render);
        return new RunVo(render);
    }


    public VelocityContext prepareContext(EasyExcelDomain easyExcelDomain)
    {
        VelocityContext velocityContext = VelocityUtil.baseContext(easyExcelDomain);
        velocityContext.put("columns", easyExcelDomain.getColumns());
        return velocityContext;
    }

    private String getTranslateInfoByType(Integer type, String text, Set<String> namingSet){

        if(type == null){
            return PinyinUtil.getFirstLetter(text, "");
        }
        switch (type){
            case 1:
                return NamingUtils.getChineseVariableName(text);
            case 2:
                return NamingUtils.getRandomName();
            case 3:
            default:
                return NamingUtils.getFirstLetterUnrepeated(text, namingSet);
        }
    }
}
