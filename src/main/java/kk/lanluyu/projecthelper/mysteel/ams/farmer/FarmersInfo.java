package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import kk.lanluyu.projecthelper.mysteel.ams.entity.BaseDTO;
import lombok.Data;
import java.util.Objects;

/**
 * @author zzh
 * @date 2024-04-15
 */
@Data
@TableName("ams_farmers_info")
public class FarmersInfo extends BaseDTO {
    /**
     * 年份
     */
    private Integer year;
    /**
     * 农户姓名
     */
    private String name;
    /**
     * 农户代码
     */
    private String code;

    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 地区代码
     */
    private String regionCode;
    /**
     * 教育程度 教育程度（0：无
     * 1：小学2：
     * 初中3：
     * 高中4：
     * 大专
     * 5：本科及以上）
     */
    private Integer education;
    /**
     * 座机号码 座机号码（区号和电话号码用-分开）
     */
    private String phoneNumber;
    /**
     * 手机号码
     */
    private String mobileNumber;
    /**
     * 建点时间
     */
    private Integer establishYear;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 情况说明
     */
    private String remark;

    /**
     * 农户来源
     */
    private Integer source;

    @TableField(exist = false)
    @ExcelProperty(value = "品种名称(代码)[规模]--常规调查", index = 6)
    private String breedInfosC;
    @TableField(exist = false)
    @ExcelProperty(value = "品种名称(代码)[规模]--直报调查", index = 7)
    private String breedInfosZ;
    @TableField(exist = false)
    @ExcelProperty(value = "品种名称(代码)[规模]--生猪月报", index = 8)
    private String breedInfosS;

    @TableField(exist = false)
    @ExcelProperty(value = "专项调查", index = 9)
    private String reports;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FarmersInfo other = (FarmersInfo) o;
        return Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
