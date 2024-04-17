package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.baomidou.mybatisplus.annotation.TableName;
import kk.lanluyu.projecthelper.mysteel.ams.entity.BaseDTO;
import lombok.Data;
import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.core.date.DateUtil;

@Data
@TableName("ams_farmer_report_type")
public class FarmerReportType extends BaseDTO {

    public static final DateTime baseDate = DateUtil.parse("2024-04-16 15:00:00");
    /**
     * 年份
     */
    private Integer checkYear;
    /**
     * 农户id
     */
    private String farmerCode;
    /**
     * 品种code
     */
    private String breedCode;
    /**
     * 上报类型1：直报调查 2：常规调查 3：应急调查 4 种植意向 5存寿粮 6农具购买
     */
    private Integer reportType;


    public static FarmerReportType build2023Default(){
        FarmerReportType farmerReportType = new FarmerReportType();
        farmerReportType.setCheckYear(2023);
        farmerReportType.setCreateUserId(25L);
        farmerReportType.setUpdateUserId(25L);
        farmerReportType.setCreateUserName("admin");
        farmerReportType.setUpdateUserName("admin");
        farmerReportType.setCreateTime(baseDate);
        farmerReportType.setUpdateTime(baseDate);
        farmerReportType.setIsDelete(false);
        return farmerReportType;
    }

}
