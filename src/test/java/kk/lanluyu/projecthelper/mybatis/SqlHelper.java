package kk.lanluyu.projecthelper.mybatis;

import kk.lanluyu.projecthelper.util.ConsoleUtil;
import org.dromara.hutool.core.text.StrUtil;
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
        String aa =

                "==>  Preparing: SELECT farmer_id,county_code,province_code FROM ams_report_farmer_info WHERE (report_type = ? AND industry_type = ? AND check_year >= ? AND check_year <= ? AND is_delete = ?)\n" +
                        "==> Parameters: 1(Integer), 0(Integer), 2020(Integer), 2020(Integer), false(Boolean)\n";
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
                        return  "'" + item.split("\\(String\\)")[0].trim() +"'";
                    }
                    return "";
                })
                .collect(Collectors.toList());
        for (String param : params) {
            sql = StrUtil.replaceFirst(sql, "?", param, false);
        }
        ConsoleUtil.printAndCopy2Clipboard(sql);

    }
}
