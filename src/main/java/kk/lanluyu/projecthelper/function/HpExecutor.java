package kk.lanluyu.projecthelper.function;


import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;


/**
 * @author zzh
 * @date 2024/04/26
 */
public interface HpExecutor {

    /**
     * @param runDto
     * @return {@link RunVo}
     */
    RunVo execute(RunDto runDto);
}
