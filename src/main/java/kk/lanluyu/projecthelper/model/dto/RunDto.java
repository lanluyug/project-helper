package kk.lanluyu.projecthelper.model.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author zzh
 * @date 2024-04-26
 */
@Data
public class RunDto {

    private String text;

    private Integer id;

    private JSONObject settings;
}
