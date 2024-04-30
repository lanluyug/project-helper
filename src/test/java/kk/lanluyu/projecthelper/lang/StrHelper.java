package kk.lanluyu.projecthelper.lang;

import kk.lanluyu.projecthelper.core.util.Util;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StrHelper {

    @Test
    public void printMultiString(){
        String aa =
                "select t1.*,t2.name as approve_breed_name,t2.industry_type  " +
                        "from ams_report_task_config_breed t1 " +
                        "left join ams_breed_info t2 on t1.approve_breed_id = t2.id where t1.config_id = #{configId} and t1.is_delete = 0 order by t1.order_num"
                ;
        Util.printAndCopy2Clipboard(aa);

    }

    @Test
    public void test(){
        String code = "\"select t1.*,t2.name as approve_breed_name,t2.industry_type  \" +\n" +
                "                        \"from ams_report_task_config_breed t1 \" +\n" +
                "                        \"left join ams_breed_info t2 on t1.approve_breed_id = t2.id where t1.config_id = #{configId} and t1.is_delete = 0 order by t1.order_num\"\n" +
                "                ";
        MapContext context = new MapContext();
        // 创建运行环境
        JexlEngine engine = new Engine();
        // 执行代码
        JexlExpression expression = engine.createExpression(code);
        System.out.println(expression.evaluate(context));
    }

    @Test
    public void test1() throws ScriptException {
        String code = "\"select t1.*,t2.name as approve_breed_name,t2.industry_type  \" +\n" +
                "                        \"from ams_report_task_config_breed t1 \" +\n" +
                "                        \"left join ams_breed_info t2 on t1.approve_breed_id = t2.id where t1.config_id = #{configId} and t1.is_delete = 0 order by t1.order_num\"\n" +
                "                ";
        ScriptEngineManager manager = new ScriptEngineManager();
        // 创建运行环境
        ScriptEngine nashorn = manager.getEngineByName("nashorn");
        // 执行代码
        nashorn.eval(code);

    }
}
