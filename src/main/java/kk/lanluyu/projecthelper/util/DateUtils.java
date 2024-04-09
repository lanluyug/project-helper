package kk.lanluyu.projecthelper.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class DateUtils {

    public static String max(String str1, String str2) {
        DateTime dateTime1 = DateUtil.parse(str1);
        DateTime dateTime2 = DateUtil.parse(str2);
        int i = dateTime2.compareTo(dateTime1);
        if(i > 0){
            return str2;
        }else{
            return str1;
        }
    }

    public static String min(String str1, String str2) {
        DateTime dateTime1 = DateUtil.parse(str1);
        DateTime dateTime2 = DateUtil.parse(str2);
        int i = dateTime2.compareTo(dateTime1);
        if(i < 0){
            return str2;
        }else{
            return str1;
        }
    }
}
