package kk.lanluyu.projecthelper.chinese.pinyin;

import kk.lanluyu.projecthelper.util.Util;
import org.dromara.hutool.core.io.file.FileUtil;
import org.dromara.hutool.core.io.resource.Resource;
import org.dromara.hutool.core.io.resource.ResourceUtil;
import org.dromara.hutool.core.text.StrUtil;
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
        Set<Object> enumNameSet = new HashSet<>();
        for (String line : lines) {
            String[] split = line.split("\t");
            String id = split[0];
            String name = split[1];
            String firstLetter = PinyinUtil.getFirstLetter(name, "")
                    .replace("（", "_")
                    .replace("-", "_")
                    .replace("%", "")
                    .replace("、", "_")
                    .replace("：", "_")
                    .replace("）", "")
                    .toUpperCase();
            while (enumNameSet.contains(firstLetter)){
                String lastChar = firstLetter.substring(firstLetter.length() - 1);
                if(StrUtil.isNumeric(lastChar)){
                    int i = Integer.parseInt(lastChar);
                    if(i == 9) throw new UnsupportedOperationException("不支持那么多重名");
                    ++ i;
                    firstLetter = firstLetter.substring(0, firstLetter.length() - 1) + i;
                }else{
                    firstLetter = firstLetter + "_1";
                }
            }
            enumNameSet.add(firstLetter);
            String enumConstructLine = firstLetter + "(" + id + "L, " + "\"" + name + "\"),\n";
            sb.append(enumConstructLine);
        }
        Util.printAndCopy2Clipboard(sb);
    }
}
