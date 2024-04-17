package kk.lanluyu.projecthelper.mysteel.ams.entity;

import lombok.Getter;

/**
 * 上报类型枚举类
 */
@Getter
public enum ReportTypeEnum {
//    SPECIAL_CHECK(0,"专项调查"),
    DIRECT_CHECK(1,"直报调查"),
    ROUTINE_CHECK(2,"常规调查"),
    EMERGENCY_CHECK(3,"应急调查"),
    PLATE_PURPOSE(4,"种植意向"),
    STORAGE_SALE_GRAIN(5,"存售粮情况"),
    TOOL_PURCHASE(6,"农资购买");

    //枚举值
    private final Integer value;
    //描述
    private final String name;

    ReportTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Integer getValueByName(String name){
        ReportTypeEnum[] values = values();
        for(ReportTypeEnum reportTypeEnum : values){
            if(reportTypeEnum.getName().equals(name)){
                return reportTypeEnum.getValue();
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value){
        ReportTypeEnum[] values = values();
        for(ReportTypeEnum reportTypeEnum : values){
            if(reportTypeEnum.getValue().equals(value)){
                return reportTypeEnum.getName();
            }
        }
        return "";
    }

}
