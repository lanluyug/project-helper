package kk.lanluyu.projecthelper.core.util.html2pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.io.IoUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;


/**
 * <p>页眉页脚处理类</p>
 *
 * @author Procon
 * @since 2023/10/25
 */
@Data
@Slf4j
public class HeaderFooterHandler implements IEventHandler {

    /**
     * 左侧留白
     */
    public static final float MARGIN_LEFT = 20;
    /**
     * 页脚线到底部距离
     */
    public static final float FOOTER_LINE_BOTTOM_DISTANCE = 46;


    /**
     * 右侧留白
     */
    public static final float MARGIN_RIGHT = 20;
    /**
     * 为页眉线到顶部距离
     */
    public static final float HEADER_LINE_TOP_DISTANCE = 60;
    /**
     * 页眉文字到页眉线留白
     */
    public static final float HEADER_PADDING_BOTTOM = 10;

    /**
     * 页眉文本间距
     */
    public static final float HEADER_TEXT_DISTANCE = 1;


    //摘要页页脚左侧留白
    public static final float ABSTRACT_MARGIN_LEFT = 70;

    //摘要页右侧留白右侧留白
    public static final float ABSTRACT_MARGIN_RIGHT = 70;


    /**
     * 摘要页脚线到底部距离
     */
    public static final float ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_1 = 40;
    /**
     * 摘要页脚线到底部距离
     */
    public static final float ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_2 = 15;





    /**
     * 页眉页脚文本
     */
    private HeaderAndFooterSet headerAndFooterSet;

    /**
     * 是否需要分割线
     */
    private Boolean divisionLineFlag;

    /**
     * 图片路径
     */
    private String logoPath;

    /**
     * 是否为摘要页
     */
    private Boolean isAbstract;

