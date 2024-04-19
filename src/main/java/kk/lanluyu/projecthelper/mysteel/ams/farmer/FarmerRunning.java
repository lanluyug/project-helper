package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import kk.lanluyu.projecthelper.mysteel.ams.entity.EducationEnum;
import kk.lanluyu.projecthelper.mysteel.ams.entity.ReportTypeEnum;
import kk.lanluyu.projecthelper.mysteel.ams.entity.YesOrNoEnum;
import kk.lanluyu.projecthelper.mysteel.ams.farmer.basefarmer.BasicRegionInfo;
import kk.lanluyu.projecthelper.mysteel.ams.farmer.basefarmer.BasicRegionInfoMapper;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.core.date.DateUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024-04-12
 */
@Service
@ConditionalOnProperty(value = "amsFarmer", havingValue = "on")
public class FarmerRunning  implements CommandLineRunner {

    @Autowired
    private FarmersInfoMapper farmersInfoMapper;


    @Autowired
    private FarmerReportTypeMapper farmerReportTypeMapper;

    @Autowired
    private BreedInfoMapper breedInfoMapper;

    @Autowired
    private BasicRegionInfoMapper basicRegionInfoMapper;

    @Override
    public void run(String... args) {
        insertFarmer();
    }


    /**
     * step 1
     */
    private void insertFarmer(){
        DateTime baseDate = DateUtil.parse("2024-04-16 15:00:00");
        int maxValue = 659004037;
        List<FarmerExcel> readExcel = read()
                ;
        Map<String, List<FarmerExcel>> excelMap = readExcel.stream()
                .collect(Collectors.groupingBy(FarmerExcel::getCode));

        Set<String> collect = readExcel.stream()
                .map(FarmerExcel::getCode)
                .collect(Collectors.toSet());

        Set<String> collectDb = readFromDb()
                .stream()
                .map(FarmersInfo::getCode)
                .collect(Collectors.toSet());

        Set<String> increment = Sets.difference(collect, collectDb);

        LambdaQueryWrapper<BasicRegionInfo> baseWrap = Wrappers.lambdaQuery();
        baseWrap.eq(BasicRegionInfo::getIsDelete, YesOrNoEnum.NO.getCode());
        Map<String, String> regionCodeMap = basicRegionInfoMapper.selectList(baseWrap).stream()
                .collect(Collectors.toMap(BasicRegionInfo::getName, BasicRegionInfo::getCode, (a, b) -> a));

        List<FarmersInfo> resultInsert = new ArrayList<>();
        for (String code : increment) {
            List<FarmerExcel> farmerExcels = excelMap.get(code);
            if(CollUtil.isEmpty(farmerExcels)){
                continue;
            }

            for (int i = 0; i < farmerExcels.size(); i++) {
                FarmerExcel farmerExcel = farmerExcels.get(i);
                FarmersInfo farmersInfo = BeanUtil.copyProperties(farmerExcel, FarmersInfo.class);
                if(i != 0){
                    farmersInfo.setCode(String.valueOf(++ maxValue));
                }
                farmersInfo.setCreateTime(baseDate);
                farmersInfo.setUpdateTime(baseDate);
                farmersInfo.setEducation(EducationEnum.getValue(farmerExcel.getEdu()));
                farmersInfo.setRegionCode(regionCodeMap.get(farmerExcel.getCounty()));
                farmersInfo.setCreateUserId(25L);
                farmersInfo.setUpdateUserId(25L);
                farmersInfo.setCreateUserName("admin");
                farmersInfo.setUpdateUserName("admin");
                farmersInfo.setIsDelete(false);
                farmersInfo.setSource(1);
                resultInsert.add(farmersInfo);
            }
        }
        farmersInfoMapper.insertBatchSomeColumn(resultInsert);


    }

