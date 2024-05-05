package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.core.domain.CommonResponse;
import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.OptionVo;
import kk.lanluyu.projecthelper.model.vo.RunVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zzh
 * @date 2024/04/26
 */

public interface WebService {

    /**
     * @param runDto
     * @return {@link CommonResponse}<{@link RunVo}>
     */
    CommonResponse<RunVo> run(RunDto runDto);
    CommonResponse<List<OptionVo>> listMode();

    void export(HttpServletResponse response, ExportDto exportDto);
}
