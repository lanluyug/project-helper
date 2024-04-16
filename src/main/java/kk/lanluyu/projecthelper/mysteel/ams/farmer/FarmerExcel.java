package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zzh
 * @date 2024-04-15
 */
@Data
public class FarmerExcel {

    /**
     * 农户来源
     */
    private Integer farmerSource;

    @ExcelProperty(value = "年份", index = 0)
    private Integer year;
    @ExcelProperty(value = "农户代码", index = 1)
    private String code;
    @ExcelProperty(value = "农户名称", index = 2)
    private String name;
    @ExcelProperty(value = "属省名称", index = 3)
    private String province;
    @ExcelProperty(value = "属市名称", index = 4)
    private String city;
    @ExcelProperty(value = "属县名称", index = 5)
    private String county;

    @ExcelProperty(value = "品种名称(代码)[规模]--常规调查", index = 6)
    private String breedInfosC;
    @ExcelProperty(value = "品种名称(代码)[规模]--直报调查", index = 7)
    private String breedInfosZ;
    @ExcelProperty(value = "品种名称(代码)[规模]--生猪月报", index = 8)
    private String breedInfosS;

    @ExcelProperty(value = "专项调查", index = 9)
    private String reports;
    @ExcelProperty(value = "出生时间", index = 10)
    private String birthday;
    @ExcelProperty(value = "教育程度", index = 11)
    private String edu;

    private Integer education;
    @ExcelProperty(value = "区号", index = 12)
    private String regionCode;
    @ExcelProperty(value = "电话", index = 13)
    private String phoneNumber;
    @ExcelProperty(value = "手机", index = 14)
    private String mobileNumber;
    @ExcelProperty(value = "地址", index = 15)
    private String address;
    @ExcelProperty(value = "邮编", index = 16)
    private String postcode;
    @ExcelProperty(value = "建点时间", index = 17)
    private String establishYear;
    @ExcelProperty(value = "情况说明", index = 18)
    private String remark;

}
