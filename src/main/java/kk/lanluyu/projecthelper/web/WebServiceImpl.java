package kk.lanluyu.projecthelper.web;

import com.alibaba.fastjson2.JSON;
import kk.lanluyu.projecthelper.core.domain.CommonResponse;
import kk.lanluyu.projecthelper.function.HpExecutorContext;
import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.OptionVo;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Service
@Slf4j
public class WebServiceImpl implements WebService{

    @Override
    public CommonResponse<RunVo> run(RunDto runDto) {
        log.info("参数信息：{}", JSON.toJSONString(runDto));
        return CommonResponse.success(HpExecutorContext.run(runDto));
    }

    @Override
    public CommonResponse<List<OptionVo>> listMode() {
        return CommonResponse.success(OptionVo.getModeList());
    }

    @Override
    public void export(HttpServletResponse response, ExportDto exportDto) {
        HpExecutorContext.export(exportDto, response);
    }


}
