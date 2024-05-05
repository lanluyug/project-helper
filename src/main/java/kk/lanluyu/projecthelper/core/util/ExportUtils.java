package kk.lanluyu.projecthelper.core.util;

import com.alibaba.excel.EasyExcelFactory;
import kk.lanluyu.projecthelper.core.domain.CommonException;
import kk.lanluyu.projecthelper.model.dto.ExportDto;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.CharsetUtil;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * @author zzh
 * @date 2024-04-30
 */
@Slf4j
public class ExportUtils {

    private ExportUtils(){}


    public static void setPdfResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        if(StrUtil.isEmpty(fileName)){
            fileName = "export.pdf";
        }
        response.setContentType("application/pdf");
        fileName = URLEncoder.encode(fileName, CharsetUtil.NAME_UTF_8).replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);

    }

    public static void setExcelResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        if(StrUtil.isEmpty(fileName)){
            fileName = "export";
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(CharsetUtil.NAME_UTF_8);
        fileName = URLEncoder.encode(fileName, CharsetUtil.NAME_UTF_8).replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName +".xlsx");
    }

    public static <T> void exportExcel(HttpServletResponse response, String fileName,
                                       Class<T> clazz, List<T> records){
        try{
            ExportUtils.setExcelResponse(response, fileName);
            EasyExcelFactory.write(response.getOutputStream(), clazz)
                    .autoCloseStream(Boolean.FALSE).sheet().doWrite(records);
        } catch (IOException e) {
            response.reset();
            response.setContentType("application/json");
            log.error("导出失败:{}", e.getMessage(), e);
            throw new CommonException("", "导出失败");
        }
    }

    public static void exportPdf(HttpServletResponse response, ExportDto exportDto){
        if(!exportDto.getFileName().endsWith(Html2PdfUtils.SUFFIX_PDF)){
            exportDto.setFileName( exportDto.getFileName() + Html2PdfUtils.SUFFIX_PDF);
        }
        try{
            ExportUtils.setPdfResponse(response, exportDto.getFileName());
            Html2PdfUtils.html2Pdf(exportDto.getText(), response);
        } catch (IOException e) {
            response.reset();
            response.setContentType("application/json");
            log.error("导出失败:{}", e.getMessage(), e);
            throw new CommonException("", "导出失败");
        }
    }
}
