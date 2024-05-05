package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.core.domain.CommonResponse;
import kk.lanluyu.projecthelper.model.dto.ExportDto;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.OptionVo;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zzh
 * @date 2024-04-26
 */
@RestController
@RequestMapping("web")
public class WebController {

    @Autowired
    private WebService webService;

    @PostMapping("run")
    public CommonResponse<RunVo> run(@RequestBody RunDto runDto){
        return webService.run(runDto);
    }

    @GetMapping("listMode")
    public CommonResponse<List<OptionVo>> listMode(){
        return webService.listMode();
    }

    @PostMapping("export")
    public void export(HttpServletResponse response, @RequestBody ExportDto exportDto){
        webService.export(response, exportDto);
    }

}
