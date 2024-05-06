package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.NamingUtils;
import kk.lanluyu.projecthelper.core.util.TextUtils;
import kk.lanluyu.projecthelper.core.util.VelocityUtils;
import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.function.generateclass.entity.Columns;
import kk.lanluyu.projecthelper.function.generateclass.entity.impl.EasyExcelDomain;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.dromara.hutool.core.text.CharPool;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import java.util.*;


/**
 * @author zzh
 * @date 2024-05-06
 */
@Slf4j
public class ProduceEasyExcelEntity3Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {

        // todo
        String className = "Region2023Excel";
        /*
         * baiduTranslate = 1
         * randomTranslate = 2
         * pinyin首字母 = 3
         */
        int variableType = 3;
        String[] split = runDto.getText().split("\n");
        String header = split[0];
        List<String> items = TextUtils.splitField(header);
        StringBuilder naming = new StringBuilder();
        Set<String> namingSets = new HashSet<>();
        if(split.length >= 2){
            naming = new StringBuilder(split[1]);
        } else {
            for (int i = 0; i < items.size(); i++) {
                String translated = getTranslateInfoByType(variableType, items.get(i), namingSets);
                naming.append(translated);
                if(i != items.size() - 1){
                    naming.append(CharPool.TAB);
                }
            }
        }
        List<String> names = TextUtils.splitField(naming.toString());
        EasyExcelDomain easyExcelDomain = new EasyExcelDomain();

        List<Columns> columns = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Columns column = new Columns();
            column.setHead(items.get(i));
            column.setVariable(names.get(i));
            column.setIndex(i);
            columns.add(column);
        }
        easyExcelDomain.setColumns(columns);
        easyExcelDomain.setClassName(className);

        String render = VelocityUtils.render(easyExcelDomain, this::prepareContext, "vm/easyexcel.java.vm");
        log.info(render);
        return new RunVo(render);
    }


    public VelocityContext prepareContext(EasyExcelDomain easyExcelDomain)
    {
        VelocityContext velocityContext = VelocityUtils.baseContext(easyExcelDomain);
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
