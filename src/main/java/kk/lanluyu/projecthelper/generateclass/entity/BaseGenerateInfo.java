package kk.lanluyu.projecthelper.generateclass.entity;

import lombok.Data;
import org.dromara.hutool.core.date.DateUtil;

/**
 * @author zzh
 * @date 2024-04-23
 */
@Data
public class BaseGenerateInfo {

    private String author = "zzh";

    private String functionName = "";

    private String datetime = DateUtil.formatDate(DateUtil.now());

    private String packageName = "";

    private String className = "";
}
