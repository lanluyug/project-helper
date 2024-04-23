package kk.lanluyu.projecthelper.util;

import kk.lanluyu.projecthelper.translate.baidu.TransApi;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.digest.DigestUtil;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzh
 * @date 2024-04-10
 */
@Slf4j
public class Util {

    public static void printAndCopy2Clipboard(CharSequence text){
        log.info(text.toString());
        ClipboardUtil.setStr(text.toString());
    }

    public static String translateByBaidu(String query){
        TransApi transApi = new TransApi();
        try {
           return transApi.getTransResult(query, "auto", "en");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return RandomUtil.randomString(6);
    }

    public static String getChineseVariableName(String chinese){
        String english = translateByBaidu(chinese);
        return StrUtil.toCamelCase(StrUtil.toCamelCase(english, ' '), '-');
    }

    public static String getRandomName(){
        return StrUtil.toCamelCase(RandomUtil.randomString(6));
    }


    public static String getFirstLetter(String text){
        return PinyinUtil.getFirstLetter(text, "");
    }

    /**
     * 获取不重名的中文拼音前缀
     * @param chinese
     * @param existsNamePool
     * @return
     */
    public static String getFirstLetterUnrepeated(String chinese, Set<String> existsNamePool){
        String firstLetter = getFirstLetter(chinese).replace("（", "_")
                .replace("-", "_")
                .replace("%", "")
                .replace("、", "_")
                .replace("：", "_")
                .replace("）", "");
        while (existsNamePool.contains(firstLetter)){
            String lastChar = firstLetter.substring(firstLetter.length() - 1);
            if(StrUtil.isNumeric(lastChar)){
                int i = Integer.parseInt(lastChar);
                if(i == 9) {
                    throw new UnsupportedOperationException("不支持那么多重名");
                }
                ++ i;
                firstLetter = firstLetter.substring(0, firstLetter.length() - 1) + i;
            }else{
                firstLetter = firstLetter + "_1";
            }
        }
        existsNamePool.add(firstLetter);
        return firstLetter;
    }

}
