package kk.lanluyu.projecthelper.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zzh
 * @date 2024/04/26
 */
@AllArgsConstructor
@Getter
public enum FunctionEnum {

    /**
     * 支持的功能清单
     */
    JAVA_STRING_EVAL(1, "java代码多段+字符串拼接",
            "例如：\n输入：\"a\" + \"b\" + \"c\"\n输出：abc", 0),
    SQL_LOG_PARSE(2, "日志打印的sql ?号处理",
            "[DEBUG] 2024-04-26 15:30:00 [http-nio-9002-exec-3] c.m.a.m.F.getOriginalDataList_mpCount - ==>  Preparing: SELECT COUNT(*) FROM (SELECT temp1.*, IFNULL(temp2.escalation_type, 0) escalation_type, temp2.report_farmer_info_id, temp2.status FROM (SELECT t1.name farmer_name, t1.update_user_name, t1.year, t1.update_time, t1.id farmer_id, t1.code farmer_code, t2.breed_code FROM ams_farmers_info t1 LEFT JOIN ams_farmer_report_type t2 ON t1.`year` = t2.check_year AND t1.`code` = t2.farmer_code WHERE t1.`year` = ? AND t1.region_code = ? AND t1.farmer_source = ? AND t1.is_delete = 0 AND t2.breed_code IN (?) AND t2.report_type = ?) temp1 INNER JOIN (SELECT id report_farmer_info_id, farmer_id, IF(`status` = -1, 0, 1) escalation_type, status FROM ams_report_farmer_info WHERE report_type = ? AND industry_type = ? AND check_year = ? AND farmer_source = ? AND breed_id IN (?) AND county_code = ?) temp2 ON temp1.farmer_id = temp2.farmer_id) temp3 WHERE (temp3.status <> 1 OR temp3.status IS NULL)\n" +
                    "[DEBUG] 2024-04-26 15:30:00 [http-nio-9002-exec-3] c.m.a.m.F.getOriginalDataList_mpCount - ==> Parameters: 2024(Integer), 360121(String), 1(Integer), null, 2(Integer), 2(Integer), 0(Integer), 2024(Integer), 1(Integer), 6678(Long), 360121(String)\n"
            , 0),
    PRODUCE_EASY_EXCEL_ENTITY(3, "根据表头生成Easy Excel实体类",
            "输入一行表头，字段用制表符分隔；例如： 年份\t地区名称\t地区代码\t级别\t属省代码\t属省名称\t属市代码\t属市名称\t属县代码\t属县名称\t大中城市"
            , 0),
    FORMAT_TO_DATE(4, "转换成时间格式",
            "例如输入：1600000000", 0),
    PARSE_TO_TIMESTAMP(5, "转换成时间戳",
            "例如输入：2024-04-27 17:57:07", 0),
    ;

    private final Integer id;
    private final String name;
    private final String desc;
    private final Integer isDelete;


}
