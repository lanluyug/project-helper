package kk.lanluyu.projecthelper.mybatis;

import kk.lanluyu.projecthelper.core.util.TextUtils;
import kk.lanluyu.projecthelper.function.impl.SqlLogParse2Fun;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import org.junit.Test;

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
        String input =
                "[DEBUG] 2024-04-26 15:30:00 [http-nio-9002-exec-3] c.m.a.m.F.getOriginalDataList_mpCount - ==>  Preparing: SELECT COUNT(*) FROM (SELECT temp1.*, IFNULL(temp2.escalation_type, 0) escalation_type, temp2.report_farmer_info_id, temp2.status FROM (SELECT t1.name farmer_name, t1.update_user_name, t1.year, t1.update_time, t1.id farmer_id, t1.code farmer_code, t2.breed_code FROM ams_farmers_info t1 LEFT JOIN ams_farmer_report_type t2 ON t1.`year` = t2.check_year AND t1.`code` = t2.farmer_code WHERE t1.`year` = ? AND t1.region_code = ? AND t1.farmer_source = ? AND t1.is_delete = 0 AND t2.breed_code IN (?) AND t2.report_type = ?) temp1 INNER JOIN (SELECT id report_farmer_info_id, farmer_id, IF(`status` = -1, 0, 1) escalation_type, status FROM ams_report_farmer_info WHERE report_type = ? AND industry_type = ? AND check_year = ? AND farmer_source = ? AND breed_id IN (?) AND county_code = ?) temp2 ON temp1.farmer_id = temp2.farmer_id) temp3 WHERE (temp3.status <> 1 OR temp3.status IS NULL)\n" +
                        "[DEBUG] 2024-04-26 15:30:00 [http-nio-9002-exec-3] c.m.a.m.F.getOriginalDataList_mpCount - ==> Parameters: 2024(Integer), 360121(String), 1(Integer), null, 2(Integer), 2(Integer), 0(Integer), 2024(Integer), 1(Integer), 6678(Long), 360121(String)\n"

                ;
        RunDto runDto = new RunDto();
        runDto.setText(input);
        SqlLogParse2Fun sqlLogParse2Fun = new SqlLogParse2Fun();
        RunVo execute = sqlLogParse2Fun.execute(runDto);
        TextUtils.printAndCopy2Clipboard(execute.getResult());

    }
}
