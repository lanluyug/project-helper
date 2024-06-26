package kk.lanluyu.projecthelper.function.impl;


import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.core.date.DateUtil;


/**
 * @author zzh
 * @date 2024-05-06
 */
@Slf4j
public class FormatToDate4Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        String text = runDto.getText();
        if(text.length() == 10){
            text += "000";
        }
        DateTime parse = new DateTime(Long.parseLong(text));
        String result = DateUtil.formatDateTime(parse);
        log.info(result);
        return new RunVo(result);
    }
}
