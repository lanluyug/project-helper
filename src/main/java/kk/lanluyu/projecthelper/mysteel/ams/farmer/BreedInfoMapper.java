package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

/**
 * @author thq
 * @description
 * @create 2023-07-04 12:12
 */
@Mapper
public interface BreedInfoMapper extends BaseMapper<BreedInfo> {

    Integer insertBatchSomeColumn(Collection<BreedInfo> entityList);

    @Select("select old_code, code from ams_breed_info group by old_code, code")
    List<BreedInfo> queryAllCode();
}
