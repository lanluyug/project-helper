package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.common.domain.CommonResponse;
import kk.lanluyu.projecthelper.function.model.dto.RunDto;
import kk.lanluyu.projecthelper.function.model.vo.RunVo;

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
}
