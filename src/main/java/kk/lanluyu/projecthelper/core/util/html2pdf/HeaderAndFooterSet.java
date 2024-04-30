package kk.lanluyu.projecthelper.core.util.html2pdf;

import com.itextpdf.kernel.colors.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 页眉页脚字体设置类
 *
 * @author procon
 * @since 2023-10-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderAndFooterSet {

    /**
     * 页眉字体大小
     */
    private Float headerFontSize;

    /**
     * 页脚
     */
    private Float footerFontSize;

    /**
     * 字体路径
     */
    private  String fontPath;


    /**
     * 字体颜色
     */
    private Color fontColor;

    /**
     * 页眉文本（从下到上分行绘制）
     */
    private List<String> headerTextList;

    /**
     * 页脚文本(从下到上分行绘制）
     */
    private List<String> footerTextList;

    private Boolean boldFlag;

}
