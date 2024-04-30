package kk.lanluyu.projecthelper.core.util;

import com.itextpdf.kernel.colors.DeviceRgb;
import kk.lanluyu.projecthelper.core.util.html2pdf.HeaderAndFooterSet;
import org.dromara.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Html2PdfUtilsTest {

    String destFileName = "test.pdf";
    String baseFontPath = "D:\\fonts\\";

    String fontName = "宋体.ttf";
    @Test
    void html2Pdf() {
        String html = "<html><body>\n" +
                "<p style= \"font-family:KAITI;font-weight: 900;\">行楷</p>\n" +
                "行楷\n" +
                "</body>\n" +
                "</html>";

        Html2PdfUtils.html2Pdf(html, destFileName, baseFontPath, null, false);
    }

    private HeaderAndFooterSet getHeaderAndFooterSet(Boolean isAbstract) {
        HeaderAndFooterSet headerAndFooter = new HeaderAndFooterSet();
        headerAndFooter.setFontPath(baseFontPath + fontName);
        List<String> headerList = new ArrayList<>();
        String dateStr = DateUtil.formatDate(new Date());
        String[] weekStr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int i = DateUtil.dayOfWeek(new Date());
        String week = weekStr[i - 1];
        if (Boolean.TRUE.equals(isAbstract)) {
            headerList.add(dateStr + week);
            //加粗最后一个字会丢失
            headerList.add("研究中心研究报告。");
        } else {
            //加粗最后一个字会丢失
            headerList.add("研究中心研究报告。");
        }
        headerAndFooter.setHeaderTextList(headerList);
        List<String> footerList = new ArrayList<>();
        if (Boolean.FALSE.equals(isAbstract)) {
            footerList.add("广州期货股份有限公司提醒广大投资者：期市有风险 入市需谨慎慎");
        } else {
            //斜体最后一个字会丢失
            footerList.add("本报告中所有观点仅供参考，请投资者务必阅读正文后的免责声明。。");
        }
        headerAndFooter.setHeaderTextList(headerList);
        headerAndFooter.setFooterTextList(footerList);
        headerAndFooter.setHeaderFontSize(10.5f);
        headerAndFooter.setFooterFontSize(10.5f);
        headerAndFooter.setBoldFlag(true);
        headerAndFooter.setFontColor(new DeviceRgb(131, 60, 11));
        headerAndFooter.setBoldFlag(true);
        return headerAndFooter;
    }
}