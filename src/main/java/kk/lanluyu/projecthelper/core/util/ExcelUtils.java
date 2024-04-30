package kk.lanluyu.projecthelper.core.util;

import com.alibaba.excel.EasyExcel;
import kk.lanluyu.projecthelper.core.domain.CommonException;
import lombok.extern.slf4j.Slf4j;
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
public class ExcelUtils {


    public static void setResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName +".xlsx");
    }

    public static <T> void exportExcel(HttpServletResponse response, String fileName,
                                       Class<T> clazz, List<T> records){
        try{
            ExcelUtils.setResponse(response, fileName);
            EasyExcel.write(response.getOutputStream(), clazz)
                    .autoCloseStream(Boolean.FALSE).sheet().doWrite(records);
        } catch (IOException e) {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            log.error("导出失败:{}", e.getMessage(), e);
            throw new CommonException("", "导出失败");
        }
    }
}
