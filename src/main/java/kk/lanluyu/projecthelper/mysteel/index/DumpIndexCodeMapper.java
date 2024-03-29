package kk.lanluyu.projecthelper.mysteel.index;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzh
 * @date 2024-03-28
 */
@Mapper
@Repository
public interface DumpIndexCodeMapper extends BaseMapper<MetaData001> {

    List<String> queryIndexCodes(@Param("frameIdPath") String frameIdPath);
}