    public HeaderFooterHandler(Boolean divisionLineFlag, HeaderAndFooterSet footerFontSet, Boolean isAbstract) {
        this.divisionLineFlag = divisionLineFlag;
        this.headerAndFooterSet = footerFontSet;
        this.isAbstract = isAbstract;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        PdfDocument pdfDoc = ((PdfDocumentEvent) event).getDocument();
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(),page.getResources(), pdfDoc);
        try {
            if(Boolean.TRUE.equals(getDivisionLineFlag()) && Boolean.FALSE.equals(isAbstract)){
                // 画页脚分割线
                pdfCanvas.setStrokeColor(getHeaderAndFooterSet().getFontColor()).moveTo(MARGIN_LEFT, FOOTER_LINE_BOTTOM_DISTANCE).lineTo(pageSize.getWidth()- MARGIN_RIGHT, FOOTER_LINE_BOTTOM_DISTANCE).closePathStroke();
                // 画页眉分割线
                pdfCanvas.setStrokeColor(getHeaderAndFooterSet().getFontColor()).moveTo(MARGIN_LEFT, pageSize.getHeight() - HEADER_LINE_TOP_DISTANCE).lineTo(pageSize.getWidth()- MARGIN_RIGHT, pageSize.getHeight() - HEADER_LINE_TOP_DISTANCE).closePathStroke();
            }else if (Boolean.TRUE.equals(getDivisionLineFlag()) && Boolean.TRUE.equals(isAbstract)) {
                //摘要页
                pdfCanvas.setStrokeColor(getHeaderAndFooterSet().getFontColor()).moveTo(MARGIN_LEFT, pageSize.getHeight() - HEADER_LINE_TOP_DISTANCE).lineTo(pageSize.getWidth()- MARGIN_RIGHT, pageSize.getHeight() - HEADER_LINE_TOP_DISTANCE).closePathStroke();

                //第一条页脚线
                pdfCanvas.setStrokeColor(getHeaderAndFooterSet().getFontColor())
                        .moveTo(ABSTRACT_MARGIN_LEFT, ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_1)
                        .lineTo(pageSize.getWidth()- ABSTRACT_MARGIN_RIGHT, ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_1)
                        .closePathStroke();
                //第二条页脚线
                pdfCanvas.setStrokeColor(getHeaderAndFooterSet().getFontColor())
                        .moveTo(ABSTRACT_MARGIN_LEFT, ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_2)
                        .lineTo(pageSize.getWidth()- ABSTRACT_MARGIN_RIGHT, ABSTRACT_FOOTER_LINE_BOTTOM_DISTANCE_2)
                        .closePathStroke();
            }
            // ------绘制页眉logo---------
            //添加页眉logo
            InputStream io = getClass().getClassLoader().getResourceAsStream("static/logo.png");
            byte[] imageBytes = IoUtil.readBytes(io);
            Image logoImage = new Image(ImageDataFactory.create(imageBytes));
            PdfCanvas logo= new PdfCanvas(page.newContentStreamBefore(),
                    page.getResources(), pdfDoc);
            Rectangle logoPosition = new Rectangle(MARGIN_LEFT+8,pageSize.getHeight() - HEADER_LINE_TOP_DISTANCE + 10,logoImage.getImageScaledWidth(), logoImage.getImageHeight());
            Canvas  logoCanvas = new Canvas(logo, logoPosition)
                    .add(logoImage);
            logoCanvas.close();

            HeaderAndFooterSet headerFooter = getHeaderAndFooterSet();
            if(Objects.isNull(headerFooter)){
                return;
            }
            // 加载字体
            PdfFont font = PdfFontFactory.createFont(headerFooter.getFontPath(), PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_NOT_EMBEDDED);
            //绘制页眉文本
            if(CollUtil.isNotEmpty(headerFooter.getHeaderTextList())){
                canvasHeaderText(pdfCanvas,pageSize,font,headerAndFooterSet);
            }
            //绘制页脚文本
            float y = 0;
            if(CollUtil.isNotEmpty(headerFooter.getHeaderTextList())){
              y =  canvasFooterText(pdfCanvas, font,headerAndFooterSet);
            }
            if(Objects.nonNull(headerFooter.getFooterFontSize()) && Boolean.FALSE.equals(isAbstract)){
                float footerFontSize = headerFooter.getFooterFontSize();

                // 页码
                String pageNumTextContent = "Page " + pdfDoc.getPageNumber(page);
                int pageNumTextLength = pageNumTextContent.length();
                // x坐标：页面宽度-右侧margin-字体占位宽度
                // y坐标：页脚线到页面底部-字体占位高度
                Rectangle pageNumPosition =
                        new Rectangle(pageSize.getWidth()-MARGIN_RIGHT - footerFontSize*pageNumTextLength - 15,
                                y+11,
                                footerFontSize * pageNumTextLength, footerFontSize);
                Canvas pageNumCanvas = new Canvas(pdfCanvas, pageNumPosition);
                Text pageNumText = new Text(pageNumTextContent).setFont(font).setFontSize(footerFontSize).setFontColor(headerFooter.getFontColor());
                Paragraph pageNumParagraph = new Paragraph().add(pageNumText).setMarginRight(0).setTextAlignment(TextAlignment.RIGHT);
                pageNumParagraph.setBold();
                pageNumCanvas.add(pageNumParagraph);
                pageNumCanvas.close();
            }
        }catch (Exception e){
            log.error("pdf添加页眉页脚异常:{}",e.getMessage(),e);
            throw new RuntimeException();
        }
    }


    /**
     *  绘制页眉文本(从下到上分行绘制)
     * @param pdfCanvas 绘制对象
     * @param pageSize 页面大小
     * @param font 字体
     * @param headerAndFooterSet 页眉页脚相关参数
     */
    private void canvasHeaderText(PdfCanvas pdfCanvas,Rectangle pageSize,PdfFont font,HeaderAndFooterSet headerAndFooterSet){
        List<String> text = getHeaderAndFooterSet().getHeaderTextList();
        float headerFontSize = headerAndFooterSet.getHeaderFontSize();
        Color fontColor = headerAndFooterSet.getFontColor();
        for (int i=0;i<text.size();i++) {
            String headerText = text.get(i);
            int headerTextLength = headerText.length();
            //字体所占宽度
            float textWidth = font.getWidth(headerText, headerFontSize);
            //起始横坐标
            float x = pageSize.getWidth() - MARGIN_RIGHT - textWidth - 15;
            if(i>0 || Boolean.FALSE.equals(isAbstract)){
                x = x + headerFontSize;
            }
            //起始纵坐标(从下到上)
            float y = pageSize.getHeight() - (HEADER_LINE_TOP_DISTANCE- (0.5f * headerFontSize)) + HEADER_PADDING_BOTTOM + (i*headerFontSize)+(i*HEADER_TEXT_DISTANCE);
            if(Boolean.TRUE.equals(getHeaderAndFooterSet().getBoldFlag())){
                y = pageSize.getHeight() - (HEADER_LINE_TOP_DISTANCE- (0.5f * headerFontSize)) + HEADER_PADDING_BOTTOM + (0.4f*i*headerFontSize)+(0.4f*i*HEADER_TEXT_DISTANCE);
            }
            if(Boolean.FALSE.equals(isAbstract)){
                y = pageSize.getHeight() - (HEADER_LINE_TOP_DISTANCE- (0.5f * headerFontSize))-5;
            }
            Canvas headerTextCanvas = new Canvas(pdfCanvas,new Rectangle(x,y,headerFontSize * headerTextLength, headerFontSize));
            // 设置下字体
            Text canvasText = new Text(headerText).setFont(font).setFontSize(headerFontSize).setFontColor(fontColor);
            Paragraph headerParagraph = new Paragraph().add(canvasText).setMarginRight(0);
            //加粗
            if(Boolean.TRUE.equals(headerAndFooterSet.getBoldFlag())){
                headerParagraph.setBold();
            }

            headerTextCanvas.add(headerParagraph);
            headerTextCanvas.close();
        }
    }

    /**
     *  绘制非摘要页页脚文本(文本居左)
     * @param pdfCanvas 绘制对象
     * @param font 字体
     * @param headerAndFooterSet 页眉页脚相关参数
     */
    private float canvasFooterText(PdfCanvas pdfCanvas, PdfFont font, HeaderAndFooterSet headerAndFooterSet){
        List<String> text = getHeaderAndFooterSet().getFooterTextList();
        float footerFontSize = headerAndFooterSet.getFooterFontSize();
        float y = 0;
        Color fontColor = headerAndFooterSet.getFontColor();
        for (int i=0;i<text.size();i++) {
            String footerText = text.get(i);
            //字体所占宽度
            float textWidth = font.getWidth(footerText, footerFontSize);
            //起始纵坐标(从下到上)
             y = FOOTER_LINE_BOTTOM_DISTANCE - footerFontSize - (i*footerFontSize);
            if(Boolean.TRUE.equals(headerAndFooterSet.getBoldFlag()) && Boolean.FALSE.equals(isAbstract)){
                y = FOOTER_LINE_BOTTOM_DISTANCE - 1.5f*footerFontSize - (1.5f*i*footerFontSize)-5;
            }
            float x = MARGIN_LEFT;
            if(isAbstract){
                x = 140;
                y = 17;
            }
            Canvas headerTextCanvas = new Canvas(pdfCanvas,new Rectangle(x,y,textWidth, footerFontSize));
            // 设置下字体

            Text canvasText = new Text(footerText).setFont(font).setFontSize(footerFontSize).setFontColor(fontColor);
            Paragraph footerParagraph = new Paragraph().add(canvasText).setMarginRight(0);
            //加粗
            if(Boolean.TRUE.equals(headerAndFooterSet.getBoldFlag()) && Boolean.FALSE.equals(isAbstract)){
                footerParagraph.setBold();
            }
            if(Boolean.TRUE.equals(isAbstract)){
                footerParagraph.setItalic();
            }
            headerTextCanvas.add(footerParagraph);
            headerTextCanvas.close();
        }
        return y;
    }

}
