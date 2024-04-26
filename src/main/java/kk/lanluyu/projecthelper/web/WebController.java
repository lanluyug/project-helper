package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.common.domain.CommonResponse;
import kk.lanluyu.projecthelper.function.model.dto.RunDto;
import kk.lanluyu.projecthelper.function.model.vo.RunVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("test")
    public CommonResponse<String> test(){
        System.out.println("test");
        return CommonResponse.success("test", "");
    }

}
