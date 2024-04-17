package kk.lanluyu.projecthelper.mysteel.ams.farmer;

import com.baomidou.mybatisplus.annotation.TableName;
import kk.lanluyu.projecthelper.mysteel.ams.entity.BaseDTO;
import lombok.Data;

@Data
@TableName("ams_breed_info")
public class BreedInfo extends BaseDTO {
    /**
     * 品种代码
     */
    private String code;
    /**
     * 老系统品种代码
     */
    private String oldCode;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 品种名称
     */
    private String name;
    /**
     * 品种类型（0分类品种 1：汇总品种 2：基础品种）
     */
    private Integer type;
    /**
     * 品种归属(0:国家品种 1：地方特色品种）
     */
    private Integer belongType;
    /**
     * 所属行业(0:种植业 1：养殖业 2：畜牧业）
     */
    private Integer industryType;

    /**
     * 是否通过审核 5审核中  6驳回 7通过;
     */
    private Integer isAudit;

    /**
     * 是否加权 0：不是 1：是
     */
    private Integer isWeight;

    /**
     * 是否大中城市 是否大中城市（0：不是  1：是）
     */
    private Integer isLargerCity;
    /**
     * 汇总内容 汇总内容(多个用，分开）
     */
    private String collectContent;
    /**
     * 所属分类id
     */
    private String classCode;
}
