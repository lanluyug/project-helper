package kk.lanluyu.projecthelper.mybatis;

import cn.hutool.core.util.StrUtil;
import kk.lanluyu.projecthelper.util.ConsoleUtil;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024-03-07
 */
public class SqlHelper {


    /**
     * 将mybatis打印的日志中?替换成完整的sql
     */
    @Test
    public void completeSql(){
        String aa = "==>  Preparing: SELECT COUNT(*) FROM (SELECT * FROM (SELECT temp1.*, IFNULL(temp2.escalation_type, 0) escalation_type, temp2.report_farmer_info_id, temp2.status FROM (SELECT t1.name farmer_name, t3.update_user_name, t3.update_time, t3.id original_id, t1.id farmer_id, t3.breed_code FROM ams_farmers_info t1 LEFT JOIN ams_farmer_report_type t2 ON t1.`year` = t2.check_year AND t1.`code` = t2.farmer_code LEFT JOIN ams_farmer_info_original t3 ON t1.code = t3.farmer_code AND t2.breed_code = t3.breed_code WHERE t1.`year` = ? AND t1.region_code = ? AND t1.farmer_source = ? AND t3.breed_code IN (?) AND t2.report_type = ? AND t2.breed_code = ?) temp1 LEFT JOIN (SELECT id report_farmer_info_id, farmer_id, IF(`status` = -1, 0, 1) escalation_type, status FROM ams_report_farmer_info WHERE report_type = ? AND industry_type = ? AND check_year = ? AND farmer_source = ? AND breed_id IN (?) AND county_code = ?) temp2 ON temp1.farmer_id = temp2.farmer_id) temp3 GROUP BY original_id, update_time) TOTAL\n" +
                "                ==> Parameters: 2024(Integer), 230111(String), 1(Integer), 01000060(String), 2(Integer), 01000060(String), 2(Integer), 0(Integer), 2024(Integer), 1(Integer), 6957(Long), 230111(String)\n" +
                "                           "
                ;
        String[] split = aa.split("\n");
        String sql = split[0].replace("==>", "")
                .replace("Preparing:", "")
                ;
        List<String> params = Arrays.stream(split[1].replace("==>", "")
                        .replace("Parameters:", "")
                        .trim().split(","))
                .map(item->{
                    if(item.contains("(Integer)")){
                        return item.split("\\(Integer\\)")[0];
                    }else if(item.contains("(Long)")){
                        return item.split("\\(Long\\)")[0];
                    }else if(item.contains("(String)")){
                        return  "'" + item.split("\\(String\\)")[0] +"'";
                    }
                    return "";
                })
                .collect(Collectors.toList());
        for (String param : params) {
            sql = StrUtil.replaceFirst(sql, "?", param);
        }
        ConsoleUtil.printAndCopy2Clipboard(sql);

    }
}
