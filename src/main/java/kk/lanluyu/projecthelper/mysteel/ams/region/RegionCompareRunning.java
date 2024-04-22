package kk.lanluyu.projecthelper.mysteel.ams.region;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import kk.lanluyu.projecthelper.mysteel.ams.entity.YesOrNoEnum;
import kk.lanluyu.projecthelper.mysteel.ams.farmer.basefarmer.BasicRegionInfo;
import kk.lanluyu.projecthelper.mysteel.ams.farmer.basefarmer.BasicRegionInfoMapper;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024-04-22
 */
@Service
@ConditionalOnProperty(value = "amsRegion", havingValue = "on")
public class RegionCompareRunning implements ApplicationRunner {

    @Autowired
    private BasicRegionInfoMapper basicRegionInfoMapper;

    @Override
    public void run(ApplicationArguments args) {
        LambdaQueryWrapper<BasicRegionInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BasicRegionInfo::getIsDelete, YesOrNoEnum.NO.getCode());
        List<BasicRegionInfo> allRegion = basicRegionInfoMapper.selectList(wrapper);
        List<Region2023Excel> excelData = read();
        Set<String> codes = excelData.stream()
                .map(row -> {
                    String code = row.getRegionCode();
                    if (StrUtil.isEmpty(code) || code.length() < 6) {
                        return null;
                    }
                    return code.substring(0, 6);
                })
                .collect(Collectors.toSet());
        Set<String> allCodes = allRegion.stream().map(BasicRegionInfo::getCode).collect(Collectors.toSet());
        Set<String> incrementCodes = Sets.difference(codes, allCodes);
        System.out.println(incrementCodes.size());

    }

    private List<Region2023Excel> read(){
        String fileName = "E:\\projects\\doc\\发改委&农本\\2023年-国家-地区名称及代码.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        List<Region2023Excel> result = new ArrayList<>();
        EasyExcel.read(fileName, Region2023Excel.class,
                new PageReadListener<Region2023Excel>(result::addAll)).sheet().doRead();
        return result;
    }
}
