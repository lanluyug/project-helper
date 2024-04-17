package kk.lanluyu.projecthelper.mysteel.ams.farmer;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface FarmerReportTypeMapper extends BaseMapper<FarmerReportType> {

    Integer insertBatchSomeColumn(Collection<FarmerReportType> entityList);

}
