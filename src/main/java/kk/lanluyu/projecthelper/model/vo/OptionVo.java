package kk.lanluyu.projecthelper.model.vo;

import kk.lanluyu.projecthelper.function.FunctionEnum;
import kk.lanluyu.projecthelper.core.domain.YesOrNoEnum;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024/04/27
 */
@Data
public class OptionVo {

    private Integer value;

    private String label;

    private String desc;

    public static List<OptionVo> getModeList(){
        return Arrays.stream(FunctionEnum.values())
                .filter(functionEnum -> functionEnum.getIsDelete().equals(YesOrNoEnum.NO))
                .map( functionEnum -> {
                    OptionVo optionVo = new OptionVo();
                    optionVo.setValue(functionEnum.getId());
                    optionVo.setLabel(functionEnum.getName());
                    optionVo.setDesc(functionEnum.getDesc());
                    return optionVo;
                }).collect(Collectors.toList());
    }
}
