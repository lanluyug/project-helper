package kk.lanluyu.projecthelper.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zzh
 * @date 2024/04/26
 */
@AllArgsConstructor
@Getter
public enum FunctionEnum {

    /**
     * 支持的功能清单
     */
    JAVA_STRING_EVAL(1, ModeEnum.CONVERT.getMode(), "java代码多段+字符串拼接",
            "例如：\n输入：\"a\" + \"b\" + \"c\"\n输出：abc", 0, 0);

    private final Integer id;
    private final Integer mode;
    private final String name;
    private final String desc;
    private final Integer isDelete;
    private final Integer status;

}
