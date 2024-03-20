package kk.lanluyu.projecthelper.util;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

public class ConsoleUtil {


    public static void printAndCopy2Clipboard(String text){
        System.out.println(text);
        ClipboardUtil.setStr(text);
    }
}
