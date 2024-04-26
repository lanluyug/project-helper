package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.common.domain.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzh
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @PostMapping("run")
    public CommonResponse run(){
        return null;
    }

}
