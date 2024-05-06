package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.IpRegionUtils;
import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;


/**
 * @author zzh
 * @date 2024-05-06
 */
@Slf4j
public class IpToRegion7Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        String region = IpRegionUtils.getRegionByIp(runDto.getText());
        log.info(region);
        return new RunVo(region);
    }
}
