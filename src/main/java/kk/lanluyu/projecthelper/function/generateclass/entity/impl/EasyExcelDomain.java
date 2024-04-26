package kk.lanluyu.projecthelper.function.generateclass.entity.impl;

import kk.lanluyu.projecthelper.function.generateclass.entity.BaseGenerateInfo;
import kk.lanluyu.projecthelper.function.generateclass.entity.Columns;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zzh
 * @date 2024-04-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EasyExcelDomain extends BaseGenerateInfo {

    private List<Columns> columns;
}
