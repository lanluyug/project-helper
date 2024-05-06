package kk.lanluyu.projecthelper.function;

import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Slf4j
public class HpExecutorContext {

    private static final Map<Integer, FunctionEnum> FUNCTIONS = new HashMap<>();

    /*
     * 提升性能
     */
    static {
        Arrays.stream(FunctionEnum.values()).forEach(functionEnum ->
                FUNCTIONS.put(functionEnum.getId(), functionEnum));
    }


    public static RunVo run(RunDto runDto){
        return FUNCTIONS.get(runDto.getId()).getSingleExecutor().execute(runDto);
    }

    public static void export(ExportDto exportDto, HttpServletResponse response){
        FUNCTIONS.get(exportDto.getId()).getSingleExporter().export(exportDto, response);
    }
}
