package kk.lanluyu.projecthelper.mysteel.ams.entity;

/**
 * 是和否的枚举类
 *
 * @author procon
 * @since 2023-05-09
 */
public enum YesOrNoEnum {
    NO(0,"否"),
    YES(1,"是");

    private final int code;
    private final String desc;

   YesOrNoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
