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
import org.springframework.util.StringUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Slf4j
public class Html2PdfUtils {

    private static final String SUFFIX_PDF = ".pdf";
    public static final String BASE_PATH = System.getProperty("user.dir");

    /**
     * @param htmlContent
     * @param fileName    文件名
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


    /**
     * @param htmlContent        html文本
     * @param fileName           文件名
     * @param basePath           字体文件夹路径
     * @param headerAndFooterSet 页眉页脚相关设置不传不生成
     * @param isAbstract         是否为摘要页
     */
    public static void html2Pdf(String htmlContent,
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
            HtmlConverter.convertToPdf(htmlContent, doc, props);
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
            throw new RuntimeException(e);
        }

    }


}