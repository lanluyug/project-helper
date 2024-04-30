package kk.lanluyu.projecthelper.core.util.html2pdf;

import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.tags.HtmlTagWorker;
import com.itextpdf.layout.Document;
import com.itextpdf.styledxmlparser.node.IElementNode;

/**
 * <p>自定义左右边距</p>
 *
 * @author Procon
 * @since 2023/10/26
 */
public class CustomerMarginHtmlTagWorker extends HtmlTagWorker {
    public CustomerMarginHtmlTagWorker(IElementNode element, ProcessorContext context) {
        super(element, context);
        Document doc = (Document) getElementResult();
        doc.setTopMargin(60);
        doc.setBottomMargin(44);
        doc.setLeftMargin(20);
        doc.setRightMargin(20);
    }
}
