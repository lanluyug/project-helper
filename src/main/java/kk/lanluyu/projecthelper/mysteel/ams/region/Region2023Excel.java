package kk.lanluyu.projecthelper.mysteel.ams.region;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 年份	地区名称	地区代码	级别	属省代码	属省名称	属市代码	属市名称	属县代码	属县名称	大中城市
 *
 * @author zzh
 * @date 2024-04-22
 */
@Data
public class Region2023Excel {

    @ExcelProperty(value = "年份", index = 0)
    private Integer year;
    @ExcelProperty(value = "地区名称", index = 0)
    private String regionName;
    @ExcelProperty(value = "地区代码", index = 0)
    private String regionCode;
    @ExcelProperty(value = "级别", index = 0)
    private String level;
    @ExcelProperty(value = "属省代码", index = 0)
    private String provinceCode;
    @ExcelProperty(value = "属省名称", index = 0)
    private String province;
    @ExcelProperty(value = "属市代码", index = 0)
    private String cityCode;
    @ExcelProperty(value = "属市名称", index = 0)
    private String city;
    @ExcelProperty(value = "属县代码", index = 0)
    private String countyCode;
    @ExcelProperty(value = "属县名称", index = 0)
    private String county;
    @ExcelProperty(value = "大中城市", index = 0)
    private String isBigCity;
}
