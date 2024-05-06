package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.IpRegionUtils;
import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.function.HpExecutorContext;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Component采用 HpExecutor_ + id的形式
 * @author zzh
 * @date 2024/04/27
 */
@Component(HpExecutorContext.COMPONENT_PREFIX + "7")
@Slf4j
public class IpToRegion7Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        String region = IpRegionUtils.getRegionByIp(runDto.getText());
        log.info(region);
        return new RunVo(region);
    }
}
