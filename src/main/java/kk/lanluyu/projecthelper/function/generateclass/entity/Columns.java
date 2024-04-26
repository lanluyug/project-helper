package kk.lanluyu.projecthelper.function.generateclass.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzh
 * @date 2024-04-23
 */
@Data
public class Columns implements Serializable {

    private Integer index;

    private String head;

    private String variable;

    private String javaType = "String";
}
