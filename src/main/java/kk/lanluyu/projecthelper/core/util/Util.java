package kk.lanluyu.projecthelper.core.util;

import kk.lanluyu.projecthelper.function.translate.baidu.TransApi;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.extra.pinyin.PinyinUtil;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;

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

}
