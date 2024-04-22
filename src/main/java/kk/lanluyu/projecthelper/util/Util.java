package kk.lanluyu.projecthelper.util;

import kk.lanluyu.projecthelper.translate.baidu.TransApi;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;

import java.io.UnsupportedEncodingException;

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

}
