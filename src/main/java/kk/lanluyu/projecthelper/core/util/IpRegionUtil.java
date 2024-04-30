package kk.lanluyu.projecthelper.core.util;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.io.resource.ResourceUtil;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzh
 * @date 2024-04-29
 */
@Slf4j
public class IpRegionUtil {

    private IpRegionUtil(){}

    /**
     * 可并发使用，用整个 xdb 数据缓存创建的查询对象可以安全的用于并发
     */
    private static Searcher searcher = null;

    /**
     * static块无法加载jar中的资源
     */
    public static void init(){
        if(searcher != null){
            return;
        }
        try {
            searcher = Searcher.newWithBuffer(ResourceUtil.readBytes("static/ip2region.xdb"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String getRegionByIp(String ip){
        init();
        String region = "";
        try {
            region = searcher.search(ip);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("region :{}", region);
        String[] split = region.split("\\|");
        if(split.length != 5){
            return "";
        }
        if("内网IP".equals(split[4])){
            return "内网IP";
        }
        return Arrays.stream(split).filter(item -> !"0".equals(item)).collect(Collectors.joining("/"));
    }

    public static String getCityByIp(String ip){
        init();
        String region = "";
        try {
            region = searcher.search(ip);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("region :{}", region);
        String[] split = region.split("\\|");
        if(split.length != 5){
            return "";
        }
        if("内网IP".equals(split[4])){
            return "";
        }
        List<String> collect = Arrays.stream(split).filter(item -> !"0".equals(item))
                .collect(Collectors.toList());
        if(collect.size() < 2){
            return "";
        }
        return collect.get(collect.size() - 2);
    }
}
