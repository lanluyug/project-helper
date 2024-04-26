package kk.lanluyu.projecthelper.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zzh
 * @date 2024-04-26
 */
@Getter
@AllArgsConstructor
public enum ModeEnum {
    /**
     * 支持的一级菜单
     */
    PRODUCE(1, "生成"),

    CONVERT(2, "转换"),

    CUSTOM(3, "自定义"),
    ;

    private final Integer mode;

    private final String desc;


    public static ModeEnum getInstance(Integer mode){
        for (ModeEnum modeEnum : values()) {
            if(modeEnum.mode.equals(mode)){
                return modeEnum;
            }
        }
        return null;
    }
}
