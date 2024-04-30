package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.IpRegionUtil;
import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Component采用 HpExecutor_ + id的形式
 * @author zzh
 * @date 2024/04/27
 */
@Component("HpExecutor_7")
@Slf4j
public class IpToRegion7Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        String region = IpRegionUtil.getRegionByIp(runDto.getText());
        log.info(region);
        return new RunVo(region);
    }
}
