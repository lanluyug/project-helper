package kk.lanluyu.projecthelper.function;

import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Slf4j
public class HpExecutorContext {


    public static RunVo run(RunDto runDto){
        FunctionEnum instance = FunctionEnum.getInstance(runDto.getId());
        return instance.getSingleExecutor().execute(runDto);
    }

    public static void export(ExportDto exportDto, HttpServletResponse response){
        FunctionEnum instance = FunctionEnum.getInstance(exportDto.getId());
        instance.getSingleExporter().export(exportDto, response);
    }
}
