package kk.lanluyu.projecthelper.core.util.html2pdf;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.html.TagConstants;
import com.itextpdf.styledxmlparser.node.IElementNode;

/**
 * <p>自定义标签样式</p>
 *
 * @author Procon
 * @since 2023/10/26
 */
public class CustomTagWorkerFactory extends DefaultTagWorkerFactory {
    @Override
    public ITagWorker getCustomTagWorker(IElementNode tag, ProcessorContext context) {
        if (TagConstants.HTML.equals(tag.name())) {
            return new CustomerMarginHtmlTagWorker(tag, context);
        }
        return null;
    }
}
