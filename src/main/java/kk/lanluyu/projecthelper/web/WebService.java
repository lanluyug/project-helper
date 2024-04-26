package kk.lanluyu.projecthelper.web;

import kk.lanluyu.projecthelper.common.domain.CommonResponse;

/**
 * @author zzh
 * @date 2024/04/26
 */

public interface WebService {

    CommonResponse run(RunDto runDto);
}
