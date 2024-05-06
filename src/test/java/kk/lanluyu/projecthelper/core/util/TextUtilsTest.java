package kk.lanluyu.projecthelper.core.util;

import org.junit.jupiter.api.Test;

import java.util.List;

class TextUtilsTest {


    @Test
    void splitLine() {
        List<String> strings = TextUtils.splitField("a   c    b");
        assert strings.size() == 3;
    }
    @Test
    void splitLine1() {
        List<String> strings = TextUtils.splitField("a,   c,    b");
        assert strings.get(2).equals("b");
    }
}