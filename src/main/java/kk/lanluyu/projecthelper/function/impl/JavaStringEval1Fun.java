package kk.lanluyu.projecthelper.function.impl;

import kk.lanluyu.projecthelper.function.HpExecutor;
import kk.lanluyu.projecthelper.function.HpExecutorContext;
import kk.lanluyu.projecthelper.model.dto.RunDto;
import kk.lanluyu.projecthelper.model.vo.RunVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;
import org.springframework.stereotype.Component;

/**
 * Component采用 HpExecutor_ + id的形式
 * @author zzh
 * @date 2024/04/26
 */
@Component(HpExecutorContext.COMPONENT_PREFIX + "1")
@Slf4j
public class JavaStringEval1Fun implements HpExecutor {

    @Override
    public RunVo execute(RunDto runDto) {
        MapContext context = new MapContext();
        // 创建运行环境
        JexlEngine engine = new Engine();
        // 执行代码
        JexlExpression expression = engine.createExpression(runDto.getText());
        String evaluate = (String) expression.evaluate(context);
        log.info(evaluate);
        return new RunVo(evaluate);
    }
}
