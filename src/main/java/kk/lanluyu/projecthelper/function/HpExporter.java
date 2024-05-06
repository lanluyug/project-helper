package kk.lanluyu.projecthelper.function;


import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;

import javax.servlet.http.HttpServletResponse;


/**
 * @author zzh
 * @date 2024/04/26
 */
public interface HpExporter {

    /**
     * @param runDto
     * @return {@link RunVo}
     */
    void export(ExportDto runDto, HttpServletResponse response);
}