    public void step2(List<FarmersInfo> resultInsert){
        Map<String, String> oldBreedMap = breedInfoMapper.queryAllCode().stream()
                .collect(Collectors.toMap(BreedInfo::getOldCode, BreedInfo::getCode, (a, b) -> a));
        List<FarmerReportType> typeList = new ArrayList<>();
        for (int i = 0; i < resultInsert.size(); i++) {
            List<String> cgList = new ArrayList<>();
            List<String> zbList = new ArrayList<>();
            List<String> yjList = new ArrayList<>();
            List<Integer> spList = new ArrayList<>();
            FarmersInfo farmersInfo = resultInsert.get(i);
            FarmerReportType farmerReportType = new FarmerReportType();
            farmerReportType.setFarmerCode(farmersInfo.getCode());
            String breedInfosC = farmersInfo.getBreedInfosC();
            String breedInfosZ= farmersInfo.getBreedInfosZ();
            String breedInfosS= farmersInfo.getBreedInfosS();
            String reports= farmersInfo.getReports();
            addBreedList(breedInfosC, oldBreedMap, cgList);
            addBreedList(breedInfosZ, oldBreedMap, zbList);
            addBreedList(breedInfosS, oldBreedMap, yjList);
            if(StrUtil.isNotEmpty(reports)){
                String[] split = reports.split(",");
                for (String s : split) {
                    Integer type = ReportTypeEnum.getValueByName(s);
                    if(type == null){
                        continue;
                    }
                    spList.add(type);
                }
            }

            // 添加
            if(CollUtil.isNotEmpty(cgList)){
                for (String code : cgList) {
                    FarmerReportType farmerReportType1 = FarmerReportType.build2023Default();
                    farmerReportType1.setFarmerCode(farmersInfo.getCode());
                    farmerReportType1.setReportType(2);
                    farmerReportType1.setBreedCode(code);
                    typeList.add(farmerReportType1);
                }
            }

            if(CollUtil.isNotEmpty(zbList)){
                for (String code : zbList) {
                    FarmerReportType farmerReportType1 = FarmerReportType.build2023Default();
                    farmerReportType1.setFarmerCode(farmersInfo.getCode());
                    farmerReportType1.setReportType(1);
                    farmerReportType1.setBreedCode(code);
                    typeList.add(farmerReportType1);
                }
            }

            if(CollUtil.isNotEmpty(yjList)){
                for (String code : yjList) {
                    FarmerReportType farmerReportType1 = FarmerReportType.build2023Default();
                    farmerReportType1.setFarmerCode(farmersInfo.getCode());
                    farmerReportType1.setReportType(3);
                    farmerReportType1.setBreedCode(code);
                    typeList.add(farmerReportType1);
                }
            }
            if(CollUtil.isNotEmpty(spList)){
                for (Integer type : spList) {
                    FarmerReportType farmerReportType1 = FarmerReportType.build2023Default();
                    farmerReportType1.setFarmerCode(farmersInfo.getCode());
                    farmerReportType1.setReportType(type);
                    farmerReportType1.setBreedCode("");
                    typeList.add(farmerReportType1);
                }
            }
        }
        System.out.println();
        farmerReportTypeMapper.insertBatchSomeColumn(typeList);
    }

    private void addBreedList(String breedInfos, Map<String, String> oldBreedMap, List<String> list){
        if(StrUtil.isNotEmpty(breedInfos)){
            String[] split = breedInfos.split(",");
            for (String s : split) {
                int begin = s.indexOf("(");
                int end = s.indexOf(")");
                if(begin < 0){
                    continue;
                }
                String oldBreed = s.substring(begin + 1, end);
                String breedCode = oldBreedMap.get(oldBreed);
                if(breedCode == null){
                    breedCode = oldBreed;
                }
                list.add(breedCode);
            }
        }
    }

    private List<FarmerExcel> read(){
//        String fileName = "E:\\projects\\doc\\发改委&农本\\now\\2023年种植业、饲养业、畜牧业、专项调查农户情况.xls";
        String fileName = "E:\\projects\\doc\\发改委&农本\\now\\2023兵团农户(1).xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        List<FarmerExcel> result = new ArrayList<>();
        EasyExcel.read(fileName, FarmerExcel.class,
                new PageReadListener<FarmerExcel>(result::addAll)).sheet().doRead();
        return result;
    }
    private List<FarmersInfo> readFromDb(){
        LambdaQueryWrapper<FarmersInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(FarmersInfo::getYear, 2023);
        wrapper.eq(FarmersInfo::getIsDelete, YesOrNoEnum.NO.getCode());
        return farmersInfoMapper.selectList(wrapper);
    }

}
