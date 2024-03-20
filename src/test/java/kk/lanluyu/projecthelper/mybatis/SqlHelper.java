package kk.lanluyu.projecthelper.mybatis;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sql {


    @Test
    public void completeSql(){
        String aa = "==>  Preparing: SELECT * From ( SELECT t1.id,t1.farmer_id,t1.task_id,t1.farmer_source,CONCAT(province_name,'-',city_name,'-',county_name) region_info, county_code,t2.create_user_name, DATE_FORMAT(t2.create_time,'%Y-%m-%d %H:%i:%s') AS create_time, IFNULL(t2.audit_type,5) audit_type,t1.industry_type FROM ams_report_farmer_info t1 INNER JOIN ams_audit_flow t2 ON t1.id = t2.report_farmer_id WHERE t1.report_type = ? AND t1.industry_type = ? AND t1.check_year = ? AND t1.`status` in( 1,-2) AND t1.task_id=? AND t1.breed_id=? AND t2.audit_from = 1 AND t1.farmer_id in ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) and (audit_user_id =? AND audit_type = ?) AND t1.id in ( ? , ? , ? ) ) temp1 GROUP BY id LIMIT ?\n" +
                "==> Parameters: 1(Integer), 0(Integer), 2024(Integer), 431(Long), 6059(Long), 545861(Long), 545873(Long), 545874(Long), 545875(Long), 545876(Long), 545877(Long), 545878(Long), 545879(Long), 545880(Long), 545881(Long), 545882(Long), 544783(Long), 544784(Long), 544785(Long), 545886(Long), 545887(Long), 545888(Long), 545889(Long), 545890(Long), 545891(Long), 545892(Long), 562343(Long), 544786(Long), 544787(Long), 544788(Long), 545953(Long), 545893(Long), 545894(Long), 545895(Long), 545896(Long), 545897(Long), 545898(Long), 545899(Long), 545900(Long), 545901(Long), 545902(Long), 545903(Long), 545904(Long), 545905(Long), 545906(Long), 545907(Long), 545908(Long), 545909(Long), 545910(Long), 545911(Long), 545912(Long), 544780(Long), 544781(Long), 544782(Long), 544777(Long), 544778(Long), 544779(Long), 545544(Long), 545545(Long), 545546(Long), 545547(Long), 545548(Long), 545549(Long), 545550(Long), 545551(Long), 545552(Long), 545553(Long), 545554(Long), 545555(Long), 545556(Long), 545557(Long), 545558(Long), 545559(Long), 545560(Long), 545561(Long), 545562(Long), 545563(Long), 545564(Long), 545565(Long), 545566(Long), 545567(Long), 545568(Long), 545569(Long), 128(Long), 7(Integer), 720994(Long), 720993(Long), 0(Long), 10(Long)\n"
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
        System.out.println(sql);
        ClipboardUtil.setStr(sql);

    }
}
