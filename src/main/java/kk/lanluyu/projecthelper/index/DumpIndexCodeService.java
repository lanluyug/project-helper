package kk.lanluyu.projecthelper.index;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzh
 * @date 2024-03-26
 */
@Service
@Slf4j
@ConditionalOnProperty("dumpIndexCodeService")
public class DumpIndexCodeService implements CommandLineRunner {

    @Autowired
    private DumpIndexCodeMapper dumpIndexCodeMapper;
    public List<MetaData001> getMetaData001(String baseName, String cnName){
        LambdaQueryWrapper<MetaData001> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MetaData001::getProCode, "PD_01");
        wrapper.eq(MetaData001::getCnName, cnName);
        wrapper.eq(MetaData001::getFramePath, baseName + "|" + cnName);
        List<MetaData001> metaData001s = dumpIndexCodeMapper.selectList(wrapper);
        if(metaData001s.isEmpty()){
            log.error("导出失败:{}", cnName);
        }

        return metaData001s;
    }

    @Override
    public void run(String... args) {
        String s = ResourceUtil.readStr("static/indexCnName", StandardCharsets.UTF_8);
        String[] split = s.split("\r\n");
        String baseName = split[0];
        for (int i = 1; i < split.length; i++) {
            List<MetaData001> metaData001 = getMetaData001(baseName, split[i].trim());
            if (CollUtil.isEmpty(metaData001)) continue;
            log.info("-------------开始导出：{}", split[i].trim());
            List<String> indexCodes = new ArrayList<>();
            for (MetaData001 data001 : metaData001) {
                indexCodes.addAll(dumpIndexCodeMapper.queryIndexCodes(data001.getFrameIdPath() + "%"));
            }
            log.info("-------------查询结束：{}", split[i].trim());
            FileUtil.writeLines(indexCodes, "C:\\Users\\pc\\Desktop\\湘钢指标\\indexCode.csv", StandardCharsets.UTF_8, true);
            log.info("-------------写入文件结束：{}", split[i].trim());
            log.info("-----------------------------------------------------------");
        }
    }
}
