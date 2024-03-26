package kk.lanluyu.projecthelper.lang;

import kk.lanluyu.projecthelper.util.ConsoleUtil;
import org.junit.Test;

public class StrHelper {

    @Test
    public void printMultiString(){
        String aa =
                "select t1.*,t2.name as approve_breed_name,t2.industry_type  " +
                        "from ams_report_task_config_breed t1 " +
                        "left join ams_breed_info t2 on t1.approve_breed_id = t2.id where t1.config_id = #{configId} and t1.is_delete = 0 order by t1.order_num"
                ;
        ConsoleUtil.printAndCopy2Clipboard(aa);

    }
}
