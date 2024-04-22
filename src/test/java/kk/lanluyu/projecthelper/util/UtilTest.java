package kk.lanluyu.projecthelper.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void translateByBaidu() {
        String s = Util.translateByBaidu("年份");
        assert "Year".equals(s);
    }

    @Test
    void getChineseVariableName() {
        String s = Util.getChineseVariableName("属省名称");
        assert "provinceName".equals(s);
    }
}