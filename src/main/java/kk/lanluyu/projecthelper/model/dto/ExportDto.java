package kk.lanluyu.projecthelper.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zzh
 * @date 2024/05/04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExportDto extends RunDto{

    private String fileName = "export";

}
