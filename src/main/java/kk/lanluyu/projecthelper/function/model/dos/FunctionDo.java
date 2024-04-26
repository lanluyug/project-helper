package kk.lanluyu.projecthelper.function.model.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzh
 * @date 2024/04/26
 */
@Data
@TableName("ph_function")
public class FunctionDo {

    private Long id;

    private Integer mode;

    private String desc;

    private Integer isDelete;

    private Integer status;
}
