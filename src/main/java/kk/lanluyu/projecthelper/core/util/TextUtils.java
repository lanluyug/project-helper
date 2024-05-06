package kk.lanluyu.projecthelper.core.util;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.swing.clipboard.ClipboardUtil;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024-04-10
 */
@Slf4j
public class TextUtils {

    private TextUtils(){}

    public static void printAndCopy2Clipboard(CharSequence text){
        log.info(text.toString());
        ClipboardUtil.setStr(text.toString());
    }

    public static List<String> splitField(String text){
        if(text == null || text.isEmpty()){
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        if(text.contains("\t")){
            list = Arrays.asList(text.split("\t"));
        }
        else if(text.contains(" ")){
            list = Arrays.asList(text.split("\\s+"));
        }
        else if(text.contains(",")){
            list = Arrays.asList(text.split(","));
        }

        return list.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

    }

}
