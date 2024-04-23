package kk.lanluyu.projecthelper.generate.entity.impl;

import kk.lanluyu.projecthelper.generate.entity.BaseGenerateInfo;
import kk.lanluyu.projecthelper.generate.entity.Columns;
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
