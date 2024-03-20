package kk.lanluyu.projecthelper.mybatis;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.StrUtil;
import kk.lanluyu.projecthelper.util.ConsoleUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        String aa = """
                ==>  Preparing: SELECT farmer_id FROM ams_report_farmer_info WHERE (report_type = ? AND industry_type = ? AND check_year = ? AND county_code = ? AND farmer_source = ? AND check_month = ? AND (status IN (?,?,?) OR (is_reject = ?)) AND breed_id = ?)
                                ==> Parameters: 2(Integer), 1(Integer), 2024(Integer), 360122(String), 1(Integer), 0(Integer), 1(Integer), 2(Integer), -2(Integer), 1(Integer), 6111(Long)
                """
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
                .toList();
        for (String param : params) {
            sql = StrUtil.replaceFirst(sql, "?", param);
        }
        ConsoleUtil.printAndCopy2Clipboard(sql);

    }
}
