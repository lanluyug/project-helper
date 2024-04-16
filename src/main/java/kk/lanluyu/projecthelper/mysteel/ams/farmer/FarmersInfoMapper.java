package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * @author thq
 * @description
 * @create 2023-06-30 9:42
 */
@Mapper
public interface FarmersInfoMapper extends BaseMapper<FarmersInfo> {

    Integer insertBatchSomeColumn(Collection<FarmersInfo> entityList);
}
