package kk.lanluyu.projecthelper.core.util;

import org.junit.Test;


public class IpRegionUtilTest {


    @Test
    public void getRegionByIp() {
        String ip = "111.75.192.2";
        assert "中国/江西省/南昌市/电信".equals(IpRegionUtil.getRegionByIp(ip));

    }

    @Test
    public void getRegionByIp_juyuwang() {
        String ip = "192.168.212.123";
        assert "内网IP".equals(IpRegionUtil.getRegionByIp(ip));
    }

    @Test
    public void getRegionByIp_waiguo() {
        String ip = "1.2.3.4";
        assert "美国/华盛顿/谷歌".equals(IpRegionUtil.getRegionByIp(ip));
    }
    @Test
    public void getRegionByIp_city() {
        String ip = "111.75.192.2";
        String cityByIp = IpRegionUtil.getCityByIp(ip);
        assert "南昌市".equals(cityByIp);
    }
}