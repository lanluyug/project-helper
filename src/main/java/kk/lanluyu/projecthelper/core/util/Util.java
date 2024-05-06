package kk.lanluyu.projecthelper.core.util;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;

/**
 * @author zzh
 * @date 2024-04-10
 */
@Slf4j
public class Util {

    private Util(){}

    public static void printAndCopy2Clipboard(CharSequence text){
        log.info(text.toString());
        ClipboardUtil.setStr(text.toString());
    }

}
