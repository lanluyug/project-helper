package kk.lanluyu.projecthelper.mysteel.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zzh
 * @date 2024-04-17
 */
@Getter
@AllArgsConstructor
public enum EducationEnum {

    ILLITERACY("-", 0),
    PRIMARY("小学", 1),
    JUNIOR("初中", 2),
    SENIOR("高中", 3),
    JUNIOR_COLLEGE("大专", 4),
    UNDERGRADUATE("本科及以上", 5);

    /** 描述 */
    private final String name;

    /** 枚举值 */
    private final Integer value;

    public static String getName(Integer value){
        if(value == null){
            return null;
        }
        for(EducationEnum frame: EducationEnum.values()){
            if(value.equals(frame.value)){
                return frame.name;
            }
        }
        return null;
    }

    public static Integer getValue(String name){
        if(name == null){
            return null;
        }
        for(EducationEnum frame: EducationEnum.values()){
            if(name.equals(frame.name)){
                return frame.value;
            }
        }
        return null;
    }
}
