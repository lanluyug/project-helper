package kk.lanluyu.projecthelper.core.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;
import kk.lanluyu.projecthelper.core.domain.CommonException;
import kk.lanluyu.projecthelper.core.util.html2pdf.CustomTagWorkerFactory;
import kk.lanluyu.projecthelper.core.util.html2pdf.HeaderAndFooterSet;
import kk.lanluyu.projecthelper.core.util.html2pdf.HeaderFooterHandler;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.io.IoUtil;
import org.dromara.hutool.core.io.file.FileUtil;
import org.dromara.hutool.core.util.SystemUtil;
import org.dromara.hutool.extra.management.ManagementUtil;
import org.dromara.hutool.extra.management.OsInfo;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author zzh
 * @date 2024/05/04
 */
@Slf4j
public class Html2PdfUtils {

    private Html2PdfUtils(){}

    public static final String SUFFIX_PDF = ".pdf";

    /**
     * html转pdf plan B
     * @param htmlContent
     * @param fileName
     * @param basePath
     */
    public static void createPdf(String htmlContent, String fileName, String basePath) {
        try {
            ConverterProperties props = new ConverterProperties();
            FontProvider fp = new FontProvider();
            fp.addDirectory(basePath);

            PdfDocument pdf = new PdfDocument(new PdfWriter(fileName));
            pdf.setDefaultPageSize(PageSize.A4);

            Document document = new Document(pdf);
            document.setLeftMargin(0);
            document.setRightMargin(0);

            props.setFontProvider(fp);
            if (!StringUtils.isEmpty(htmlContent)) {
                List<IElement> elements = HtmlConverter.convertToElements(htmlContent, props);

                for (IElement element : elements) {
                    // 分页符
                    if (element instanceof HtmlPageBreak) {
                        document.add((HtmlPageBreak) element);
                    } else {
                        document.add((IBlockElement) element);
                    }
                }
                document.close();
            } else {
                document.close();
            }

        } catch (Exception e) {
            log.error("createPdf fail:{}", e);
            throw new CommonException("500", "createPdf fail");
        }
    }

    public static String html2Pdf(String html, String fileName){
        String baseFontPath = getDefaultFontPath();
        if(StringUtils.isEmpty(fileName)){
            fileName= SystemUtil.get(ManagementUtil.TMPDIR) + IdUtil.fastSimpleUUID() + ".pdf";
        }
        html2Pdf(html, fileName, baseFontPath, null, false);
        return fileName;
    }
    public static void html2Pdf(String html, HttpServletResponse response){
        String tempPath = html2Pdf(html, "");
        try {
            IoUtil.copy(Files.newInputStream(Paths.get(tempPath)), response.getOutputStream());
        } catch (IOException e) {
            log.error("html2Pdf导出失败");
            throw new CommonException("html2Pdf导出失败");
        }finally {
            FileUtil.del(tempPath);
        }
    }

    private static String getDefaultFontPath(){
        OsInfo osInfo = ManagementUtil.getOsInfo();
        if(osInfo.isLinux()){
            /*
            Linux 安装字体需要root用户执行下面三个命令
            mkfontscale
            mkfontdir
            fc-cache -fv
             */
            return  "/usr/share/fonts";
        }
        else if(osInfo.isWindows()){
            return  "C:\\Windows\\Fonts";
        }else{
            throw new UnsupportedOperationException("只支持windows和Linux系统");
        }
    }

    /**
     * @param html        html文本
     * @param fileName           文件名
     * @param baseFontPath           字体文件夹路径
     * @param headerAndFooterSet 页眉页脚相关设置不传不生成
     * @param isAbstract         是否为摘要页
     */
    public static void html2Pdf(String html,
                                   String fileName,
                                   String baseFontPath,
                                   HeaderAndFooterSet headerAndFooterSet, boolean isAbstract) {

        try {
            ConverterProperties props = new ConverterProperties();
            FontProvider fp = new FontProvider();
            fp.addDirectory(baseFontPath);
            PdfDocument doc = new PdfDocument(new PdfWriter(fileName));
            doc.setDefaultPageSize(PageSize.A4);
            props.setTagWorkerFactory(new CustomTagWorkerFactory());
            props.setFontProvider(fp);
            if(headerAndFooterSet != null){
                //添加页眉页脚
                HeaderFooterHandler handler = new HeaderFooterHandler(true, headerAndFooterSet, isAbstract);
                doc.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
            }
            HtmlConverter.convertToPdf(html, doc, props);
        } catch (FileNotFoundException e) {
            log.error("html转pdf异常:{}", e.getMessage(), e);
        }
    }

    /**
     * 将两个个PDF合并成一个PDF
     *
     * @param pdfFile1       源PDF路径1
     * @param pdfFile2       源PDF路径2
     * @param outputFileName 合并后输出的PDF文件名
     */
    public static void mergePdf(String pdfFile1, String pdfFile2, String outputFileName, Integer pdf2PageNum) {

        PdfDocument pdfDocument;
        try {
            pdfDocument = new PdfDocument(new PdfReader(pdfFile1), new PdfWriter(outputFileName));
            PdfDocument pdfDocument2 = new PdfDocument(new PdfReader(pdfFile2));
            PdfMerger merger = new PdfMerger(pdfDocument);
            merger.merge(pdfDocument2, 1, Optional.ofNullable(pdf2PageNum).orElse(pdfDocument2.getNumberOfPages()));
            pdfDocument2.close();
            pdfDocument.close();
        } catch (IOException e) {
            log.error("合并pdf异常{}", e.getMessage(), e);
            throw new CommonException("合并pdf异常");
        }

    }


}