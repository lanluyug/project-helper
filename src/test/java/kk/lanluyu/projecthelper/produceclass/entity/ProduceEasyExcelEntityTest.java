package kk.lanluyu.projecthelper.produceclass.entity;

import kk.lanluyu.projecthelper.core.util.Util;
import kk.lanluyu.projecthelper.function.impl.ProduceEasyExcelEntity3Fun;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import org.junit.Test;

/**
 * @author zzh
 * @date 2024-04-22
 */
public class ProduceEasyExcelEntityTest {

    /**
     * 输入信息
     * 第一行  表头
     * 第二行 表头对应的变量名称
     * **/
    @Test
    public void produceEasyExcelEntity(){
        String input = "年份\t地区名称\t地区代码\t级别\t属省代码\t属省名称\t属市代码\t属市名称\t属县代码\t属县名称\t大中城市";
        String className = "Region2023Excel";
        RunDto runDto = new RunDto();
        runDto.setText(input);
        RunVo execute = new ProduceEasyExcelEntity3Fun().execute(runDto);
        Util.printAndCopy2Clipboard(execute.getResult());
    }

}
