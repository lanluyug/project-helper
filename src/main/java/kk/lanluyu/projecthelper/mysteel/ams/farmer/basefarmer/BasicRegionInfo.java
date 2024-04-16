package kk.lanluyu.projecthelper.mysteel.ams.farmer.basefarmer;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ams_basic_region_info")
public class BasicRegionInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 地区编码
     */
    private String code;
    /**
     * 地区名称
     */
    private String name;
    /**
     * 父地区编码
     */
    private String parentCode;
    /**
     * 1:省  2:市  3：区
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 0：未删除  ；1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

}
