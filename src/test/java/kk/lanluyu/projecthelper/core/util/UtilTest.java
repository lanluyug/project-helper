package kk.lanluyu.projecthelper.core.util;

import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void translateByBaidu() {
        String s = NamingUtils.translateByBaidu("年份");
        assert "Year".equals(s);
    }

    @Test
    void getChineseVariableName() {
        String s = NamingUtils.getChineseVariableName("属省名称");
        assert "provinceName".equals(s);
    }
}