package kk.lanluyu.projecthelper.mysteel.ams.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xumc
 * @date 2022年04月14日18:24
 */
@Data
public class BaseDTO implements Serializable{
    /**
     * ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    /**
     * 创建人用户名
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;
    /**
     * 更新人用户名
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
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
