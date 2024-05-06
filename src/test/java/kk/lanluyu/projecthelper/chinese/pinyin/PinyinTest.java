package kk.lanluyu.projecthelper.chinese.pinyin;

import kk.lanluyu.projecthelper.core.util.NamingUtils;
import kk.lanluyu.projecthelper.core.util.TextUtils;
import org.dromara.hutool.core.io.file.FileUtil;
import org.dromara.hutool.core.io.resource.Resource;
import org.dromara.hutool.core.io.resource.ResourceUtil;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PinyinTest {

    @Test
    public void test(){
        String pinyin = PinyinUtil.getPinyin("每亩总成本：统一工价（国家）");
        System.out.println(pinyin);
        String firstLetter = PinyinUtil.getFirstLetter("每亩总成本：统一工价（国家）", "");
        System.out.println(firstLetter);
    }

    /**
     * 按照id与名称，批量生成枚举构造实例
     */
    @Test
    public void convertTsv2Enum(){
        Resource resource = ResourceUtil.getResource("static/pinyin2Enum");
        List<String> lines = FileUtil.readLines(resource.getUrl(), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        Set<String> enumNameSet = new HashSet<>();
        for (String line : lines) {
            String[] split = line.split("\t");
            String id = split[0];
            String name = split[1];
            String firstLetter = NamingUtils.getFirstLetterUnrepeated(name, enumNameSet);
            String enumConstructLine = firstLetter.toUpperCase() + "(" + id + "L, " + "\"" + name + "\"),\n";
            sb.append(enumConstructLine);
        }
        TextUtils.printAndCopy2Clipboard(sb);
    }
}
