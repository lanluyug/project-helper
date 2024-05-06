package kk.lanluyu.projecthelper.io;

import org.dromara.hutool.core.util.SystemUtil;
import org.dromara.hutool.extra.management.ManagementUtil;
import org.junit.Test;

public class FileTest {

    @Test
    public void test(){
        String s = SystemUtil.get(ManagementUtil.TMPDIR);
        assert "C:\\Users\\pc\\AppData\\Local\\Temp\\".equals(s);
    }
}
