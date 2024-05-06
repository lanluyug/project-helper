package kk.lanluyu.projecthelper.function;

import kk.lanluyu.projecthelper.core.domain.YesOrNoEnum;
import kk.lanluyu.projecthelper.function.impl.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zzh
 * @date 2024/04/26
 */
@RequiredArgsConstructor
@Getter
public enum FunctionEnum {

    /**
     * 支持的功能清单
     */
    JAVA_STRING_EVAL(1, "java代码多段+字符串拼接",
            "例如：\n输入：\"a\" + \"b\" + \"c\"\n输出：abc", YesOrNoEnum.NO, new JavaStringEval1Fun()),
    SQL_LOG_PARSE(2, "日志打印的sql ?号处理",
            "复制后台日志，第一行输入一行带?的sql，第二行输入参数日志"
            , YesOrNoEnum.NO, new SqlLogParse2Fun()),
    PRODUCE_EASY_EXCEL_ENTITY(3, "根据表头生成Easy Excel实体类",
            "输入一行表头，字段用制表符分隔；例如： 年份\t地区名称\t地区代码\t级别\t属省代码\t属省名称\t属市代码\t属市名称\t属县代码\t属县名称\t大中城市"
            , YesOrNoEnum.NO, new ProduceEasyExcelEntity3Fun()),
    FORMAT_TO_DATE(4, "转换成时间格式",
            "例如输入：1600000000", YesOrNoEnum.NO, new FormatToDate4Fun()),
    PARSE_TO_TIMESTAMP(5, "转换成时间戳",
            "例如输入：2024-04-27 17:57:07", YesOrNoEnum.NO, new ParseToTimestamp5Fun()),
    HTML_TO_PDF(6, "HTML转PDF", "输入完整HTML", YesOrNoEnum.NO, new HtmlToPdf6Fun()),
    IP_TO_REGION(7, "ip归属地查询",
            "例如输入：111.75.192.2", YesOrNoEnum.NO, new IpToRegion7Fun()),
    ;
    /**
     * 8、cron表达式
     */

    private final Integer id;
    private final String name;
    private final String desc;
    private final YesOrNoEnum isDelete;
    private HpExecutor singleExecutor;
    private HpExporter singleExporter;

    FunctionEnum(Integer id, String name, String desc, YesOrNoEnum isDelete, HpExecutor singleExecutor){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.isDelete = isDelete;
        this.singleExecutor = singleExecutor;
    }

    FunctionEnum(Integer id, String name, String desc, YesOrNoEnum isDelete, HpExporter singleExporter){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.isDelete = isDelete;
        this.singleExporter = singleExporter;
    }

}
