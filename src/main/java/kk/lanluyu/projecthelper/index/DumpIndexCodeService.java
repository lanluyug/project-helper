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
 *
 * 导出indexcode， 第一行写大类 后面2~n行写二级CnName
 * example:
 *
 * 将application.properties中的dumpIndexCodeService=on
 * @author zzh
 * @date 2024-03-26
 */
@Service
@Slf4j
@ConditionalOnProperty(value = "dumpIndexCodeService", havingValue = "on")
public class DumpIndexCodeService implements CommandLineRunner {

    @Autowired
    private DumpIndexCodeMapper dumpIndexCodeMapper;

    /**
     * 获取二级框架下FrameIdPath
     * @param baseName 一级目录得名称
     * @param cnName 二级目录的名称
     * @return
     */
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

    /**
     * 获取三级框架下FrameIdPath价格
     * @param baseName 一级目录得名称
     * @param cnName 二级目录的名称
     * @return
     */
    public List<MetaData001> getMetaData001_03(String baseName, String cnName){
        LambdaQueryWrapper<MetaData001> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MetaData001::getProCode, "PD_01");
        wrapper.eq(MetaData001::getCnName, "价格");
        wrapper.eq(MetaData001::getFramePath, baseName + "|" + cnName + "|价格");
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
            FileUtil.writeLines(indexCodes, "C:\\Users\\pc\\Desktop\\indexCode.csv", StandardCharsets.UTF_8, true);
            log.info("-------------写入文件结束：{}", split[i].trim());
            log.info("-----------------------------------------------------------");
        }
    }
}
