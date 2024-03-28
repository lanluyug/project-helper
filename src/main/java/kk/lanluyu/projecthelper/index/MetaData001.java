package kk.lanluyu.projecthelper.index;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzh
 * @date 2024-03-28
 */
@Data
@TableName("mb_df_metadata_0001")
public class MetaData001 {

    @TableId
    private String msId;

    private String framePath;

    private String frameIdPath;

    private String proCode;

    private String cnName;
}
