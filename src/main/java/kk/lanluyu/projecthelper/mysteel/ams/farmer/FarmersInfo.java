package kk.lanluyu.projecthelper.mysteel.ams.farmer;

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
