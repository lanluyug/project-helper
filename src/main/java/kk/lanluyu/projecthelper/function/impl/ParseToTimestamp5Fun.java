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
public class ParseToTimestamp5Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        DateTime parse = DateUtil.parse(runDto.getText());
        String result = String.valueOf(parse.getTime());
        log.info(result);
        return new RunVo(result);
    }
}
