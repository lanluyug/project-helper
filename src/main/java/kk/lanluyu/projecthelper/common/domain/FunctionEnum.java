package kk.lanluyu.projecthelper.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FunctionEnum {

    private final Integer id;
    private final Integer mode;
    private final String name;
    private final String desc;
    private final Integer isDelete;
    private final Integer status;
}
