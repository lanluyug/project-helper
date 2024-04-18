package kk.lanluyu.projecthelper.util;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;

/**
 * @author zzh
 * @date 2024-04-10
 */
@Slf4j
public class ConsoleUtil {


    public static void printAndCopy2Clipboard(String text){
        log.info(text);
        ClipboardUtil.setStr(text);
    }
}