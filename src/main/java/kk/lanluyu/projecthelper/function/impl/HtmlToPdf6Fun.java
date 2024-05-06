package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.core.util.ExportUtils;
import kk.lanluyu.projecthelper.function.HpExporter;
import kk.lanluyu.projecthelper.model.dto.ExportDto;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zzh
 * @date 2024-05-06
 */
@Slf4j
public class HtmlToPdf6Fun implements HpExporter {


    @Override
    public void export(ExportDto exportDto, HttpServletResponse response) {
        ExportUtils.exportPdf(response, exportDto);
    }
}
