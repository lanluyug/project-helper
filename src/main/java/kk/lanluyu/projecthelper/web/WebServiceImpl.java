package kk.lanluyu.projecthelper.web;

import com.alibaba.fastjson2.JSON;
import kk.lanluyu.projecthelper.common.domain.CommonResponse;
import kk.lanluyu.projecthelper.function.HpExecutorContext;
import kk.lanluyu.projecthelper.function.model.dto.RunDto;
import kk.lanluyu.projecthelper.function.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Service
@Slf4j
public class WebServiceImpl implements WebService{

    @Autowired
    private HpExecutorContext hpExecutorContext;

    @Override
    public CommonResponse<RunVo> run(RunDto runDto) {
        log.info("参数信息：{}", JSON.toJSONString(runDto));
        return CommonResponse.success(hpExecutorContext.run(runDto));
    }

}
