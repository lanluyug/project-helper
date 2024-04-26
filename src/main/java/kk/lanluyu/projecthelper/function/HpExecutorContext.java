package kk.lanluyu.projecthelper.function;

import kk.lanluyu.projecthelper.function.model.dto.RunDto;
import kk.lanluyu.projecthelper.function.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Component
@Slf4j
public class HpExecutorContext {

    private static final String COMPONENT_PREFIX = "HpExecutor_";

    @Autowired
    private Map<String, HpExecutor> hpExecutorMap;


    public RunVo run(RunDto runDto){
        HpExecutor hpExecutor = hpExecutorMap.get(COMPONENT_PREFIX + runDto.getId());
        if(hpExecutor == null){
            log.error("找不到功能组件，id={}", runDto.getId());
            throw new UnsupportedOperationException();
        }
        return hpExecutor.execute(runDto);
    }
}
