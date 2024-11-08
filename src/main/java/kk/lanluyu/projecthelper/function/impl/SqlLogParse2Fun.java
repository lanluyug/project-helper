package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.CharSequenceUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zzh
 * @date 2024-05-06
 */
@Slf4j
public class SqlLogParse2Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {

        String[] split = runDto.getText().split("\n");
        String sql = split[0].split("==>")[1]
                .replace("Preparing:", "").trim()
                ;
        List<String> params = Arrays.stream(split[1].split("==>")[1]
                        .replace("Parameters:", "")
                        .trim().split(","))
                .map(item->{
                    if(item.contains("(Integer)")){
                        return item.split("\\(Integer\\)")[0];
                    }else if(item.contains("(Long)")){
                        return item.split("\\(Long\\)")[0];
                    }else if(item.contains("(String)")){
                        return  "'" + item.split("\\(String\\)")[0].trim() +"'";
                    }else if(item.contains("(Timestamp)")){
                        return  "'" + item.split("\\(Timestamp\\)")[0].trim() +"'";
                    }else if(item.contains("(Boolean)")){
                        boolean aFalse = item.contains("false");
                        if(aFalse) return "0";
                        return "1";
                    }
                    return "";
                })
                .collect(Collectors.toList());
        for (String param : params) {
            sql = CharSequenceUtil.replaceFirst(sql, "?", param, false);
        }
        log.info(sql);
        return new RunVo(sql);
    }
}
