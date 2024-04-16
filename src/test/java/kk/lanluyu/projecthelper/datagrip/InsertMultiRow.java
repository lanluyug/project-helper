package kk.lanluyu.projecthelper.datagrip;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.dromara.hutool.core.io.file.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class InsertMultiRow {

    @Test
    public void splitBigInsertSql() throws IOException {
        int segSize = 30_0000;
        String path = "E:\\data\\uat\\ams_ams_farmer_ref_audit.sql";
        String pathExport = "E:\\data\\uat\\ams_ams_farmer_ref_audit_new";
        BufferedReader reader = FileUtil.getReader(new File(path), StandardCharsets.UTF_8);
        AtomicInteger counter = new AtomicInteger(0);
        AtomicInteger batchNum = new AtomicInteger(0);
        List<String> temp = new ArrayList<>();
        String prefix = "insert into ams_farmer_ref_audit (id, part_key, report_farmer_id, index_audit_id, index_value, country_audit_desc, country_audit_result, province_audit_desc, province_audit_result, correct_desc, correct_result, create_user_id, create_user_name, create_time, update_user_id, update_user_name, update_time, is_delete)\n" +
                "values ";
        reader.lines().forEach(line -> {
            if(counter.getAndAdd(1) > segSize) {
                line = line.substring(0, line.length() - 1) + ";";
                temp.add(line);
                FileUtil.writeLines(temp, pathExport + (batchNum.addAndGet(1) / 30) + ".sql", StandardCharsets.UTF_8, true);
                log.info("第{}批次完成..", batchNum.get());
                counter.set(0);
                temp.clear();
                temp.add(prefix);
            }else{
                temp.add(line);
            }
        });
        FileUtil.writeLines(temp, pathExport + (batchNum.addAndGet(1) / 30) + ".sql", StandardCharsets.UTF_8, true);

    }

}
